using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using CPT.WebService;
using System.Device.Location;
using System.Collections.ObjectModel;
using System.Windows.Media.Imaging;
using System.Data;
using System.Data.Linq;
using System.Linq;
using Facebook;

namespace CPT.ViewModels
{
    public class ProductViewModel : BaseModel
    {
        public class ReviewProxy : BaseModel
        {
            public Review ProxyReview { get; private set; }

            public string Image { get { return String.Format("http://graph.facebook.com/{0}/picture", ProxyReview.FacebookID); } }

            public string Name { get { return GetValue(() => Name); } set { SetValue(() => Name, value); } }

            public FacebookClient FB { get { return GetValue(() => FB); } set { SetValue(() => FB, value); } }

            public ReviewProxy(Review review)
            {
                ProxyReview = review;
                FB = new FacebookClient();
                FB.GetCompleted += FB_GetCompleted;
                FB.GetAsync(ProxyReview.FacebookID);
            }

            private void FB_GetCompleted(object sender, FacebookApiEventArgs e)
            {
                if (e.Error == null)
                    Deployment.Current.Dispatcher.BeginInvoke(delegate { Name = String.Format("{0} ({1}/5)", (string)((JsonObject)e.GetResultData())["name"], ProxyReview.ProductRating.ToString()); });
                else
                    Deployment.Current.Dispatcher.BeginInvoke(delegate { Name = String.Format("Not Found ({0}/5)", ProxyReview.ProductRating.ToString()); });
            }
        }

        #region Properties
        public Product CurrentProduct
        {
            get { return mCurrentProduct; }
            set { mCurrentProduct = value; }
        }
        private Product mCurrentProduct;

        public string Nutrition
        {
            get
            {
                string mNutrition = String.Empty;
                string[] facts = CurrentProduct.Nutrition.Split(new char[] { ';' });
                foreach (string fact in facts)
                {
                    string[] parts = fact.Split(new char[] { '=' });
                    mNutrition += String.Format("{0}: {1}{2}", parts[0], parts[1], Environment.NewLine);
                }
                return mNutrition;
            }
        }

        public GeoCoordinate ManufacturerMapCenter
        {
            get { return new GeoCoordinate(CurrentProduct.ProductManufacturer.Latitude, CurrentProduct.ProductManufacturer.Longtitude); }
        }

        public bool IsLoading { get { return GetValue(() => IsLoading); } set { SetValue(() => IsLoading, value); } }

        public PublicServiceClient APIClient
        {
            get { return mAPIClient; }
            set
            {
                if (APIClient != null)
                {
                    APIClient.OpenCompleted -= APIClient_OpenCompleted;
                    APIClient.GetReviewsCompleted -= APIClient_GetReviewsCompleted;
                    APIClient.AddReviewCompleted -= APIClient_AddReviewCompleted;
                    APIClient.CloseCompleted -= APIClient_CloseCompleted;
                }

                mAPIClient = value;

                if (APIClient != null)
                {
                    APIClient.OpenCompleted += APIClient_OpenCompleted;
                    APIClient.GetReviewsCompleted += APIClient_GetReviewsCompleted;
                    APIClient.AddReviewCompleted += APIClient_AddReviewCompleted;
                    APIClient.CloseCompleted += APIClient_CloseCompleted;
                }
            }
        }
        private PublicServiceClient mAPIClient;

        public ObservableList<ReviewProxy> Reviews { get { return GetValue(() => Reviews); } set { SetValue(() => Reviews, value); } }

        public bool AddReviewEnabled { get { return !String.IsNullOrEmpty(SettingsViewModel.INSTANCE.FBID) && !IsLoading; } }

        public Visibility AddReviewVisible 
        { 
            get 
            {
                if (Reviews == null)
                    return Visibility.Visible;
                return (Reviews.Any(r => r.ProxyReview.FacebookID == SettingsViewModel.INSTANCE.FBID) ? Visibility.Collapsed : Visibility.Visible); 
            } 
        }

        public string AddReviewMessage
        {
            get
            {
                if (AddReviewEnabled)
                    return "Add Review:";
                else if (IsLoading)
                    return "Please wait...";
                else
                    return "Login to add review:";
            }
        }
        #endregion

        #region Methods
        public void UpdateReviews()
        {
            IsLoading = true;
            APIClient = new PublicServiceClient();
            APIClient.OpenAsync();
        }

        public void AddReview(Review review)
        {
            IsLoading = true;
            APIClient = new PublicServiceClient();
            APIClient.OpenAsync(review);
        }

        private void APIClient_OpenCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while trying to establish a connection with the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }

            if (e.UserState == null)
                APIClient.GetReviewsAsync(CurrentProduct.ProductID);
            else
                APIClient.AddReviewAsync(CurrentProduct.ProductID, (Review)e.UserState);
        }

        private void APIClient_GetReviewsCompleted(object sender, GetReviewsCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while retrieving data from the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }
            APIClient.CloseAsync(e.Result);
        }

        private void APIClient_AddReviewCompleted(object sender, AddReviewCompletedEventArgs e)
        {
            if (e.Error != null)
            {
                MessageBox.Show("An error occured while retrieving data from the system, please try again later.", "Communication Error", MessageBoxButton.OK);
                APIClient.CloseAsync();
                return;
            }

            if (!String.IsNullOrEmpty(e.Result))
            {
                MessageBox.Show(e.Result, "Add Review Error", MessageBoxButton.OK);
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
                if (e.UserState is string)
                    UpdateReviews();
                else
                {
                    Reviews = new ObservableList<ReviewProxy>();
                    Reviews.AddRange(((ObservableCollection<Review>)e.UserState).Select(r => new ReviewProxy(r)));
                }
            }

            Deployment.Current.Dispatcher.BeginInvoke(delegate 
            {
                NotifyPropertyChanged(() => AddReviewEnabled);
                NotifyPropertyChanged(() => AddReviewVisible);
                NotifyPropertyChanged(() => AddReviewMessage);
                IsLoading = false; 
            });
        }

        protected override void NotifyPropertyChanged(string propName)
        {
            if (propName == GetPropertyName(() => IsLoading))
            {
                NotifyPropertyChanged(() => AddReviewEnabled);
                NotifyPropertyChanged(() => AddReviewVisible);
                NotifyPropertyChanged(() => AddReviewMessage);
            }
            base.NotifyPropertyChanged(propName);
        }
        #endregion

        #region Initialization
        public ProductViewModel(Product product)
        {
            CurrentProduct = product;
            UpdateReviews();
        }
        #endregion
    }
}
