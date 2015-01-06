using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;

namespace CPT.WebAPI.Contract.Data
{
    /// <summary>
    /// Manufacturer model, defines the shape and file of the database
    /// </summary>
    [DataContract]
    public class Manufacturer
    {
        /// <summary>
        /// Primary Key for Manufacturer, Required Field. Get and Set Manufacturer ID.
        /// </summary>
        [Key]
        [Required]
        [DataMember]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid ManufacturerID { get; set; }

        /// <summary>
        /// Manufacturer Name, Required Field. Get and Set Manufacturer Name.
        /// </summary>
        [Required]
        [DataMember]
        public string Name { get; set; }

        /// <summary>
        /// Manufactuer Description, Required Field. Get and Set Manufactuer Description.
        /// </summary>
        [Required]
        [DataMember]
        public string Description { get; set; }

        /// <summary>
        /// Manufacturer Location (Latitude). Get and Set Manufacturer Location (Latitude)
        /// </summary>
        [DataMember]
        public double Latitude { get; set; }

        /// <summary>
        /// Manufacturer Location (Longitude). Get and Set Manufacturer Location (Longitude)
        /// </summary>
        [DataMember]
        public double Longtitude { get; set; }
    }
}
