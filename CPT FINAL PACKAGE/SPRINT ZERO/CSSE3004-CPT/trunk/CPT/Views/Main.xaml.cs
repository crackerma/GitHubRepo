using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using CPT.ViewModels;
using System.ComponentModel;
using Microsoft.Phone.Shell;
using CPT.WebService;

namespace CPT.Views
{
    public partial class Main : PhoneApplicationPage
    {
        private MainViewModel vM = MainViewModel.INSTANCE;

        #region App Bar Buttons
        private ApplicationBarIconButton btnFacebook = new ApplicationBarIconButton()
        {
            Text = "login",
            IconUri = new Uri("/Resources/Images/Facebook.png", UriKind.Relative)
        };

        private ApplicationBarIconButton btnSettings = new ApplicationBarIconButton()
        {
            Text = "settings",
            IconUri = new Uri("/Resources/Images/Settings.png", UriKind.Relative)
        };

        private ApplicationBarIconButton btnHelp = new ApplicationBarIconButton()
        {
            Text = "help",
            IconUri = new Uri("/Resources/Images/Help.png", UriKind.Relative)
        };

        private ApplicationBarIconButton btnCancelLogin = new ApplicationBarIconButton()
        {
            Text = "cancel",
            IconUri = new Uri("/Resources/Images/Cancel.png", UriKind.Relative)
        };
        #endregion

        public Main()
        {
            InitializeComponent();
            DataContext = vM;
            vM.PropertyChanged += new PropertyChangedEventHandler(vM_PropertyChanged);

            btnFacebook.Click += new EventHandler(btnFacebook_Click);
            btnSettings.Click += new EventHandler(btnSettings_Click);
            btnHelp.Click += new EventHandler(btnHelp_Click);
            btnCancelLogin.Click += new EventHandler(btnCancelLogin_Click);
            AppBarNormal();
        }

        private void AppBarNormal()
        {
            // Change facebook login button text according to current login state.
            btnFacebook.Text = vM.FBLoggedIn ? "logout" : "login";

            ApplicationBar.Buttons.Clear();
            ApplicationBar.Buttons.Add(btnFacebook);
            ApplicationBar.Buttons.Add(btnSettings);
            ApplicationBar.Buttons.Add(btnHelp);
        }

        private void AppBarCancel()
        {
            ApplicationBar.Buttons.Clear();
            ApplicationBar.Buttons.Add(btnCancelLogin);
        }

        private void btnScanBarcode_Click(object sender, RoutedEventArgs e)
        {
            vM.ScanBarcode();
        }

        private void btnAddFavourite_Click(object sender, RoutedEventArgs e)
        {
            vM.AddFavouriteProduct(((Product)((MenuItem)sender).DataContext).ProductID);
        }

        private void ProductPanel_Tap(object sender, System.Windows.Input.GestureEventArgs e)
        {
            vM.CurrentProduct = (Product)((StackPanel)sender).DataContext;
        }

        private void btnRemoveFavourite_Click(object sender, RoutedEventArgs e)
        {
            vM.RemoveFavouriteProduct(((Product)((MenuItem)sender).DataContext).ProductID);
        }

        private void btnFacebook_Click(object sender, EventArgs e)
        {
            AppBarCancel();
            if (vM.FBLoggedIn)
                vM.FBLogout();
            else
                vM.FBLogin();
        }

        private void btnSettings_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/Views/Settings.xaml", UriKind.Relative));
        }

        private void btnHelp_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/Views/Guide.xaml", UriKind.Relative));
        }

        private void btnCancelLogin_Click(object sender, EventArgs e)
        {
            AppBarNormal();
            vM.FBCancelled();
        }

        private void FBBrowserLogin_Navigated(object sender, System.Windows.Navigation.NavigationEventArgs e)
        {
            if (vM.FBLogin(e.Uri))
                AppBarNormal();
        }

        private void FBBrowserLogout_Navigated(object sender, System.Windows.Navigation.NavigationEventArgs e)
        {
            if (vM.FBLogout(e.Uri))
                AppBarNormal();
        }

        private void vM_PropertyChanged(object sender, PropertyChangedEventArgs e)
        {
            if(e.PropertyName == "CurrentProduct")
                NavigationService.Navigate(new Uri("/Views/ProductView.xaml", UriKind.Relative));
        }
    }
}