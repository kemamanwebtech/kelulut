-- TODO : insert genus info 
insert into genus 
(genus_name, des, image)
values 
('genusname1', 'description of this genus', '/genus/genus1.png'),
('genusname2', 'description of this genus', '/genus/genus2.png');

-- TODO : check table genus for genus_id then use it in the following insert query
insert into species 
(genus_id, species_name, des, image)
values 
(1, 'homosapien', 'this is human species', '/known_species/001-homosapien-1.png'),
(1, 'anotherspeciesname', 'this is another species', '/known_species/002-anotherspecies-1.png');