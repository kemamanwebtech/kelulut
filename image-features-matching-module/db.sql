DROP TABLE IF EXISTS species_features;
CREATE TABLE species_features (
   num_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id of this feature',
   species_id int(11) NOT NULL COMMENT 'Identifies the user of the species',
   keypoints varchar(256) NOT NULL COMMENT 'keypoints output of SIFT analysis',
   descriptors varchar(256) NOT NULL COMMENT 'descriptors output of SIFT analysis',
   image_location varchar(256) NOT NULL COMMENT 'relative location of the image',
   PRIMARY KEY (num_id)
) COMMENT='Known species features. One species may have multiple features';

commit;

DROP TABLE IF EXISTS species_id;
CREATE TABLE species_id (
   species_id int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identifies the user of the species',
   species_name varchar(256) NOT NULL COMMENT 'scientific name of this species',
   des varchar(256) NOT NULL COMMENT 'description of this species',
   PRIMARY KEY (species_id)
) COMMENT='List of species';

commit;