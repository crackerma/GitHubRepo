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
    public partial class Main : PhoneApplicationPage
    {
        private MainViewModel viewModel = new MainViewModel();

        public Main()
        {
            InitializeComponent();
            DataContext = viewModel;
            GuideBrowser.NavigateToString(viewModel.GuideText);
        }

        private void BarcodeScan_Click(object sender, EventArgs e)
        {
            viewModel.ScanBarcode();
        }
    }
}