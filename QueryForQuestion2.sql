select * from crs_customer;

select * from crs_car;

select * from crs_rental;
 
select * from crs_car where compact_flag='Y';

select * from crs_car where medium_flag='Y';

select * from crs_car where large_flag='Y';

select * from crs_car where suv_flag='Y';

select * from crs_car where truck_flag='Y';

select * from crs_car where van_flag='Y';

 select * from crs_rental where rental_timing='active';
 
 select * from crs_rental where rental_timing='scheduled';
 
select c.model as CAR,b.bank_name as CAR_OWNER from crs_car c inner join crs_bank b on c.owner_id=b.owner_id UNION select c.model as CAR,r.company_name as CAR_OWNER from crs_car c inner join crs_rental_company r on c.owner_id=r.owner_id UNION select c.model as CAR,i.first_name as CAR_OWNER from crs_car c inner join crs_individual i on c.owner_id=i.owner_id;