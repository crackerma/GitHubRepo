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
using System.IO.IsolatedStorage;
using System.Collections;
using System.Collections.Generic;
using CPT.WebService;
using com.google.zxing;

namespace CPT.ViewModels
{
    public class SettingsViewModel : BaseModel
    {
        public static SettingsViewModel INSTANCE
        {
            get
            {
                if (mINSTANCE == null)
                    mINSTANCE = new SettingsViewModel();
                return mINSTANCE;
            }
        }
        private static SettingsViewModel mINSTANCE;

        #region Properties
        public string FBAccessToken
        {
            get
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                if (!settings.Contains("FBAccessToken"))
                    settings.Add("FBAccessToken", "");
                return (string)settings["FBAccessToken"];
            }
            set
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                settings["FBAccessToken"] = value;
                NotifyPropertyChanged(() => FBAccessToken);
            }
        }

        public string FBID
        {
            get
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                if (!settings.Contains("FBID"))
                    settings.Add("FBID", "");
                return (string)settings["FBID"];
            }
            set
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                settings["FBID"] = value;
                NotifyPropertyChanged(() => FBID);
            }
        }

        public List<string> RecentProducts
        {
            get
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                if (!settings.Contains("RecentProducts"))
                    settings.Add("RecentProducts", new List<string>());
                return (List<string>)settings["RecentProducts"];
            }
            set
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                settings["RecentProducts"] = value;
                NotifyPropertyChanged(() => RecentProducts);
            }
        }

        public List<string> FavouriteProducts
        {
            get
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                if (!settings.Contains("FavouriteProducts"))
                    settings.Add("FavouriteProducts", new List<string>());
                return (List<string>)settings["FavouriteProducts"];
            }
            set
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                settings["FavouriteProducts"] = value;
                NotifyPropertyChanged(() => FavouriteProducts);
            }
        }

        public List<string> BarcodeFormatOptions
        {
            get
            {
                return new List<string>()
                {
                    "ALL_1D",
                    "CODE_128",
                    "CODE_39",
                    "DATAMATRIX",
                    "EAN_13",
                    "EAN_8",
                    "ITF",
                    "PDF417",
                    "QR_CODE",
                    "UPC_A",
                    "UPC_E",
                    "UPC_EAN",

                };
            }
        }

        public string SelectedBarcodeFormat
        {
            get
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                if (!settings.Contains("SelectedBarcodeFormat"))
                    settings.Add("SelectedBarcodeFormat", "ALL_1D");
                return (string)settings["SelectedBarcodeFormat"];
            }
            set
            {
                var settings = IsolatedStorageSettings.ApplicationSettings;
                settings["SelectedBarcodeFormat"] = value;
                NotifyPropertyChanged(() => SelectedBarcodeFormat);
            }
        }

        public BarcodeFormat BarcodeFormatActual
        {
            get
            {
                switch (SelectedBarcodeFormat)
                {
                    case "CODE_128": return BarcodeFormat.CODE_128;
                    case "CODE_39": return BarcodeFormat.CODE_39;
                    case "DATAMATRIX": return BarcodeFormat.DATAMATRIX;
                    case "EAN_13": return BarcodeFormat.EAN_13;
                    case "EAN_8": return BarcodeFormat.EAN_8;
                    case "ITF": return BarcodeFormat.ITF;
                    case "PDF417": return BarcodeFormat.PDF417;
                    case "QR_CODE": return BarcodeFormat.QR_CODE;
                    case "UPC_A": return BarcodeFormat.UPC_A;
                    case "UPC_E": return BarcodeFormat.UPC_E;
                    case "UPC_EAN": return BarcodeFormat.UPC_EAN;
                    default: return BarcodeFormat.ALL_1D;
                }
            }
        }
        #endregion

        #region Methods
        public void Save()
        {
            var settings = IsolatedStorageSettings.ApplicationSettings;
            settings.Save();
        }

        protected override void NotifyPropertyChanged(string propName)
        {
            Save();
            base.NotifyPropertyChanged(propName);
        }
        #endregion

        #region Initialization
        public SettingsViewModel()
        {
            if (mINSTANCE != null && mINSTANCE != this)
                throw new InvalidOperationException("There can only be a single instance of SettingsViewModel.");
        }
        #endregion
    }
}
