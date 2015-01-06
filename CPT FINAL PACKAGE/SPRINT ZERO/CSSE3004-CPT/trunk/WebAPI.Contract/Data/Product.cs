using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;

namespace CPT.WebAPI.Contract.Data
{
    /// <summary>
    /// This is the product model and defines the shape of the database table and the file
    /// </summary>
    [DataContract]
    public class Product
    {
        /// <summary>
        /// Primary Key for Product, Required Field. Get and Set Product ID
        /// </summary>
        [Key]
        [Required]
        [DataMember]
        public string ProductID { get; set; }

        /// <summary>
        /// Product Name, Required Field. Get and Set Product Name
        /// </summary>
        [Required]
        [DataMember]
        public string Name { get; set; }

        /// <summary>
        /// Product Nutrition Information, Required Field. Get and Set Nutrition Information
        /// </summary>
        [Required]
        [DataMember]
        public string Nutrition { get; set; }

        /// <summary>
        /// Product Allergen Information, Required Field. Get and Set Allergen Information
        /// </summary>
        [Required]
        [DataMember]
        public string Allergen { get; set; }

        /// <summary>
        /// Product Image, Required Field. Get and Set Product Image
        /// </summary>
        [Required]
        [DataMember]
        public string Image { get; set; }

        /// <summary>
        /// Foreign Key, Product Manufacturer ID, Required Field. Get and Set Manufacturer ID.
        /// </summary>
        [Required]
        [ForeignKey("ProductManufacturer")]
        public Guid ManufacturerID { get; set; }

        /// <summary>
        /// Product Manufacturer Information. Get and Set Product Manufacturer
        /// </summary>
        [DataMember]
        public virtual Manufacturer ProductManufacturer { get; set; }

        /// <summary>
        /// Product Review Information. Get and Set Reviews.
        /// </summary>
        public virtual ICollection<Review> Reviews { get; set; }
    }
}
