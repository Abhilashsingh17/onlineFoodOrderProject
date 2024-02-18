use onlinesaledatabase;

-- USERS AND AUTHORITIES ARE DEFAULT TABLE USED BY SPRING BOOT TO STORE USER AND ROLE

-- ADD NEW ADMIN USER AND USER ROLE
insert into user_form (date_of_birth,address,city,email_id,first_name,last_name,mobile_no,password,pin_code,security_question,state)
values('1994-02-17','yash garden','navi mumbai','abhilash@gmail.com','abhilash','singh','8898007435','$2a$10$p76qOVR4mksq71I3E3R5qO1ezS35VG90n7HEodnGPhyay4K9UR5X.',410218,'chelsea','Maharashtra');
insert into user_role values('abhilash@gmail.com','ROLE_ADMIN');
insert into user values(1,'abhilash@gmail.com','$2a$10$p76qOVR4mksq71I3E3R5qO1ezS35VG90n7HEodnGPhyay4K9UR5X.');

-- SELECT USER DETAILS  ----------------------------
select * from user;
select * from user_form;
select * from user_role;
select * from product;
select * from cart;
select * from my_order;
select * from order_details;
SELECT * FROM background_image;


-- CREATE TABLE DETAILS  ----------------------------
show create table cart;
show create table product;
show create table user;
show create table user_form;
show create table my_order;
show create table order_details;

-- TO TRUNCATE DETAILS  ----------------------------
-- truncate table user_form;
-- truncate table user;
-- truncate table product;
-- truncate table user_role;
-- truncate table cart;
-- truncate table my_order;
-- truncate table order_details;
-- truncate table background_image;




-- INSERT DATA FOR THE BACKGROUND IMAGE

insert into background_image values(1,'backgroundone.png');
insert into background_image values(2,'backgroundtwo.png');
insert into background_image values(3,'backgroundthree.png');
insert into background_image values(4,'backgroundfour.png');
insert into background_image values(5,'backgroundfive.png');
insert into background_image values(6,'backgroundsix.png');
insert into background_image values(7,'backgroundseven.png');
insert into background_image values(8,'backgroundeight.png');
insert into background_image values(9,'backgroundnine.png');

-- INSERT BASIC DETAILS FOR THE PRODUCT
select * from product;
insert into product (product_amount,product_description,product_image,product_name,product_type) values (300,'SeaFood','starbucks.png','Fish Fry','SEAFOOD');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (250,'SeaFood','Chillyfish.jpg','Chilly Fish','SEAFOOD');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (600,'SeaFood','prawn-pepper-fry.webp','Prawn pepper fry','SEAFOOD');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (650,'SeaFood','Prawncurry.jpg','Prawn curry','SEAFOOD');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (700,'SeaFood','Chillyprawn.jpg','Chilly prawn','SEAFOOD');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (440,'MUTTON','Muttonpepperfry.jpg','Mutton pepper fry','MUTTON');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (450,'MUTTON','Muttoncurry.jpg','Mutton curry','MUTTON');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (460,'MUTTON','Muttonmohali.jpg','Mutton mohali','MUTTON');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (490,'MUTTON','muttonroganjosh.jpg','mutton rogan josh','MUTTON');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (510,'MUTTON','muttonbiryaní.jpg','mutton biryaní','MUTTON');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (110,'NOODLES','Chickennoodles.jpg','Chicken noodles','NOODLES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (130,'NOODLES','Eggnoodles.jpg','Egg noodles','NOODLES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (100,'NOODLES','Vegnoodles.jpg','Veg noodles','NOODLES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (120,'NOODLES','Mixnoodles.jpg','Mix noodles','NOODLES');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (20,'EGG','Omelet.jpg','Omelet','EGG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (25,'EGG','MasalaOmelet.jpg','MasalaOmelet','EGG');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (140,'VEG','DhalFry.jpg','Dhal Fry','VEG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (140,'VEG','MixVegCurry.jpg','Mix Veg Curry','VEG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (150,'VEG','AlooDobiMasala.jpg','Aloo Dobi Masala','VEG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (120,'VEG','MasalaChenna.jpg','MasalaChenna','VEG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (130,'VEG','KadiVegetable.jpg','Kadi Vegetable','VEG');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (160,'VEG','KadiKolapurí.jpg','Kadi Kolapurí','VEG');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (80,'TANDOOR','Naan.jpg','Naan','TANDOOR');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (90,'TANDOOR','Butter Naan.jpg','Butter Naan','TANDOOR');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (100,'TANDOOR','Kulcha.jpg','Kulcha','TANDOOR');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (150,'TANDOOR','Pulka.jpg','Pulka','TANDOOR');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (220,'TANDOOR','Chicken Tikka.jpg','Chicken Tikka','TANDOOR');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (190,'TANDOOR','Paneer Tikka.jpg','Paneer Tikka','TANDOOR');

insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','Pepsi.jpg','Pepsi','BEVERAGES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','Sprite.jpg','Sprite','BEVERAGES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','Fanta.jpg','Fanta','BEVERAGES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','Coke.jpg','Coca-Cola','BEVERAGES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','Limca.jpg','Limca','BEVERAGES');
insert into product (product_amount,product_description,product_image,product_name,product_type) values (40,'BEVERAGES','MountainDew.jpg','Mountain Dew','BEVERAGES');