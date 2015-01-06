using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Entity;

namespace CPT.WebAPI.Contract.Data
{
    /// <summary>
    /// The DataContext is the method for data access, the connection string will be configured here.
    /// </summary>
    public class DataContext : DbContext
    {
        /// <summary>
        /// Products Database Set. Get and Set Products.
        /// </summary>
        public DbSet<Product> Products { get; set; }

        /// <summary>
        /// Reviews Database Set. Get and Set Reviews.
        /// </summary>
        public DbSet<Review> Reviews { get; set; }

        /// <summary>
        /// Manufacturers Database Set. Get and Set Manufacturers.
        /// </summary>
        public DbSet<Manufacturer> Manufacturers { get; set; }
    }
}
