using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using CPT.WebAPI.Contract.Data;

namespace CPT.WebAPI.Contract
{
    /// <summary>
    /// Interface that defines all Public API Functions
    /// </summary>
    [ServiceContract]
    public interface IPublicService
    {
        /// <summary>
        /// Method to parse a barcode to return a single Product Information
        /// </summary>
        /// <param name="barcode"></param>
        /// <returns>Single Product Information</returns>
        [OperationContract]
        Product GetProduct(string barcode);

        /// <summary>
        /// Method to parse an array of barcodes to return each barcode Product Information
        /// </summary>
        /// <param name="barcodes"></param>
        /// <returns>Each barcode Product Information</returns>
        [OperationContract]
        IEnumerable<Product> GetProducts(string[] barcodes);

        /// <summary>
        /// Method to parse a Product ID to return each Product ID Review
        /// </summary>
        /// <param name="productid"></param>
        /// <returns>Each Product ID Review</returns>
        [OperationContract]
        IEnumerable<Review> GetReviews(string productid);

        /// <summary>
        /// Method to Add a Review on a Product
        /// </summary>
        /// <param name="productid"></param>
        /// <param name="review"></param>
        /// <returns></returns>
        [OperationContract]
        string AddReview(string productid, Review review);
    }
}
