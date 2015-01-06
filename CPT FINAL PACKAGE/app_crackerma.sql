-- phpMyAdmin SQL Dump
-- version 3.3.8.1
-- http://www.phpmyadmin.net
--
-- 主机: w.rdc.sae.sina.com.cn:3307
-- 生成日期: 2012 年 10 月 18 日 15:41
-- 服务器版本: 5.5.23
-- PHP 版本: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `app_crackerma`
--

-- --------------------------------------------------------

--
-- 表的结构 `allergen`
--

CREATE TABLE IF NOT EXISTS `allergen` (
  `AllergenID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Allergen` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`AllergenID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- 转存表中的数据 `allergen`
--

INSERT INTO `allergen` (`AllergenID`, `Allergen`) VALUES
(1, 'Barley'),
(2, 'Wheat'),
(3, 'Milk'),
(4, 'Gluten'),
(5, 'Soy'),
(6, 'Benzoyl Peroxide'),
(7, 'Parfume'),
(8, 'Monosodium Glutamate'),
(9, 'Yeast'),
(10, 'Caffeine'),
(11, 'Sulphite'),
(13, 'Wheat Flour'),
(14, 'Synthetic polymer'),
(15, 'Beeswax'),
(17, 'Alcohol');

-- --------------------------------------------------------

--
-- 表的结构 `flagged`
--

CREATE TABLE IF NOT EXISTS `flagged` (
  `FlaggedID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` varchar(255) NOT NULL,
  `ManufacturerID` int(11) NOT NULL,
  `Message` varchar(500) NOT NULL,
  `Managed By` varchar(100) NOT NULL,
  PRIMARY KEY (`FlaggedID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- 转存表中的数据 `flagged`
--

INSERT INTO `flagged` (`FlaggedID`, `ProductID`, `ManufacturerID`, `Message`, `Managed By`) VALUES
(8, '', 1, 'Sustainability information should be changed. (Test)', ''),
(9, '9300605121577', 0, 'Description need to be change. (Test)', ''),
(14, '', 10, 'Test', ''),
(13, '9311493750015', 0, 'Test', '');

-- --------------------------------------------------------

--
-- 表的结构 `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `LocationID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductId` varchar(255) NOT NULL,
  `Name` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `Image` text,
  `Latitude` decimal(18,15) DEFAULT NULL,
  `Longitude` decimal(18,15) DEFAULT NULL,
  `NextLocationID` int(11) NOT NULL,
  PRIMARY KEY (`LocationID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- 转存表中的数据 `location`
--

INSERT INTO `location` (`LocationID`, `ProductId`, `Name`, `Description`, `Image`, `Latitude`, `Longitude`, `NextLocationID`) VALUES
(1, '9300605121577', 'Nestle Australia Ltd', 'Manufacturer Head Office.', 'http://crackerma-img.stor.sinaapp.com/1.jpg', -31.019566000000000, 152.943101000000000, 21),
(2, '9310495074112', 'Diageo Australia Ltd.', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/2.jpg', -33.843758000000000, 151.204075000000000, 23),
(3, '9310050000013', 'Clorox Australia Pty Ltd', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/3.jpg', -33.933676000000000, 151.036062000000000, 25),
(4, '9300650658516', 'Kraft Foods Limited', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/4.jpg', -37.824616000000000, 144.949276000000000, 26),
(5, '90162602', 'Red Bull Australasia', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/5.jpg', 47.791521000000000, 13.296908000000000, 30),
(6, '9300701011567', 'Reckitt Benckiser (Australia) Pty Limited', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/6.jpg', -33.811539000000000, 151.071891000000000, 31),
(7, '8888021203424', 'Energizer Holdings ', 'Manufacturer Head office ', 'http://crackerma-img.stor.sinaapp.com/7.jpg', -33.848118000000000, 151.072209000000000, 34),
(8, '9310059051030', 'Johnson & Johnson Pacific', 'Manufacturer Head Office ', 'http://crackerma-img.stor.sinaapp.com/8.jpg', -33.876970000000000, 151.195740000000000, 35),
(9, '9416500090433', 'Grove Mill Winery ', 'Vineyard', 'http://crackerma-img.stor.sinaapp.com/9.jpg', -41.516603000000000, 173.797583000000000, 0),
(10, '9300675001007', 'COCA-COLA AMATIL(AUST) Pty Ltd.', 'Manufacturing Plant ', 'http://crackerma-img.stor.sinaapp.com/10.jpg', -33.797997000000000, 150.985126000000000, 39),
(11, '9310055536494', 'KELLOGG (AUST.) PTY LTD', 'Manufacturer Head Office ', 'http://crackerma-img.stor.sinaapp.com/11.jpg', -33.942029000000000, 151.217376000000000, 41),
(12, '9300617325079', 'Cadbury Schweppes Chocolate Factory', 'Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/12.jpg', -37.830724000000000, 145.222427000000000, 43),
(13, '9310004111246', 'SCA Hygiene Australasia', 'Manufacturer Head Office ', 'http://crackerma-img.stor.sinaapp.com/13.jpg', -37.834463000000000, 145.132971000000000, 0),
(14, '9310988009140', 'Snacks Brands Australia', 'Manufacturer Head Office ', 'http://crackerma-img.stor.sinaapp.com/14.jpg', -33.733780000000000, 150.945987000000000, 46),
(15, '5000101845208', 'PZ Cussons Australia Pty Ltd', 'Manufacturer Head Office  ', 'http://crackerma-img.stor.sinaapp.com/15.jpg', -38.023690000000000, 145.203680000000000, 48),
(30, '0', 'Shayang Tianyi Medicine Industry Co. Ltd', 'Taurine Manufacturer ', 'http://crackerma-img.stor.sinaapp.com/30.jpg', 30.593090000000000, 114.305360000000000, 0),
(16, '4005900036599', 'Beiersdorf Australia Ltd', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/16.jpg', -33.779379000000000, 151.123463000000000, 50),
(25, '0', 'Qenos Pty Ltd', 'Polyethylene Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/25.jpg', -37.850849000000000, 144.812826000000000, 0),
(17, '9310263001067', 'Mentholatum Austrasia Pty Ltd', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/17.jpg', -37.900374000000000, 145.229727000000000, 52),
(18, '97855062420', 'Logitech Australia Computer Peripherals Pty Ltd', 'Manufacturer Head Office', 'http://crackerma-img.stor.sinaapp.com/18.jpg', -33.886288000000000, 151.073531000000000, 56),
(19, '9300624005377', 'COCA-COLA AMATIL(AUST) Pty Ltd', 'Manufacturing Plant ', 'http://crackerma-img.stor.sinaapp.com/19.jpg', -33.797997000000000, 150.985126000000000, 0),
(20, '9311493750015', 'Bundaberg Brewed Drinks', 'Manufacturing Plant & Head Office', 'http://crackerma-img.stor.sinaapp.com/20.jpg', -24.851764000000000, 152.381484000000000, 57),
(23, '0', 'Dan Murphys', 'Distributor Head Office ', 'http://crackerma-img.stor.sinaapp.com/23.jpg', -37.780320000000000, 145.033030000000000, 24),
(21, '0', 'Nestle Australia ', 'Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/21.jpg', -31.019566000000000, 152.943101000000000, 22),
(31, '0', 'Shanghai Sunhome (Group) Co.Ltd.', 'Packaging Manufacturer', 'http://crackerma-img.stor.sinaapp.com/31.jpg', 24.781680000000000, 118.552360000000000, 32),
(32, '0', 'Parchem Trading Ltd', 'Butylated hydroxy toluene Supplier', 'http://crackerma-img.stor.sinaapp.com/32.jpg', 40.905940000000000, -73.786700000000000, 33),
(33, '0', 'Tiger Chemical Company', 'Permethrin Supplier', 'http://crackerma-img.stor.sinaapp.com/33.jpg', -33.848118000000000, 151.072209000000000, 34),
(34, '0', 'East (Shenzhen) Technology Co. Ltd.', 'Lithium Manufacturer', 'http://crackerma-img.stor.sinaapp.com/34.jpg', 22.532990000000000, 113.930440000000000, 0),
(35, '0', 'Denstply Australia', 'Distributor', 'http://crackerma-img.stor.sinaapp.com/35.jpg', -37.896039000000000, 145.135872000000000, 36),
(36, '0', 'Chemical Distributors INC', 'Cineole Distributor', 'http://crackerma-img.stor.sinaapp.com/36.jpg', 42.880960000000000, -78.836400000000000, 37),
(24, '0', 'Mountainview Tree Nurseries', 'Lime/Citrus Nursery', 'http://crackerma-img.stor.sinaapp.com/24.jpg', -19.575840000000000, 147.405020000000000, 0),
(26, '0', 'Kraft Foods Limited', 'Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/26.jpg', -37.826548000000000, 144.923140000000000, 27),
(37, '0', 'Hunan Huacheng Biotech Inc.', 'Thymol Distributor', 'http://crackerma-img.stor.sinaapp.com/37.jpg', 28.228210000000000, 112.938810000000000, 38),
(38, '0', 'Hangzhou Ruijiang Chemical Co. Ltd', 'Cineole Distributor', 'http://crackerma-img.stor.sinaapp.com/38.jpg', 30.274090000000000, 120.155070000000000, 0),
(39, '0', 'NSW Sugar Milling Co-operative Ltd', 'Sugarcane Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/39.jpg', -29.012569000000000, 153.436729000000000, 40),
(40, '0', 'KF Specialty Ingredients Australia', 'Caramel Color Manufacturing Plant', 'http://crackerma-img.stor.sinaapp.com/40.jpg', -33.745539000000000, 150.914723000000000, 0),
(41, '0', 'Allied Mills', 'Wheat, Maize (Corn) Mill', 'http://crackerma-img.stor.sinaapp.com/41.jpg', -34.196292000000000, 150.645022000000000, 42),
(42, '0', 'Kellogg Aust Pty Ltd', 'Production Facility', 'http://crackerma-img.stor.sinaapp.com/42.jpg', -33.948583000000000, 151.209494000000000, 0),
(27, '0', 'Australian Saltworks', 'Salt Distributor', 'http://crackerma-img.stor.sinaapp.com/27.jpg', -35.297480000000000, 139.191810000000000, 28),
(28, '0', 'Angel Yeast Co. Ltd', 'Yeast Manufacturer', 'http://crackerma-img.stor.sinaapp.com/28.jpg', 30.675020000000000, 111.336970000000000, 29),
(29, '0', 'Bintani Australia Pty Ltd', 'Malt Extract Manufacturer', 'http://crackerma-img.stor.sinaapp.com/29.jpg', -37.987040000000000, 145.102450000000000, 0),
(22, '0', 'Murray Goulburn Co-Op', 'Milk Powder Collection Area', 'http://crackerma-img.stor.sinaapp.com/22.jpg', -38.468220000000000, 145.953100000000000, 0),
(43, '0', 'Mossman Central Mill Company', 'Sugar Mill', 'http://crackerma-img.stor.sinaapp.com/43.jpg', -16.459031000000000, 145.375264000000000, 44),
(44, '0', 'Dangote Flour Mills Plc ', 'Wheat Flour Manufacturer', 'http://crackerma-img.stor.sinaapp.com/44.jpg', 6.444650000000000, 3.363500000000000, 45),
(45, '0', 'Zhengzhou Haoshi Food Additives Co Ltd.', 'Emulsifiers Manufacturer', 'http://crackerma-img.stor.sinaapp.com/45.jpg', 34.724400000000000, 113.639960000000000, 0),
(46, '0', 'Otway Potato Growers Association', 'Potato Farm', 'http://crackerma-img.stor.sinaapp.com/46.jpg', -38.292092000000000, 143.555191000000000, 47),
(47, '0', 'Masterol Foods', 'Oil Supplier', 'http://crackerma-img.stor.sinaapp.com/47.jpg', -27.614280000000000, 152.930560000000000, 0),
(48, '0', 'Boom Agro India Private Limited', 'Mint Leaves Supplier', 'http://crackerma-img.stor.sinaapp.com/48.jpg', 26.955480000000000, 88.021080000000000, 49),
(49, '0', 'Shanghai Polymet Commodities Ltd', 'Sodium Laureth Sulfate Manufacturer', 'http://crackerma-img.stor.sinaapp.com/49.jpg', 31.048770000000000, 121.427680000000000, 0),
(50, '0', 'FM Fragrance Group Australia', 'Parfum Supplier', 'http://crackerma-img.stor.sinaapp.com/50.jpg', -33.796288000000000, 150.939252000000000, 51),
(51, '0', 'Fuzhou Farwell Import & Export Co. Ltd', 'Limonene Supplier', 'http://crackerma-img.stor.sinaapp.com/51.jpg', 26.091080000000000, 119.300410000000000, 52),
(52, '0', 'Sudha Menthol Company', 'Menthol Manufacturer', 'http://crackerma-img.stor.sinaapp.com/52.jpg', 28.658050000000000, 77.220750000000000, 53),
(53, '0', 'Zhengzhou Yi Bang Industry Co. Ltd.', 'Butylene Glycol Manufacturer ', 'http://crackerma-img.stor.sinaapp.com/53.jpg', 34.792170000000000, 113.385230000000000, 54),
(54, '0', 'Hefei TNJ Chemical Industry Co. Ltd. ', 'Linalool Manufacturer', 'http://crackerma-img.stor.sinaapp.com/54.jpg', 31.887700000000000, 117.469340000000000, 55),
(55, '0', 'Hangzhou Haichem  Co.  Ltd. ', 'Phenoxyethanol Supplier', 'http://crackerma-img.stor.sinaapp.com/55.jpg', 30.274090000000000, 120.155070000000000, 0),
(56, '0', 'ASI Solutions', 'Distributor', 'http://crackerma-img.stor.sinaapp.com/56.jpg', -33.938560000000000, 151.197800000000000, 0),
(57, '0', 'Canegrowers', 'Cane Sugar Supplier', 'http://crackerma-img.stor.sinaapp.com/57.jpg', -20.402650000000000, 148.583560000000000, 0);

-- --------------------------------------------------------

--
-- 表的结构 `manufacturer`
--

CREATE TABLE IF NOT EXISTS `manufacturer` (
  `ManufacturerID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(64) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `Sustainability` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ManufacturerID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- 转存表中的数据 `manufacturer`
--

INSERT INTO `manufacturer` (`ManufacturerID`, `Name`, `Description`, `Sustainability`) VALUES
(1, 'Nestle MILO', 'MILO was developed in the 1930s during the depression as a direct response to the fact that children were not receiving enough nutrients from their daily diet. Thomas Mayne, a Nestle Engineer, created the nutritious and delicious beverage using local milk knowledge and Swiss cocoa expertise. He name', 'Environmental sustainability commitment. Sustainable use of water and continuous improvement in water management commitment.'),
(3, 'Clorox Australia Pty Ltd', 'Clorox Australia Pty Ltd a subsidiary of Clorox USA and is one of many well-known quality brands owned and manufactured by or on behalf this company. Other brands include OSO, CHUX, ArmorAll, STP, ASTRA and Clorox.', '2001 National Packaging Covenant Action Plan. 2005 new Packaging Covenant will begin with a national recycling target of 65% by 2010. 2005 Clorox won the Gold award for their 2004 action plan report a'),
(4, 'Kraft Foods Limited', 'Kraft Foods is a global leader in snacks with an unrivalled portfolio of brands people love. Proudly marketing delicious biscuits, confectionery, beverages, cheese, grocery products and convenient meals in approximately 170 countries.', 'Australia Packaging Covenant - Kraft Foods Australia Packaging Covenant Action Plan 2011-2015. Energy Efficiency Opportunities - Participates in the Australian Government''s Energy Efficiency Opportuni'),
(5, 'Red Bull', 'Red Bull has been giving wings from the beginning and has started to spread its own wings around the world quickly.  While the consumption was doubling year on year in Austria, Red Bull arrived in its first foreign markets, Singapore (1989) and Hungary (1992). The authorization for Germany was grant', 'Red Bull is working hard to shrink its carbon footprint, now and in the future, via a series of energy and resource-saving measures. Uses Wall-to-wall production which dramatically shortens transport '),
(6, 'Reckitt Benckiser (Australia) Pty Limited', 'Reckitt Benckiser is a British multinational consumer goods company headquartered in Slough, United Kingdom. It is the world''s largest producer of household cleaning products and a major producer of consumer healthcare and personal products.', 'OTC Magazine Awards, Best Overall Company at SMaRT awards, 2011 Excellence in Corporate Practice Award, 2011 Digital Impact Awards, Gold at the Marketing Design Awards, Britain''s Most Admired Companie'),
(7, 'Energizer Holdings ', 'An American manufacturer of batteries and personal care products, headquartered in Town and Country, Missouri. Its most well known brands are Energizer and Eveready batteries, Schick, Wilkinson Sword and Edge shaving products, Playtex feminine hygiene and baby products, and Hawaiian Tropic and Banan', 'Environmental Sustainability- Work and partnerships with employees, customers and communities in programs that provide environmentally sustainable products or endorsements like Energy Star®, SmartWay®'),
(8, 'Johnson & Johnson Pacific', 'Multi-national manufacturers of pharmaceutical, diagnostic, therapeutic, surgical, and biotechnology products, as well as personal hygiene products', 'The world’s sixth-largest consumer health company'),
(9, 'New Zealand Wine Company Limited - Grove Mill Winery', 'The Grove Mill story begins in 1988, when the Marlborough region was first beginning to establish its reputation for wines of unparalleled intensity and purity.', 'World''s FIRST carbon neutral certified winery, 2006. New Zealand Government''s Green Ribbon Award, 2010. Sustainable Winegrowing New Zealand'),
(10, 'COCA-COLA AMATIL(AUST) Pty Ltd.', 'CCA is one of the largest bottlers of non-alcoholic ready-to-drink beverages in the Asia-Pacific region and one of the world’s top five Coca-Cola bottlers. CCA employs more than 15,000 people and has access to more than 265 million consumers through over 700,000 active customers.', 'CCA Australian Packaging Covenant 2011-2016 Action Plan'),
(11, 'KELLOGG (AUST.) PTY LTD', 'Kellogg has been providing food to the Australian community since 1924 when production began out of rented premises in Chippendale. By 1928 Corn Flakes proved so successful in the market that a new plant was built at Botany.', 'Proud supporter of Foodbank Australia. Long and proud association with Australian sport. Kellogg’s® Breakfast Buddies® - A community initiative providing kids the opportunity to eat a sensible breakfa'),
(12, 'Cadbury Pty Ltd', 'Cadbury is a brand with a long history in Australia and a passionate commitment to making everyone feel happy. ', 'Fairtrade Certified™. Focuses on education and enterprise, health and welfare and environmental sustainability.'),
(13, 'SCA Hygiene Australasia', 'Leading manufacturer of tissue and personal care hygiene products. Leading manufacturer of tissue and personal care hygiene products SCA locally manufacture a range of products including toilet and facial tissue, feminine hygiene products, paper towel, disposable tableware and incontinence products.', 'SCA’s commitment to sustainability: Identify, assess and manage risks to our employees, contractors, the environment and communities; Strive to achieve leading industry best practice; Reduce environme'),
(14, 'Snack Brands Australia', 'Snack Brands Australia make Australians favourite snacks.  100% Australian Made.', 'Australian Packaging Covenant Plan 2011-2013'),
(15, 'PZ Cussons Australia Pty Ltd', 'PZ Cussons operates in Australia and New Zealand, and is part of the PZ Cussons Group. We are a leading organisation within the Fast Moving Consumer Goods Industry with recognised quality brands such as Radiant, Morning Fresh, Duo, Trix, Imperial Leather, Original Source, Reflect and Down to Earth.', 'Various recycling campaigns'),
(16, 'Nivea', 'Latest face care, body care, sun care and skin care products brand. Nivea has been caring for skin for more than 100 years.  ', 'Readers Digest Trusted Brand 2011'),
(17, 'Mentholatum Austrasia Pty Ltd.', 'Manufactures and markets products within the following categories: topical analgesics, skin care, laxatives and anthelminthics.', 'Consumer Healthcare Australian Packaging Covenant Action Plan'),
(18, 'Logitech', 'A Swiss company focused on innovation and quality, Logitech designs personal peripherals to help people enjoy a better experience with the digital world.', 'CES Innovations 2012 Honorees. 2011 American Technology Award Winner - Consumer Electronics category'),
(19, 'Coca Cola Amatil', 'CCA is one of the largest bottlers of non-alcoholic ready-to-drink beverages in the Asia-Pacific region and one of the world’s top five Coca-Cola bottlers. CCA employs more than 15,000 people and has access to more than 265 million consumers through over 700,000 active customers.', 'CCA Australian Packaging Covenant 2011-2016 Action Plan'),
(20, 'Bundaberg Brewed Drinks Pty Ltd', 'Bundaberg Brewed Drinks is a family owned business based in Bundaberg Queensland', 'Australian Packaging Covenant 5 Year Action Plan 2010-2015. Partnered with CO2 Australia to help reduce and offset Australia’s carbon footprint');

-- --------------------------------------------------------

--
-- 表的结构 `manufacturerreview`
--

CREATE TABLE IF NOT EXISTS `manufacturerreview` (
  `ManufacturerID` int(100) unsigned DEFAULT NULL,
  `FacebookID` bigint(20) unsigned NOT NULL,
  `Review` varchar(500) NOT NULL,
  `Rating` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `manufacturerreview`
--

INSERT INTO `manufacturerreview` (`ManufacturerID`, `FacebookID`, `Review`, `Rating`) VALUES
(1, 12, 'Bad', 3),
(1, 13, 'Good', 5),
(4, 13, 'good', 3),
(4, 13, 'good', 3),
(4, 13, 'good', 4);

-- --------------------------------------------------------

--
-- 表的结构 `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ProductID` varchar(255) NOT NULL,
  `Name` varchar(64) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `Image` text,
  `Sustainability` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `Energy` decimal(5,2) DEFAULT NULL,
  `Water` decimal(5,2) DEFAULT NULL,
  `CO2` decimal(5,2) DEFAULT NULL,
  `ManufacturerID` int(11) NOT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `product`
--

INSERT INTO `product` (`ProductID`, `Name`, `Description`, `Image`, `Sustainability`, `Energy`, `Water`, `CO2`, `ManufacturerID`) VALUES
('9300605121577', 'Nestle Milo 450g', 'Milo is a malted barley powder which is mixed with hot or cold water and/or milk to produce a beverage. MILO contains: Calcium & Magnesium for strong bones, Iron that carries oxygen around the body, B vitamins (B1 and B2) that help the body use energy, Vitamin C which assists iron absorption, Vitamin A to support healthy eyesight.Contains MILK POWDER, BARLEY MALT EXTRACT, SUGAR, COCOA, SODIUM PHOSPHATE, DICALCIUM PHOSPHATE, SOY LECITHIN, VITAMIN A PALMITATE, THIAMINE HYDRICHLORIDE, FERROUS FUMARATE, ARTIFICIAL FLAVOUR.', 'http://crackerma-img.stor.sinaapp.com/P1.jpg', 'The Glycemic Index (GI) Symbol Program Label', 80.00, 10.00, 40.00, 1),
('9300675001007', 'Coca Cola 2L', 'Coca-Cola is a carbonated soft drink . Contains Carbonated Water, Cane Sugar, Caramel Colour, Phosphoric Acid, Natural Flavours, Caffeine', 'http://crackerma-img.stor.sinaapp.com/P10.jpg', 'Recyclable Packaging', 90.00, 80.00, 45.00, 10),
('9310055536494', 'Kellogg’s® All-Bran® Original 530g', 'Kellogg’s® All-Bran® Original is a crunchy wheat bran cereal that''s very high in dietary fibre. Fibre is needed to help maintain a healthy digestive system and insoluble fibre in particular, helps with regularity. Ingredients: Wheat bran,sugar, barley malt extract, salt, vitamins (riboflavin, folate, thiamin). ', 'http://crackerma-img.stor.sinaapp.com/P11.jpg', 'Kosher Certified. Halal Certified. National Heart Foundation Approved.', 70.00, 55.00, 30.00, 11),
('9300617325079', 'Time Out® Bar 40g', 'Two Wafer Fingers with a Cadbury® FlakeTM Centre covered in Cadbury® Dairy Milk Milk Chocolate. Ingredients: Sugar, Milk Solids, Cocoa Butter, Cocoa Mass, Wheat Flour (Contains Maize Starch, Salt), Vegetable Fat, Cocoa Powder, Emulsifiers, Water, Flavours, Raising Agent.  Milk Chocolate contains Cocoa Solids, Milk Solids', 'http://crackerma-img.stor.sinaapp.com/P12.jpg', 'Halal Certified', 40.00, 60.00, 10.00, 12),
('9310004111246', 'Sorbent Hypo Allergenic 12 Pk', 'Sorbent Hypo Allergenic is a toilet tissue is unscented and free from inks and dyes. It is suitable for sensitive skin.', 'http://crackerma-img.stor.sinaapp.com/P13.jpg', 'Clinically tested by the Skin and Cancer Foundation Australia.', 65.00, 60.00, 60.00, 13),
('9310988009140', 'French Fries 6 Pk 111g', 'All-time favourite French Fries. Contains POTATOES, VEGETABLES, OIL, SALT.', 'http://crackerma-img.stor.sinaapp.com/P14.jpg', 'Recyclable Packaging', 60.00, 50.00, 25.00, 14),
('5000101845208', 'Original Source Mint & Tea Tree Shower Gel 250mL', 'Shower gel stuffed with 7927 mint leaves. Leaves skin with a tingling sensation. Made of 100% pure and natural Mint and Tea Tree essential oils. Contains Aqua, Sodium Laureth Sulfate, Cocamide DEA, Mentha Arvensis (Mint) Leaf Oil, Melaleuca Alternifolia (Tea Tree) Leaf Oil, Glycerin, Sodium Chloride, Sodium Lactate, PEG-150 Distearate, Lactic Acid, Magnesium Chloride, Magnesium Nitrate, Limonene.', 'http://crackerma-img.stor.sinaapp.com/P15.jpg', 'Recyclable bottles packaging.Not tested on animals ', 25.00, 25.00, 30.00, 15),
('4005900036599', 'Nivea Deodorant for Men Black and White Invisible Power Roll On ', 'First antiperspirant deodorant that protects against yellow stains on white and white marks on black clothes, while you are protected for 48 hours from perspiration and odour. Ingredients: Aqua, Aluminum Chlorohydrate, Isoceteth-20, Paraffinum Liquidum, Butylene Glycol, Glyceryl Isostearate, Laureth-7 Citrate, Parfum, Palmitamidopropyltrimonium Chloride, Propylene Glycol, PEG-150 Distearate, Linalool, Limonene, Geraniol.', 'http://crackerma-img.stor.sinaapp.com/P16.jpg', '2012 Consumer Survey of Product Innovation Product of the Year Award', 30.00, 40.00, 65.00, 16),
('9310263001067', 'OXY emergency zit blitz pads 50', 'OXY Zit Blitz Pads have shown to be effective in as little as four hours for reducing the size and redness of spots. Contains  Aqua, alcohol denat, butylene glycol, glycerin, lactic acid, Laureth-11 carboxylic acid, PEG 6, Menthol, Bisbolol, Parfum, Hydrolysed algin, Disodium EDTA, Limonene, Linalool, Phenoxyethanol, Zinc sulfate, butyphenyl methylpropional, zingiber officinale extract.', 'http://crackerma-img.stor.sinaapp.com/P17.jpg', '2011 Health Product of the Year Award', 60.00, 50.00, 70.00, 17),
('97855062420', 'Logitech Rechargeable Speaker S315i Pink', 'IPod dock that can play up to 20 hours of music from your iPod® or iPhone™—at home or on the go. Compatible with Ipod, Ipod Touch, Ipod Nano, Ipod Classic.Supports other portable music players via 3.5 mm auxiliary input. Technical Specifications: Dimensions (H x W x D):10.8 inches (27.4 cm) x 7.77 inches (13.1 cm) x 1.8 inches (4.7 cm) Weight:1.47 pounds (670 grams) Cable length: 58 inches (1.5 m) Warranty Information:2-year limited hardware warranty', 'http://crackerma-img.stor.sinaapp.com/P18.jpg', 'IF Product Design Award 2010. Red dot product Design Award 2010', 80.00, 40.00, 80.00, 18),
('9300624005377 ', 'Mount Franklin Easy-Crush Bottle 600mL', 'The lightest 600mL water bottle produced in Australia. Weighing just 12.8grams. The ‘Mount Franklin Easy-Crush Bottle’ is made possible by a multi-million investment in new technology which has enabled us to make lighter-weight beverage bottles, right across our bottling facilities nationwide. ', 'http://crackerma-img.stor.sinaapp.com/P19.jpg', 'Awarded Gold in the Australian Packaging Covenant Sustainability Award category of the Australian Packaging Design Awards in 2011', 20.00, 20.00, 10.00, 19),
('9310495074112', 'Smirnoff Long Island Iced Tea 700 mL', 'An authentic blend of Smirnoff No 21, the world’s number one premium vodka, white rum, gin, and triple sec mixed with a splash of lime and cola. Perfectly blended to an authentic recipe. Alcohol by volume: 15.0%', 'http://crackerma-img.stor.sinaapp.com/P2.jpg', 'Recyclable Packaging', 90.00, 90.00, 50.00, 2),
('9311493750015', 'Bundaberg Ginger Beer 750ML', 'It''s always cloudy in a bottle of good, old fashioned ginger beer. Hold the bottle up to the light and you''ll see it''s full of real ginger pieces, brewed to a genuine old recipe to release the natural flavours of ginger. Bundaberg Ginger Beer contains no artificial colours or flavours delivering a superior taste in every bottle. Ingredients: Cane Sugar, Yeast, Ginger', 'http://crackerma-img.stor.sinaapp.com/P20.jpg', 'Free of Allergens as defined by the Australian and New Zealand Food Standards Code', 50.00, 90.00, 60.00, 20),
('9310050000013', 'GLAD Wrap 15x33', 'Premium quality polyethylene food wrap that seals in freshness and flavour. Made of polyethylene.', 'http://crackerma-img.stor.sinaapp.com/P3.jpg', 'Recyclable Packaging', 70.00, 60.00, 50.00, 3),
('9300650658516', 'Vegemite 400g', 'Vegemite is a dark brown Australian food paste made from yeast extract. Ingredients: Yeast extract, salt, malt extract, colour (caramel), vegetable flavours, vitamins (niacin, thiamine, riboflavin) ', 'http://crackerma-img.stor.sinaapp.com/P4.jpg', 'Halal Certified. Kosher Certified.  ', 60.00, 50.00, 80.00, 4),
('90162602', 'Red Bull Energy Drink 355mL', 'Red Bull Energy Drink is a functional beverage. It  vitalizes body and mind. Red Bull Energy Drink has been developed for people who want to have a clear and focused mind, perform physically, are dynamic and performance-oriented whilst also balancing this with a fun and active lifestyle. Ingredients: Taurine, Caffeine , Glucuronolactone , Inositol, Niacinamide, Pantothenic Acid , Vitamin B6 , Vitamin B12, sucrose, Alpine Spring Water ', 'http://crackerma-img.stor.sinaapp.com/P5.jpg', '2006 Best Energy Drink Award', 90.00, 90.00, 60.00, 5),
('9300701011567', 'Mortein Auto Indoor Insect Control System', 'The Naturgard Automatic Outdoor Insect Control System provides outdoor protection against flies and mosquitoes when used continuously.Ingredients: Hydrocarbon Propellent, Ethanol, Permethrin, Transfluthrin, Perfume, D-Limonene, Butylated hydroxy toulene', 'http://crackerma-img.stor.sinaapp.com/P6.jpg', '2010 Consumer Survey of Product Innovation Product of the Year Award', 90.00, 40.00, 50.00, 6),
('8888021203424', 'Energizer® Ultimate Lithium AA 4 Pk', 'Energizer® Ultimate Lithium batteries deliver long-lasting power to keep up with today’s high-tech, power hungry devices like digital cameras, photo flash units and handheld GPS devices.', 'http://crackerma-img.stor.sinaapp.com/P7.jpg', '100% Recyclable', 95.00, 10.00, 60.00, 7),
('9310059051030', 'Listerine Tartar Control 1 Litre', 'Antiseptic Mouthwash that fights tartar build up. Kills germs that cause bad breathe, plaque and gingivitis. Contains Zinc Chloride, Ethanol, Benzoic acid, Thymol, Cineole', 'http://crackerma-img.stor.sinaapp.com/P8.jpg', 'Australian Dental Association Seal of Approval', 20.00, 80.00, 40.00, 8),
('9416500090433', 'Mobius Marlborough Sauvignon Blanc 2009', 'Grapes grown on no fewer than 24 vineyards complete the Mobius Sauvignon Blanc. It shows some complexity and the ripe fruit characteristics of the Wairau Valley fruit it is made from.', 'http://crackerma-img.stor.sinaapp.com/P9.jpg', 'Planet Ark Carbon Reduction Label', 20.00, 40.00, 25.00, 9);

-- --------------------------------------------------------

--
-- 表的结构 `productallergen`
--

CREATE TABLE IF NOT EXISTS `productallergen` (
  `allergenid` int(11) NOT NULL,
  `productid` varchar(255) NOT NULL,
  PRIMARY KEY (`allergenid`,`productid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `productallergen`
--

INSERT INTO `productallergen` (`allergenid`, `productid`) VALUES
(0, '0'),
(1, '9300605121577'),
(1, '9300675001007'),
(2, '9300605121577'),
(3, '9300605121577'),
(4, '9300605121577'),
(4, '9300675001007'),
(5, '9300617325079'),
(5, '9300675001007'),
(6, '2'),
(7, '2'),
(8, '4'),
(8, '9300605121577'),
(9, '4'),
(10, '5'),
(10, '9300605121577'),
(10, '9300617325079'),
(11, '9'),
(11, '9300605121577'),
(13, '12'),
(13, '9300605121577'),
(14, '14'),
(15, '17');

-- --------------------------------------------------------

--
-- 表的结构 `productreview`
--

CREATE TABLE IF NOT EXISTS `productreview` (
  `ProductID` varchar(255) NOT NULL,
  `FacebookID` varchar(255) NOT NULL,
  `Review` varchar(500) NOT NULL,
  `Rating` tinyint(4) NOT NULL,
  PRIMARY KEY (`ProductID`,`FacebookID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `productreview`
--

INSERT INTO `productreview` (`ProductID`, `FacebookID`, `Review`, `Rating`) VALUES
('9300617325079', '12', 'awesome', 4),
('9300617325079', '2147483647', 'love it', 3),
('9300617325079', '100001372149695', 'awesome', 3),
('9310263001067', '100000035997428', 'Testing app', 4),
('90162602', '1255680127', 'good', 0),
('9300617325079', '100002362647345', 'nice', 4),
('9310263001067', '100002362647345', 'test', 4);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8 NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `Description` varchar(200) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(64) CHARACTER SET utf8 NOT NULL,
  `Permissions` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`UserID`, `Username`, `Password`, `Name`, `Description`, `Email`, `Permissions`) VALUES
(1, 'root', 'root', 'Root user', 'Root user of the CPT database ', 'unknown@cpt.com', '0'),
(2, 'james', 'james', 'James', 'Account for James Tsai', 'james@cpt.com', '1'),
(4, 'sera', 'sera', 'sera', 'tt', 'serapicseraphim@hotmail.com', '1');
