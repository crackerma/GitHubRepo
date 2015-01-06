using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using System.ServiceModel;
using CPT.WebAPI.Tests.WebServiceClient;

namespace CPT.WebAPI.Tests
{
    [TestFixture]
    public class ClientUnitTests
    {
        /// <summary>
        /// Unit Test 7: Client correctly handles no internet access
        /// </summary>
        [Test]
        public void LossOfInternetAccess()
        {
            PublicServiceClient fake = new PublicServiceClient("BasicHttpBinding_IPublicService_Fake");
            Assert.Catch(typeof(EndpointNotFoundException), delegate
            {
                fake.GetProduct(String.Empty);
            });
            PublicServiceClient real = new PublicServiceClient("BasicHttpBinding_IPublicService");
            Assert.DoesNotThrow(delegate
            {
                real.GetProduct(String.Empty);
            });

            fake.Close();
            real.Close();
        }

        /// <summary>
        /// API Unit Test: Test Web Service (GetProduct)
        /// Unit Test 3: Client Application retrieves correct Product Record
        /// Unit Test 4: Client Application correctly handles 'no record found' web service response
        /// </summary>
        [Test]
        public void TestGetProduct()
        {
            PublicServiceClient client = new PublicServiceClient("BasicHttpBinding_IPublicService");

            Assert.IsNull(client.GetProduct("0000000000000"));
            Assert.AreEqual(client.GetProduct("9300675020923").ProductID, "9300675020923");

            client.Close();
        }

        /// <summary>
        /// API Unit Test: Test Web Service (GetProducts)
        /// </summary>
        [Test]
        public void TestGetProducts()
        {
            String[] barcodes_fake = { "0000000000000", "0000000000000", "0000000000000" };
            String[] barcodes_real = { "9300675001113", "9300675020923", "08043256017" };

            PublicServiceClient client = new PublicServiceClient("BasicHttpBinding_IPublicService");

            Assert.IsTrue(client.GetProducts(barcodes_fake).Count() == 0);
            Assert.IsTrue(client.GetProducts(barcodes_real).Where(p => barcodes_real.Contains(p.ProductID)).Count() == barcodes_real.Count());

            client.Close();
        }

        /// <summary>
        /// API Unit Test: Test Web Service (GetReviews)
        /// </summary>
        [Test]
        public void TestGetReviews()
        {
            PublicServiceClient client = new PublicServiceClient("BasicHttpBinding_IPublicService");

            Assert.IsNull(client.GetReviews("0000000000000"));
            Assert.IsNotNull(client.GetReviews("9300675001113"));

            client.Close();
        }

        /// <summary>
        /// API Unit Test: Test Web Service (AddReview)
        /// </summary>
        [Test]
        public void TestAddReview()
        {
            PublicServiceClient client = new PublicServiceClient("BasicHttpBinding_IPublicService");

            Assert.IsNotEmpty(client.AddReview("0000000000000", new Review() { FacebookID = "jeremywwl", ProductRating = 5, ProductReview = "Best Drink in Town" }));
            Assert.IsEmpty(client.AddReview("9300675001113", new Review() { FacebookID = "jeremywwl", ProductRating = 5, ProductReview = "Best Drink in Town" }));

            client.Close();
        }
    }
}
