using System;
using System.Collections.Specialized;
using System.Windows;
using CPT.WebService;
using Facebook;
using WP7_Barcode_Library;
using System.Data;
using System.Data.Linq;
using System.Linq;
using System.Collections.ObjectModel;

namespace CPT.ViewModels
{
    public class MainViewModel : BaseModel
    {
        public static MainViewModel INSTANCE 
        { 
            get 
            {
                if (mINSTANCE == null)
                    mINSTANCE = new MainViewModel();
                return mINSTANCE; 
            } 
        }
        private static MainViewModel mINSTANCE;

        private enum GetProductsMode
        {
            Recents,
            Favourites
        }

        #region Facebook States
        private const string TokenCheck = "token_check";
        #endregion

        #region Properties
        #region Facebook
        public Uri FBLoginURL
        {
            get
            {
                if (FBBrowserLoginVisibility != Visibility.Visible)
                    return null;
                else
                    return FB.GetLoginUrl(new
                    {
                        client_id = "357152384321188",
                        response_type = "token",
                        redirect_uri = "http://www.facebook.com/connect/login_success.html",
                        display = "touch"
                    });
            }
        }

        public Uri FBLogoutURL
        {
            get
            {
                if (FBBrowserLogoutVisibility != Visibility.Visible)
                    return null;
                else
                    return FB.GetLogoutUrl(new
                    {
                        next = "http://www.facebook.com/",
                        access_token = FBAccessToken
                    });
            }
        }

        public FacebookClient FB
        {
            get { return mFB; }
            set 
            {
                if (FB != null)
                    FB.GetCompleted -= FB_GetCompleted;
                mFB = value;
                FB.GetCompleted += FB_GetCompleted;
            }
        }
        private FacebookClient mFB;

        public string FBAccessToken
        {
            get { return SettingsViewModel.INSTANCE.FBAccessToken; }
            set
            {
                SettingsViewModel.INSTANCE.FBAccessToken = value;
                NotifyPropertyChanged(() => FBAccessToken);
            }
        }

        public string FBID
        {
            get { return SettingsViewModel.INSTANCE.FBID; }
            set
            {
                SettingsViewModel.INSTANCE.FBID = value;
                NotifyPropertyChanged(() => FBID);
            }
        }

        public bool FBLoggedIn { get { return !String.IsNullOrEmpty(FBAccessToken); } }

        public Visibility FBBrowserLoginVisibility { get { return GetValue(() => FBBrowserLoginVisibility); } set { SetValue(() => FBBrowserLoginVisibility, value); } }

        public Visibility FBBrowserLogoutVisibility { get { return GetValue(() => FBBrowserLogoutVisibility); } set { SetValue(() => FBBrowserLogoutVisibility, value); } }
        #endregion

        public ObservableList<Product> RecentProducts { get { return GetValue(() => RecentProducts); } set { SetValue(() => RecentProducts, value); } }

        public ObservableList<Product> FavouriteProducts { get { return GetValue(() => FavouriteProducts); } set { SetValue(() => FavouriteProducts, value); } }

        public Product CurrentProduct { get { return GetValue(() => CurrentProduct); } set { SetValue(() => CurrentProduct, value); } }

        public PublicServiceClient APIClient
        {
            get { return mAPIClient; }
            set 
            {
                if (APIClient != null)
                {
                    APIClient.OpenCompleted -= APIClient_OpenCompleted;
                    APIClient.GetProductCompleted -= APIClient_GetProductCompleted;
                    APIClient.GetProductsCompleted -= APIClient_GetProductsCompleted;
                    APIClient.CloseCompleted -= APIClient_CloseCompleted;
                }

                mAPIClient = value;

                if (APIClient != null)
                {
                    APIClient.OpenCompleted += APIClient_OpenCompleted;
                    APIClient.GetProductCompleted += APIClient_GetProductCompleted;
                    APIClient.GetProductsCompleted += APIClient_GetProductsCompleted;
                    APIClient.CloseCompleted += APIClient_CloseCompleted;
                }
            }
        }
        private PublicServiceClient mAPIClient;

        public bool IsLoading { get { return GetValue(() => IsLoading); } set { SetValue(() => IsLoading, value); } }

        public bool CanScanBarcode { get { return !IsLoading; } }
        #endregion

        #region Methods
        public void ScanBarcode()
        {
            IsLoading = true;
            WP7BarcodeManager.ScanMode = SettingsViewModel.INSTANCE.BarcodeFormatActual;
            WP7BarcodeManager.ScanBarcode((Action<BarcodeCaptureResult>)delegate(BarcodeCaptureResult result)
            {
                if (!String.IsNullOrEmpty(result.BarcodeText))
                {
                    APIClient = new PublicServiceClient();
                    APIClient.OpenAsync(result.BarcodeText);
                    return;
                }

                Deployment.Current.Dispatcher.BeginInvoke(delegate
                {
                    MessageBox.Show("No barcode detected, make sure you take a clear picture of the products barcode.", "No Barcode Detected", MessageBoxButton.OK);
                    IsLoading = false;
                });
            });
        }

        private void APIClient_OpenCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while trying to establish a connection with the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }

            if (e.UserState is string)
                APIClient.GetProductAsync((string)e.UserState);
            else
            {
                object[] args = (object[])e.UserState;
                APIClient.GetProductsAsync((ObservableCollection<string>)args[0], args[1]);
            }
        }

        private void APIClient_GetProductsCompleted(object sender, GetProductsCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while retrieving data from the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }
            APIClient.CloseAsync(new object[] { e.Result, e.UserState });
        }

        private void APIClient_GetProductCompleted(object sender, GetProductCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while retrieving data from the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }

            if (e.Result == null)
            {
                MessageBox.Show("The product could not be found in the system, please try again later.", "Product Not Found", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }

            APIClient.CloseAsync(e.Result);
        }

        private void APIClient_CloseCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        {
            if (e.Error != null)
                MessageBox.Show("An error occurred while trying to close a connection to the system.", "Communication Error", MessageBoxButton.OK);

            if (e.UserState != null)
            {
                if (e.UserState is Product)
                {
                    CurrentProduct = (Product)e.UserState;
                    AddRecentProduct(((Product)e.UserState).ProductID);
                }
                else
                {
                    object[] args = (object[])e.UserState;
                    if ((GetProductsMode)args[1] == GetProductsMode.Recents)
                    {
                        RecentProducts = new ObservableList<Product>();
                        RecentProducts.AddRange((ObservableCollection<Product>)args[0]);

                        APIClient = new PublicServiceClient();
                        APIClient.OpenAsync(new object[] { new ObservableCollection<string>(SettingsViewModel.INSTANCE.FavouriteProducts), GetProductsMode.Favourites });
                    }
                    else
                    {
                        FavouriteProducts = new ObservableList<Product>();
                        FavouriteProducts.AddRange((ObservableCollection<Product>)args[0]);
                    }
                }
            }
            Deployment.Current.Dispatcher.BeginInvoke(delegate { IsLoading = false; });
        }

        public void UpdateProductLists()
        {
            IsLoading = true;
            APIClient = new PublicServiceClient();
            APIClient.OpenAsync(new object[] { new ObservableCollection<string>(SettingsViewModel.INSTANCE.RecentProducts), GetProductsMode.Recents });
        }

        public void AddRecentProduct(string productID)
        {
            if (!SettingsViewModel.INSTANCE.RecentProducts.Contains(productID))
                SettingsViewModel.INSTANCE.RecentProducts.Add(productID);
            if (SettingsViewModel.INSTANCE.RecentProducts.Count > 9)
                SettingsViewModel.INSTANCE.RecentProducts.RemoveAt(10);
            UpdateProductLists();
        }

        public void AddFavouriteProduct(string productID)
        {
            if (!SettingsViewModel.INSTANCE.FavouriteProducts.Contains(productID))
                SettingsViewModel.INSTANCE.FavouriteProducts.Add(productID);
            UpdateProductLists();
        }

        public void RemoveFavouriteProduct(string productID)
        {
            if (SettingsViewModel.INSTANCE.FavouriteProducts.Contains(productID))
                SettingsViewModel.INSTANCE.FavouriteProducts.Remove(productID);
            UpdateProductLists();
        }

        public Product ConsumeCurrentProduct()
        {
            Product product = CurrentProduct;
            CurrentProduct = null;
            return product;
        }

        public bool FBLogin()
        {
            return FBLogin(null);
        }

        public bool FBLogin(Uri loginState)
        {
            if (loginState == null)
            {
                FBBrowserLoginVisibility = Visibility.Visible;
                NotifyPropertyChanged(() => FBLoginURL);
            }
            else
            {
                if (!loginState.AbsolutePath.StartsWith("/connect") && !loginState.AbsolutePath.StartsWith("/dialog") && !loginState.AbsolutePath.StartsWith("/login.php"))
                {
                    FBBrowserLoginVisibility = Visibility.Collapsed;
                    return true;
                }

                if (loginState.AbsoluteUri.StartsWith("http://www.facebook.com/connect/login_success.html"))
                {
                    FBAccessToken = FB.ParseOAuthCallbackUrl(loginState).AccessToken;
                    FBBrowserLoginVisibility = Visibility.Collapsed;
                    return true;
                }
            }
            return false;
        }

        public bool FBLogout()
        {
            return FBLogout(null);
        }

        public bool FBLogout(Uri logoutState)
        {
            if (logoutState == null)
            {
                FBBrowserLogoutVisibility = Visibility.Visible;
                NotifyPropertyChanged(() => FBLogoutURL);
            }
            else
            {
                if (!logoutState.AbsoluteUri.StartsWith("http://www.facebook.com/logout.php"))
                {
                    FBAccessToken = String.Empty;
                    FBID = String.Empty;
                    FBBrowserLogoutVisibility = Visibility.Collapsed;
                    return true;
                }
            }
            return false;
        }

        public void FBCancelled()
        {
            FBBrowserLoginVisibility = Visibility.Collapsed;
            FBBrowserLogoutVisibility = Visibility.Collapsed;
        }

        public void VerifyToken()
        {
            if (String.IsNullOrEmpty(FBAccessToken))
                FB = new FacebookClient();
            else
            {
                FB = new FacebookClient(FBAccessToken);
                FB.GetAsync("me", null, TokenCheck);
            }
        }

        private void FB_GetCompleted(object sender, FacebookApiEventArgs e)
        {
            switch ((string)e.UserState)
            {
                case TokenCheck:
                    {
                        if (e.Error != null)
                            Deployment.Current.Dispatcher.BeginInvoke(delegate
                            {
                                FBAccessToken = String.Empty;
                                FBID = String.Empty;
                            });
                        else
                            Deployment.Current.Dispatcher.BeginInvoke(delegate
                            {
                                FBID = (string)((JsonObject)e.GetResultData())["id"];
                            });
                        break;
                    }
            }
        }

        protected override void NotifyPropertyChanged(string propName)
        {
            if (propName == GetPropertyName(() => FBAccessToken))
            {
                VerifyToken();
                NotifyPropertyChanged(() => FBLoggedIn);
            }

            if (propName == GetPropertyName(() => IsLoading))
                NotifyPropertyChanged(() => CanScanBarcode);

            base.NotifyPropertyChanged(propName);
        }
        #endregion

        #region Initialization
        public MainViewModel()
        {
            if (mINSTANCE != null && mINSTANCE != this)
                throw new InvalidOperationException("There can only be a single instance of MainViewModel.");

            // Initialize Model
            IsLoading = false;
            FBBrowserLoginVisibility = Visibility.Collapsed;
            FBBrowserLogoutVisibility = Visibility.Collapsed;
            UpdateProductLists();
            VerifyToken();
        }
        #endregion
    }
}
