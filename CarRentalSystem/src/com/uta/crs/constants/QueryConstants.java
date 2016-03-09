package com.uta.crs.constants;

public class QueryConstants {

	public static final String INSERT_CAR_DETAILS_QUERY="INSERT INTO crs_car(vehicle_id,model,"
			+ "year,daily_rate,weekly_rate,compact_flag,medium_flag,large_flag,suv_flag,truck_flag,van_flag,available,creation_date)"
			+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String GET_CAR_LIST ="SELECT vehicle_id,model,year,daily_rate,weekly_rate,compact_flag,medium_flag,large_flag,suv_flag,truck_flag,van_flag,available,creation_date,owner_id from crs_car order by creation_date desc";
	
	public static final String INSERT_CUSTOMER_DETAILS_QUERY="INSERT INTO crs_customer(first_name,last_name,phone_no,street,state,zipcode,creation_date)values(?,?,?,?,?,?,?)";
	
	public static final String GET_CUSTOMER_LIST_QUERY="SELECT customer_id,first_name,last_name,phone_no,street,state,zipcode,creation_date FROM crs_customer ORDER BY creation_date DESC";
	
	public static final String GET_CAR_INFO_QUERY="SELECT vehicle_id,model,year,daily_rate,weekly_rate,compact_flag,medium_flag,large_flag,suv_flag,truck_flag,van_flag,owner_id from crs_car where vehicle_id=?";

	public static final String UPDATE_CAR_RATES="UPDATE crs_car SET daily_rate=?,weekly_rate=? WHERE vehicle_id=?";
	
	public static final String CAR_AVAILABLE_FOR_RENT="SELECT vehicle_id,model,year,daily_rate,weekly_rate,compact_flag,medium_flag,large_flag,suv_flag,truck_flag,van_flag FROM crs_car WHERE vehicle_id IN(SELECT vehicle_id FROM crs_car WHERE vehicle_id NOT IN(SELECT vehicle_id FROM crs_rental WHERE (? between start_date AND return_date)or (? between start_date AND return_date) or (? <= start_date and ? >= return_date)))";

	public static final String CHECK_VEHICLE_ID_EXISTS="SELECT COUNT(vehicle_id) FROM crs_car WHERE vehicle_id=?";
	
	public static final String GET_CUSTOMER_LIST_WITH_NAME_ID_QUERY="SELECT customer_id,first_name,last_name FROM crs_customer";
	
	public static final String INSERT_RENTAL_DETAILS="INSERT INTO crs_rental(customer_id,vehicle_id,start_date,return_date,amount_due,rental_type,rental_timing,no_of_days,no_of_weeks,creation_date,end_date)values(?,?,?,?,?,?,?,?,?,?,?)";

	public static final String GET_RENTAL_INFO="SELECT r.customer_id,r.vehicle_id,r.start_date,r.end_date,r.return_date,r.amount_due,r.rental_type,r.rental_timing,r.no_of_days,r.no_of_weeks,c.first_name,c.last_name,cr.model,r.creation_date,r.rental_id FROM crs_rental r INNER JOIN crs_customer c on r.customer_id=c.customer_id INNER JOIN crs_car cr on r.vehicle_id=cr.vehicle_id order by r.creation_date desc";
	
	public static final String CHECK_RENTAL_INFO_EXISTS="SELECT COUNT(vehicle_id) FROM crs_rental";
	
	public static final String CHECK_USER_EXISTS="SELECT COUNT(username) FROM crs_user where username=? and password=?";
	
	public static final String GET_CAR_LIST_FOR_RENTAL="SELECT vehicle_id,model,year,daily_rate,weekly_rate,compact_flag,medium_flag,large_flag,suv_flag,truck_flag,van_flag,available FROM crs_car where available=? ";
	
	public static final String UPDATE_CAR_AVAILABILITY="UPDATE crs_car set availabilty=? where vehicle_id=?";
	
	public static final String GET_OWNER_LIST="select o.owner_id,b.bank_name from crs_owner o inner join crs_bank b on o.owner_id=b.owner_id UNION select o.owner_id,r.company_name from crs_owner o inner join crs_rental_company r on o.owner_id=r.owner_id UNION select o.owner_id,i.first_name from crs_owner o inner join crs_individual i on o.owner_id=i.owner_id";
	
	public static final String GET_RATES_FOR_CAR="select daily_rate,weekly_rate from crs_car where vehicle_id=?";
	  
	public static final String UPDATE_AMOUNT_DUE_IN_RENTAL="UPDATE crs_rental set amount_due=?,rental_timing=? where rental_id=?";
	
	public static final String GET_RENTAL_INFO_FOR_RENTAL_ID="select start_date,return_date,rental_type,vehicle_id,no_of_days,no_of_weeks,customer_id from crs_rental where rental_id= ?";
}
