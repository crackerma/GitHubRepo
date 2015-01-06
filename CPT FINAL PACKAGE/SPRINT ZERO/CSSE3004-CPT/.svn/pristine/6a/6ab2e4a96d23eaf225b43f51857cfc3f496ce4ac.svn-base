using System;
using NUnit.Framework;
using CPT.WebAPI.Contract;
using System.Data;
using System.Linq;
using System.Data.Linq;
using CPT.WebAPI.Contract.Data;

namespace CPT.WebAPI.Tests
{
    [TestFixture]
    public class ServerUnitTests
    {
        /// <summary>
        /// API Unit Test: Test Web Service (GetProduct)
        /// Unit Test 5: Web Service correctly handles bad client requests
        /// </summary>
        [Test]
        public void TestGetProduct()
        {
            PublicService service = new PublicService();

            Assert.IsNull(service.GetProduct("0000000000000"));
            Assert.AreEqual(service.GetProduct("9300675020923").ProductID, "9300675020923");
        }
        
        /// <summary>
        /// API Unit Test: Test Web Service (GetProducts)
        /// </summary>
        [Test]
        public void TestGetProducts()
        {
            String[] barcodes_fake = {"0000000000000", "0000000000000", "0000000000000"};
            String[] barcodes_real = { "9300675001113", "9300675020923", "08043256017" };
            
            PublicService service = new PublicService();

            Assert.IsTrue(service.GetProducts(barcodes_fake).Count() == 0);
            Assert.IsTrue(service.GetProducts(barcodes_real).Where(p => barcodes_real.Contains(p.ProductID)).Count() == barcodes_real.Count());
        }

        /// <summary>
        /// API Unit Test: Test Web Service (GetReviews)
        /// </summary>
        [Test]
        public void TestGetReviews()
        {
            PublicService service = new PublicService();

            Assert.IsNull(service.GetReviews("0000000000000"));
            Assert.IsNotNull(service.GetReviews("9300675001113"));
        }

        /// <summary>
        /// API Unit Test: Test Web Service (AddReview)
        /// </summary>
        [Test]
        public void TestAddReview()
        {
            PublicService service = new PublicService();

            Assert.IsNotEmpty(service.AddReview("0000000000000", new Review() { FacebookID = "jeremywwl", ProductRating = 5, ProductReview = "Best Drink in Town" }));
            Assert.IsEmpty(service.AddReview("9300675001113", new Review() { FacebookID = "jeremywwl", ProductRating = 5, ProductReview = "Best Drink in Town" }));
        }
    }
}
