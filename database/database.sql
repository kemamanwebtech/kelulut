
drop database if exists kelulut;
create database kelulut;
commit;

use kelulut;


DROP TABLE IF EXISTS kelulut_user;
create table kelulut_user (
  user_id         integer   not null auto_increment   comment 'unique ID for each user.',
  username        varchar(255)                        comment 'name of the user',
  location        varchar(255)                        comment 'user location',
  des             varchar(255)                        comment 'profile description of the user',
  created         datetime  						  comment 'date of the creation of this user',
  is_admin        integer                             comment '1 indicates admin user, else normal user',
  primary key (user_id)
) comment 'list of users';
commit;


DROP TABLE IF EXISTS kelulut_login;
create table kelulut_login (
  user_id         integer                             comment 'unique ID for each user.',
  email           varchar(255)                        comment 'name of the user',
  passwd          varchar(255)                        comment 'name of the user',
  created         datetime                            comment 'date of the creation of this user',
  last_login      datetime                            comment 'date of the last login by this user',
  primary key (user_id),
  FOREIGN KEY (user_id) REFERENCES kelulut_user(user_id)
) comment 'list of user logins';
commit;


DROP TABLE IF EXISTS genus;
CREATE TABLE genus (
   genus_id       int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the user of the genus',
   genus_name     varchar(256)                        COMMENT 'scientific name of this genus',
   des            varchar(256)                        COMMENT 'description of this genus',
   image          varchar(256)                        comment 'url to the image of this genus',
   PRIMARY KEY (genus_id)
) COMMENT='list of genus';
commit;


DROP TABLE IF EXISTS species;
CREATE TABLE species (
   species_id     int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the species',
   genus_id       int                                 COMMENT 'identifies the genus',
   species_name   varchar(256)                        COMMENT 'scientific name of this species',
   des            varchar(256)                        COMMENT 'description of this species',
   image          varchar(256)                        comment 'url to the image of this species to display on App',
   PRIMARY KEY (species_id),
   FOREIGN KEY (genus_id) REFERENCES genus(genus_id)
) COMMENT='list of species';
commit;


DROP TABLE IF EXISTS uploaded_images;
CREATE TABLE uploaded_images (
   image_id       int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the image',
   user_id        int                                 COMMENT 'identifies the user who uploads this image',
   image_des      varchar(256)                        COMMENT 'description of this image',
   image_loc      varchar(256)                        COMMENT 'location where the image was stored as file system',
   location       varchar(256)						  COMMENT 'location where the image was uploaded',
   is_analyzed    int(1)                              COMMENT 'to indicate if the image have been analyzed',
   date_uploaded  datetime                            COMMENT 'the date the image was uploaded',
   species_id     int                                 COMMENT 'the id of the matched species after being analyzed',
   PRIMARY KEY (image_id),
   FOREIGN KEY (user_id) REFERENCES kelulut_user(user_id),
   FOREIGN KEY (species_id) REFERENCES species(species_id)
) COMMENT='list of uploaded images';
commit;


DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
   comment_id     int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the comment',
   image_id       int                                 COMMENT 'identifies the image the comment were on',
   user_id        int                                 COMMENT 'identifies the user who submitted this comment',
   comment        varchar(256)                        COMMENT 'the submitted comment',
   date_submitted datetime                            COMMENT 'the date the comment were made',
   PRIMARY KEY (comment_id),
   FOREIGN KEY (user_id) REFERENCES kelulut_user(user_id),
   FOREIGN KEY (image_id) REFERENCES uploaded_images(image_id)
) COMMENT='list of comments';
commit;


DROP TABLE IF EXISTS quiz;
CREATE TABLE quiz (
   question_id    int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the question',
   question       varchar(256)                        comment 'url to the image of this species',
   answer_1       varchar(256)                        comment 'option 1',
   answer_2       varchar(256)                        comment 'option 2',
   answer_3       varchar(256)                        comment 'option 3',
   answer_4       varchar(256)                        comment 'option 4',
   answer         varchar(256)                        comment 'actual correct answer',
   image          varchar(256)                        COMMENT 'url to the image of this question',
   PRIMARY KEY (question_id)
) COMMENT='list of questions';
commit;


DROP TABLE IF EXISTS score;
CREATE TABLE score (
   score_id       int     NOT NULL AUTO_INCREMENT     COMMENT 'identifies the score',
   user_id        int                                 comment 'user id of the user which this score belong to',
   score          int                                 COMMENT 'actual score',
   date_submitted datetime                            COMMENT 'the date the score was submitted',
   PRIMARY KEY (score_id),
   FOREIGN KEY (user_id) REFERENCES kelulut_user(user_id)
) COMMENT='list of scores';
commit;