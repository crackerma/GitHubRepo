using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using CPT.WebAPI.Contract.Data;
using System.Data.Entity;

namespace CPT.WebAPI.Contract
{
    /// <summary>
    /// Implements the service contract for the web service to execute.
    /// </summary>
    public class PublicService : IPublicService
    {
        /// <summary>
        /// Static constructor to set the intializer so the database is seeded correctly
        /// </summary>
        static PublicService()
        {
            
            Database.SetInitializer<DataContext>(new Initializer());
        }

        /// <summary>
        /// Method to parse a barcode to return a single Product Information
        /// </summary>
        /// <param name="barcode"></param>
        /// <returns>Single Product Information</returns>
        public Product GetProduct(string barcode)
        {
            DataContext Db = new DataContext();
            var p = Db.Products.Find(barcode);
            if (p == null)
                return null;

            return new Product()
            {
                ProductID = p.ProductID,
                Name = p.Name,
                Nutrition = p.Nutrition,
                Allergen = p.Allergen,
                Image = p.Image,
                ProductManufacturer = p.ProductManufacturer
            };
        }

        /// <summary>
        /// Method to parse an array of barcodes to return each barcode Product Information
        /// </summary>
        /// <param name="barcodes"></param>
        /// <returns>Each barcode Product Information</returns>
        public IEnumerable<Product> GetProducts(string[] barcodes)
        {
            DataContext Db = new DataContext();
            return Db.Products.ToList().Where(p => barcodes.Contains(p.ProductID)).Select(p => new Product()
            {
                ProductID = p.ProductID,
                Name = p.Name,
                Nutrition = p.Nutrition,
                Allergen = p.Allergen,
                Image = p.Image,
                ProductManufacturer = p.ProductManufacturer
            });
        }

        /// <summary>
        /// Method to parse a Product ID to return all Reviews on a Product
        /// </summary>
        /// <param name="productid"></param>
        /// <param name="review"></param>
        /// <returns>Product Reviews</returns>
        public IEnumerable<Review> GetReviews(string productid)
        {
            DataContext Db = new DataContext();
            var p = Db.Products.Find(productid);
            if (p == null)
                return null;

            return p.Reviews.Select(r => new Review()
            {
                FacebookID = r.FacebookID,
                ProductRating = r.ProductRating,
                ProductReview = r.ProductReview
            });
        }

        /// <summary>
        /// Method to add a Review on a Product
        /// </summary>
        /// <param name="productid"></param>
        /// <param name="review"></param>
        /// <returns></returns>
        public string AddReview(string productid, Review review)
        {
            DataContext Db = new DataContext();
            var p = Db.Products.Find(productid);
            if (p == null)
                return "Product not found in system, please try again later.";

            if (p.Reviews == null)
                p.Reviews = new List<Review>();
            p.Reviews.Add(review);
            Db.SaveChanges();

            return String.Empty;
        }
    }
}
