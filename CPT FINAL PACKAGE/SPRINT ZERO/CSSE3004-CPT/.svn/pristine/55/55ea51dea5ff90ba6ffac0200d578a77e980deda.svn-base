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
using System.IO;
using System.Reflection;

namespace CPT.Views
{
    public partial class Guide : PhoneApplicationPage
    {
        public Guide()
        {
            InitializeComponent();
            Loaded += new RoutedEventHandler(Guide_Loaded);
        }

        private void Guide_Loaded(object sender, RoutedEventArgs e)
        {
            Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("CPT.Resources.Html.Guide.html"); 
            StreamReader reader = new StreamReader(stream); 
            string html = reader.ReadToEnd();
            GuideBrowser.NavigateToString(html);
        }
    }
}