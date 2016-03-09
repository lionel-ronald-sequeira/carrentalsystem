-----------------------------Created View since mysql does not allow to select on complex subqueries---------------------------- 

CREATE VIEW CAR_DETAILS
AS
select c.vehicle_id,c.model as CAR,c.compact_flag,
c.medium_flag,c.large_flag,c.suv_flag,c.truck_flag,c.van_flag,b.bank_name as CAR_OWNER from crs_car c 
inner join crs_bank b on c.owner_id=b.owner_id 
UNION select c.vehicle_id,c.model as CAR,c.compact_flag,c.medium_flag,c.large_flag,c.suv_flag,
c.truck_flag,c.van_flag,r.company_name as CAR_OWNER from crs_car c inner join crs_rental_company r on c.owner_id=r.owner_id 
UNION select c.vehicle_id,c.model,c.compact_flag,c.medium_flag,c.large_flag,
c.suv_flag,c.truck_flag,c.van_flag as CAR,i.first_name as CAR_OWNER from crs_car c inner join crs_individual i 
on c.owner_id=i.owner_id;


--------------------Query to get earnings for owner based on car type and end-date

select r.vehicle_id,o.car,o.car_owner,sum(amount_due) as EARNING
from crs_rental r inner join  car_details o
on r.vehicle_id=o.vehicle_id
where o.compact_flag='Y'  and o.suv_flag='Y' and r.end_date>='2015-11-1' and r.end_date<='2015-11-30'
group by r.vehicle_id,o.car,o.car_owner;

---------------------Query to get earnings for owner based only on dates.
select r.vehicle_id,o.car,o.car_owner,sum(amount_due) as EARNING
from crs_rental r inner join  car_details o
on r.vehicle_id=o.vehicle_id
where r.end_date>='2015-11-1' and r.end_date<='2015-11-30'
group by r.vehicle_id,o.car,o.car_owner;