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
using Microsoft.Phone.Controls.Maps;
using CPT.WebService;

namespace CPT.Views
{
    public partial class ProductView : PhoneApplicationPage
    {
        private ProductViewModel vM = new ProductViewModel(MainViewModel.INSTANCE.ConsumeCurrentProduct());

        private int Rating
        {
            get
            {
                if ((bool)ReviewRating2.IsChecked)
                    return 2;
                else if ((bool)ReviewRating3.IsChecked)
                    return 3;
                else if ((bool)ReviewRating4.IsChecked)
                    return 4;
                else if ((bool)ReviewRating5.IsChecked)
                    return 5;
                return 1;
            }
        }

        public ProductView()
        {
            InitializeComponent();
            DataContext = vM;
            Loaded += new RoutedEventHandler(ProductView_Loaded);
        }

        private void ProductView_Loaded(object sender, RoutedEventArgs e)
        {
            ManufacturerMap.Center = vM.ManufacturerMapCenter;
            ManufacturerMap.Children.Add(new Pushpin() { Location = vM.ManufacturerMapCenter, Content = "Manufacturer Location" });
        }

        private void AddReview_Click(object sender, RoutedEventArgs e)
        {
            if (ReviewText.Text.Length < 1)
                MessageBox.Show("You must enter a review comment.", "Review Comment Required", MessageBoxButton.OK);

            vM.AddReview(new Review()
            {
                FacebookID = SettingsViewModel.INSTANCE.FBID,
                ProductRating = Rating,
                ProductReview = ReviewText.Text
            });
        }
    }
}