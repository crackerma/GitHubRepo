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
using Microsoft.Phone.Tasks;
using System.Windows.Media.Imaging;
using WP7_Barcode_Library;
using System.IO.IsolatedStorage;
using System.IO;
using System.Reflection;

namespace CPT.ViewModels
{
    public class MainViewModel : BaseModel
    {
        public string BarcodeText { get { return GetValue(() => BarcodeText); } set { SetValue(() => BarcodeText, value); } }

        public BitmapImage BarcodeImage { get { return GetValue(() => BarcodeImage); } set { SetValue(() => BarcodeImage, value); } }

        public bool Scanning { get { return GetValue(() => Scanning); } set { SetValue(() => Scanning, value); } }

        public string GuideText
        {
            get
            {
                Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("CPT.Resources.Guide.html");
                StreamReader reader = new StreamReader(stream);
                string html = reader.ReadToEnd();
                return html;
            }
        }

        public Visibility GuideVisibility { get { return GetValue(() => GuideVisibility); } set { SetValue(() => GuideVisibility, value); } }

        public void ScanBarcode()
        {
            WP7BarcodeManager.ScanBarcode(ScanBarcodeComplete);
            Scanning = true;
        }

        public void ScanBarcodeComplete(BarcodeCaptureResult result)
        {
            GuideVisibility = Visibility.Collapsed;
            if (!String.IsNullOrEmpty(result.BarcodeText))
                BarcodeText = String.Format("Barcode Text: {0}", result.BarcodeText);
            else
                BarcodeText = "No barcode detected.";
            BarcodeImage = result.BarcodeImage;
            Scanning = false;
        }

        public MainViewModel()
        {
            GuideVisibility = Visibility.Visible;
        }
    }
}
