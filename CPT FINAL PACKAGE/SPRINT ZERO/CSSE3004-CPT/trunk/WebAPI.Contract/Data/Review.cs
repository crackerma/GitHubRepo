using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;

namespace CPT.WebAPI.Contract.Data
{
    /// <summary>
    /// Review model, defines the shape and file of the database
    /// </summary>
    [DataContract]
    public class Review
    {
        /// <summary>
        /// Primary Key for Review, Required Field. Get and Set Review ID.
        /// </summary>
        [Key]
        [Required]
        [DataMember]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid ReviewID { get; set; }

        /// <summary>
        /// Facebook ID, Required Field. Get and Set Facebook ID.
        /// </summary>
        [Required]
        [DataMember]
        public string FacebookID { get; set; }

        /// <summary>
        /// Product Review, Required Field. Get and Set Product Review.
        /// </summary>
        [Required]
        [DataMember]
        public string ProductReview { get; set; }

        /// <summary>
        /// Product Rating, Required Field. Get and Set Product Rating.
        /// </summary>
        [Required]
        [DataMember]
        [Range(1, 5)]
        public int ProductRating { get; set; }
    }
}