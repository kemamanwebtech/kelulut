-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 144.217.93.255    Database: db_kelulut
-- ------------------------------------------------------
-- Server version	5.5.54-0+deb8u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `genus_picture`
--

DROP TABLE IF EXISTS `genus_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genus_picture` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `genus_name` varchar(30) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genus_picture`
--

LOCK TABLES `genus_picture` WRITE;
/*!40000 ALTER TABLE `genus_picture` DISABLE KEYS */;
INSERT INTO `genus_picture` VALUES (1,'Frie','Frieseomelitta.jpg'),(2,'Lestri','Lestrimelitta.jpg'),(3,'Meli','Melipona.jpg'),(4,'Pleb','\" \"'),(5,'Tetra','Tetragonisca'),(6,'Trigo','Trigona.jpg');
/*!40000 ALTER TABLE `genus_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `info_kelulut`
--

DROP TABLE IF EXISTS `info_kelulut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `info_kelulut` (
  `genus_name` varchar(200) DEFAULT NULL,
  `about` varchar(3000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `info_kelulut`
--

LOCK TABLES `info_kelulut` WRITE;
/*!40000 ALTER TABLE `info_kelulut` DISABLE KEYS */;
INSERT INTO `info_kelulut` VALUES ('Frieseomelitta (Ihering, 1912)','Frieseomelitta is a stingless bee (Meliponini) genus in the family Apidae. It currently contains about 16 described species:'),('Lestrimelitta (Friese, 1903)','Lestrimelitta is a genus of stingless bees found in the Neotropics, from Mexico to Brazil and Argentina, with 19 known species. They are small, shining black species from 4 to 7 mm in length, with rounded heads and reduced pollen baskets. Unlike most eusocial bees, they do not gather their own pollen and nectar from flowers, thus are not pollinators, but instead they invade the colonies of other stingless bee species and rob their pollen and honey stores (a phenomenon called \"cleptobiosis\"). They do not initiate their own nests, but they will \"evict\" another stingless bee colony from its nest (usually in a tree cavity), and convert the pre-existing nest to house their own colony.'),('Melipona (Illiger, 1806)','Melipona is a genus of stingless bees, widespread in warm areas of the Neotropics, from Sinaloa and Tamaulipas (México) to Tucumán and Misiones (Argentina). At least 40 species are known. The largest producer of honey from Melipona bees in Mexico is in the state of Yucatán where bees are studied at an interactive park called \"Bee Planet\" which is within the Cuxtal Ecological Reserve. Several species are kept for honey production, such as in Brazil, where some are well-known enough to have common names. Melipona honey has long been used by humans and now is of minor commercial importance. Research is going on in improved beekeeping techniques.'),('Plebeia (Schwarz, 1938)','Plebeia is a small genus of stingless bees, formerly including in the genus Trigona. Most of the 35 species are placed in the subgenus (Plebeia) (s.s.), but there also are four species in the subgenus (Scaura). They differ in only minor structural details, primarily of the hind leg, from other genera that were formerly treated as constituents of Trigona. Since its separation from the genus Trigona, the genus Schwarziana, as in the species Schwarziana quadripunctata, has also been separated into a new and distinct category after the 1943 work of Padre J.S. Moure. Today, he is occasionally referred to as \"the Father of Brazilian bee taxonomy.'),('Tetragonisca (Latreille, 1811)','Tetragonisca is a small eusocial stingless bee found in Central and South America. It is known by a variety of names in different regions (e.g. jataí, yatei, jaty, virginitas, angelitas ingleses, españolita, mariola, chipisas, virgencitas, and mariolitas). A closely related species, Tetragonisca fiebrigi, occupies different areas in South America and has a slightly different coloration. It is a very small bee and builds unobtrusive nests, allowing it to thrive in urban areas. It also produces large amounts of honey, and is thus frequently kept in wooden hives by beekeepers. Their hives are often overlooked, and since the bee lacks a stinger, it is not seen as a threat to humans. Many of their behaviors are concerned with colonizing a new nest and producing offspring, demonstrated by their swarming and nursing behaviors, however a special caste of it are soldiers who are slightly larger than the workers. The soldiers in the nest are very good at protecting the hive against intruders which makes up for not having a stinger. Some of these soldiers hover in mid air outside the nest, which is seen in the adjacent picture.'),('Trigona (Jurine, 1807)','Trigona is the largest genus of stingless bees, formerly including many more subgenera than the present assemblage; many of these former subgenera have been elevated to generic status. There are approximately 150 species presently included in the genus, in 11 subgenera. They differ from those groups now excluded in only minor structural details, primarily of the hind leg. Trigona species occur throughout the Neotropical region and also throughout the Indo-Australian region; as presently defined, no members of the genus occur in Africa.');
/*!40000 ALTER TABLE `info_kelulut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kelulut_map`
--

DROP TABLE IF EXISTS `kelulut_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kelulut_map` (
  `name` varchar(30) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `uploaded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kelulut_map`
--

LOCK TABLES `kelulut_map` WRITE;
/*!40000 ALTER TABLE `kelulut_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `kelulut_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kelulut_user`
--

DROP TABLE IF EXISTS `kelulut_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kelulut_user` (
  `name` varchar(30) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL,
  `passwd` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kelulut_user`
--

LOCK TABLES `kelulut_user` WRITE;
/*!40000 ALTER TABLE `kelulut_user` DISABLE KEYS */;
INSERT INTO `kelulut_user` VALUES ('raf','a@gmail.com','a'),('a','a','a'),('b','b','b'),('c','c','c');
/*!40000 ALTER TABLE `kelulut_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `question` varchar(300) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `answer1` varchar(30) DEFAULT NULL,
  `answer2` varchar(30) DEFAULT NULL,
  `answer3` varchar(30) DEFAULT NULL,
  `answer4` varchar(30) DEFAULT NULL,
  `correct_answer` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `species` (
  `genus_name` varchar(30) DEFAULT NULL,
  `species` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES ('Frie','Frieseomelitta dispar'),('Frie','Frieseomelitta doederleini'),('Frie','Frieseomelitta flavicornis'),('Frie','Frieseomelitta francoi'),('Frie','Frieseomelitta freiremaiai'),('Frie','Frieseomelitta languida'),('Frie','Frieseomelitta lehmanni'),('Frie','Frieseomelitta longipes'),('Frie','Frieseomelitta meadewaldoi'),('Frie','Frieseomelitta nigra'),('Frie','Frieseomelitta paranigra'),('Frie','Frieseomelitta paupera'),('Frie','Frieseomelitta portoi'),('Frie','Frieseomelitta silvestrii'),('Frie','Frieseomelitta trichocerata'),('Frie','Frieseomelitta varia'),('Lestri','Lestrimelitta catira(Gonzalez and Griswold, 2012)'),('Lestri','Lestrimelitta chacoana(Roig-Alsina, 2010)'),('Lestri','Lestrimelitta chamelenis(Ayala, 1999)'),('Lestri','Lestrimelitta ciliata(Marchi and Melo, 2006)'),('Lestri','Lestrimelitta danuncia(Oliveira and Marchi, 2005)'),('Lestri','Lestrimelitta ehrhardti(Friese, 1931)'),('Lestri','Lestrimelitta glaberrima(Oliveira and Marchi, 2005)'),('Lestri','Lestrimelitta glabrata(Camargo and Moure, 1990)'),('Lestri','Lestrimelitta guyanensis(Roubik, 1980)'),('Lestri','Lestrimelitta huilensis(Gonzalez and Griswold, 2012)'),('Lestri','Lestrimelitta limao (Smith, 1863) –irati'),('Lestri','Lestrimelitta maracaia(Marchi and Melo, 2006)'),('Lestri','Lestrimelitta monodonta( Camargo and Moure, 1990)'),('Lestri','Lestrimelitta mourei(Oliveira and Marchi, 2005)'),('Lestri','Lestrimelitta nana(Melo, 2003)'),('Lestri','Lestrimelitta niitkib(Ayala, 1999)'),('Lestri','Lestrimelitta opita(Gonzalez and Griswold, 2012)'),('Lestri','Lestrimelitta rufa(Friese, 1903)'),('Lestri','Lestrimelitta rufipes(Friese, 1903)'),('Lestri','Lestrimelitta similis(Marchi and Melo, 2006)'),('Lestri','Lestrimelitta spinosa( Marchi and Melo, 2006)'),('Lestri','Lestrimelitta sulina( Marchi and Melo, 2006)'),('Lestri','Lestrimelitta tropica( Marchi and Melo, 2006)'),('Meli','Melipona beecheii Bennett'),('Meli','Melipona bicolor (Lepeletier, 1836) – guarupu, guaraipo'),('Meli','Melipona capixaba (Moure & Camargo, 1994)'),('Meli','Melipona compressipes – tiúba'),('Meli','Melipona compressipes manaosensis – jupará'),('Meli','Melipona fasciata Latreille, 2008'),('Meli','Melipona fuscipes Friese, 1900 (Latreille, 1811)'),('Meli','Melipona fuliginosa (Latreille, 1811) – manduri-preto'),('Meli','Melipona interrupta – jandaíra'),('Meli','Melipona marginata Lepeletier, 1836 – manduri, manduri menor, mandurim, minduri, gurupu-do-miúdo, taipeira'),('Meli','Melipona mimetica'),('Meli','Melipona quadrifasciata (Lepeletier) – mandaçaia, amanaçaia, manaçaia, \"uruçu\"'),('Meli','M. q. anthidioides'),('Meli','Melipona quinquefasciata (Lepeletier) – mandaçaia-da-terra, mandaçaia-do-chão, uruçu-do-chão'),('Meli','Melipona ruficrus – irapuá'),('Meli','Melipona rufiventris (Lepeletier) – uruçu-amarela, tuiuva, tujuba, bugia'),('Meli','M. r. paraensis – uruçu-boca-de-ralo'),('Meli','Melipona scutellaris Latreille, 1811 – uruçu-nordestina, \"uruçu\"'),('Meli','Melipona seminigra'),('Meli','M. s. merrillae – boca-de-renda'),('Meli','Melipona subnitida – jandaíra'),('Pleb','P. alvarengai'),('Pleb','P. catamarcensis'),('Pleb','P. cora'),('Pleb','P. domiciliorum'),('Pleb','P. droryana'),('Pleb','P. emerina'),('Pleb','P. flavocincta'),('Pleb','P. franki'),('Pleb','P. frontalis'),('Pleb','P. fulvopilosa'),('Pleb','P. goeldiana'),('Pleb','P. intermedia'),('Pleb','P. jatiformis'),('Pleb','P. julianii'),('Pleb','P. kerri'),('Pleb','P. llorentei'),('Pleb','P. lucii'),('Pleb','P. malaris'),('Pleb','P. manantlensis'),('Pleb','P. margaritae'),('Pleb','P. melanica'),('Pleb','P. meridionalis'),('Pleb','P. mexica'),('Pleb','P. minima'),('Pleb','P. molesta'),('Pleb','P. mosquito'),('Pleb','P. moureana'),('Pleb','P. nigriceps'),('Pleb','P. parkeri'),('Pleb','P. peruvicola'),('Pleb','P. phrynostoma'),('Pleb','P. poecilochroa'),('Pleb','P. pulchra'),('Pleb','P. remota'),('Pleb','P. saiqui'),('Pleb','P. tica'),('Pleb','P. tobagoensis'),('Pleb','P. variicolor'),('Pleb','P. wittmanni'),('Tetra','\" \"'),('Trigo','Trigona barrocoloralensis'),('Trigo','Trigona branneri—Mato Grosso (BR)'),('Trigo','Trigona carbonaria—Queensland (AU)'),('Trigo','Trigona chanchamayoensis—Mato Grosso (BR)'),('Trigo','Trigona cilipes—América'),('Trigo','Trigona collina—Thailand'),('Trigo','Trigona corbina—América (Mesoámerica-Costa Rica)'),('Trigo','Trigona corvina - Central and South America'),('Trigo','Trigona iridipennis—India, Sri Lanka'),('Trigo','Trigona ferricauda—América'),('Trigo','Trigona fuscipennis'),('Trigo','Trigona fulviventris—México.'),('Trigo','Trigona fuscipennis—México a Brasil.'),('Trigo','Trigona hockingsi—(AU)'),('Trigo','Trigona hyalinata—Mato Grosso (BR)'),('Trigo','Trigona minangkabau'),('Trigo','Trigona nigerrima—América (México, Costa Rica).'),('Trigo','Trigona nigra—México.'),('Trigo','Trigona pallens—América'),('Trigo','Trigona recursa—Mato Grosso (BR)'),('Trigo','Trigona siLvestriana—América (Costa Rica)'),('Trigo','Trigona spinipes-arapuá (BR)');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-31  5:05:24
