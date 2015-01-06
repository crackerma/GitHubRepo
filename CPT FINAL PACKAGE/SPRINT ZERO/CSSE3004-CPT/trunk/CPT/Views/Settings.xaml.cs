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

namespace CPT.Views
{
    public partial class Settings : PhoneApplicationPage
    {
        private SettingsViewModel vM = SettingsViewModel.INSTANCE;

        private bool IsLoaded = false;

        public Settings()
        {
            InitializeComponent();
            DataContext = vM;
            Loaded += new RoutedEventHandler(Settings_Loaded);
        }

        private void Settings_Loaded(object sender, RoutedEventArgs e)
        {
            BarcodeFormatPicker.SelectedItem = vM.SelectedBarcodeFormat;
            IsLoaded = true;
        }

        private void BarcodeFormatPicker_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (!IsLoaded)
                return;
            vM.SelectedBarcodeFormat = (string)BarcodeFormatPicker.SelectedItem;
        }
    }
}