﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Entity;

namespace CPT.WebAPI.Contract.Data
{
    /// <summary>
    /// This is the database intializer class for debuging purposes only really, it allows us to add test data.
    /// </summary>
    public class Initializer : DropCreateDatabaseAlways<DataContext>
    {
        /// <summary>
        /// Add test data to the database here.
        /// </summary>
        /// <param name="context"></param>
        protected override void Seed(DataContext context)
        {
            #region Coca-Cola 1.25 Litres
            Manufacturer CocaColaManufacturer = new Manufacturer()
            {
                Name = "The Coca-Cola Company",
                Description = "The company produces concentrate, which is then sold to licensed Coca-Cola bottlers throughout the world.",
                Latitude = 33.907561,
                Longtitude = -84.460265
            };
            context.Manufacturers.Add(CocaColaManufacturer);
            context.SaveChanges();

            Product CocaCola125 = new Product()
            {
                ProductID = "9300675001113",
                Name = "Coca-Cola 1.25 Litres",
                Nutrition = "Energy=450kJ;Protein=0g;Fat. total=0g;Carbohydrates=27g;Sodium=25mg",
                Allergen = "None",
                Image = "http://i.walmartimages.com/i/p/00/04/90/00/05/0004900005537_500X500.jpg",
                ManufacturerID = CocaColaManufacturer.ManufacturerID,
                Reviews = new List<Review>() 
                { 
                    new Review()
                    {
                        FacebookID = "michellewong06",
                        ProductRating = 5,
                        ProductReview = "I love coke!"
                    },
                    new Review()
                    {
                        FacebookID = "1435368878",
                        ProductRating = 1,
                        ProductReview = "I hate coke!"
                    },
                    new Review()
                    {
                        FacebookID = "pukallus",
                        ProductRating = 3,
                        ProductReview = "I am indifferent towards coke."
                    },
                    new Review()
                    {
                        FacebookID = "jeremywwl",
                        ProductRating = 4,
                        ProductReview = "Coke is totally boss."
                    }
                }
            };
            context.Products.Add(CocaCola125);
            context.SaveChanges();
            #endregion

            #region Coca-Cola Vanilla 375 mL
            Product CocaCola375 = new Product()
            {
                ProductID = "9300675020923",
                Name = "Coca-Cola Vanilla 375 mL",
                Nutrition = "Energy=450kJ;Protein=0g;Fat. total=0g;Carbohydrates=27g;Sodium=25mg",
                Allergen = "None",
                Image = "http://2.bp.blogspot.com/_d49ZzF0Bwzw/THXd5URtWVI/AAAAAAAAAPk/VZSivX2PbAM/s1600/coke.jpg",
                ManufacturerID = CocaColaManufacturer.ManufacturerID,
                Reviews = new List<Review>() 
                { 
                    new Review()
                    {
                        FacebookID = "michellewong06",
                        ProductRating = 5,
                        ProductReview = "I love coke, but cans are small!"
                    },
                    new Review()
                    {
                        FacebookID = "1435368878",
                        ProductRating = 1,
                        ProductReview = "I hate coke!, although cans seal in the freshness better."
                    }
                }
            };
            context.Products.Add(CocaCola375);
            context.SaveChanges();
            #endregion

            #region Wild Turkey - American Honey
            Manufacturer WildTurkeyManufacturer = new Manufacturer()
            {
                Name = "Campari Group",
                Description = "Gruppo Campari is an Italian-based multinational producer of alcoholic and non-alcoholic beverages.",
                Latitude = 38.037297,
                Longtitude = -84.896617
            };
            context.Manufacturers.Add(WildTurkeyManufacturer);
            context.SaveChanges();

            Product WildTurkey = new Product()
            {
                ProductID = "08043256017",
                Name = "Wild Turkey - American Honey",
                Nutrition = "ALC./VOL=750mL 35.5%;Standard Drinks=21;",
                Allergen = "None",
                Image = "http://www.drinkhacker.com/wp-content/uploads/2008/08/wild-turkey-american-honey.jpg",
                ManufacturerID = WildTurkeyManufacturer.ManufacturerID,
                Reviews = new List<Review>() 
                { 
                    new Review()
                    {
                        FacebookID = "alex.hope.oconnor",
                        ProductRating = 5,
                        ProductReview = "Good stuff."
                    }
                }
            };
            context.Products.Add(WildTurkey);
            context.SaveChanges();
            #endregion

            base.Seed(context);
        }
    }
}
