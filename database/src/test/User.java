package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class User {
	/*
	 * cicciorose fr@mail.it 123456 2016-07-27 01:45:44.0 francesco rose 1994-07-14
     * 0 87100 87036 cane nebbia ADMIN
	 * */
	
	
	User( ResultSet resultSet) throws SQLException, ParseException{
		this.username=resultSet.getString(1);
		this.email=resultSet.getString(2);
		this.password=resultSet.getString(3);
		this.create_time= new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(4));
		this.name=resultSet.getString(5);
		this.surname=resultSet.getString(6);
		this.bornDate=new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(7));
		this.user_category=Integer.parseInt(resultSet.getString(8));
		this.born_cap=Integer.parseInt(resultSet.getString(9));
		this.residence_cap=Integer.parseInt(resultSet.getString(10));
		this.question=resultSet.getString(11);
		this.answer=resultSet.getString(12);
		this.referral=resultSet.getString(13);
	}
	
	String username;
	String email;
	String password;
	Date create_time;
	String name;
	String surname;
	Date bornDate;
	int user_category;
	int born_cap;
	int residence_cap;
	String question;//domanda
	String answer;//risposta
	String referral;
	
	@Override
	public String toString() {
	return 	"Username      : "+username+"\n"+
			"Email         : "+email+"\n"+ 
			"Password      : "+password+"\n"+
			"create date   : "+create_time+"\n"+
			"name          : "+name+"\n"+
			"surname       : "+surname+"\n"+
			"born date     : "+bornDate+"\n"+
			"user_category : "+user_category+"\n"+
			"Born Cap      : "+born_cap+"\n"+
			"Residence Cap : "+residence_cap+"\n"+
			"Question      : "+question+"\n"+
			"answer        : "+answer+"\n"+
			"referral      : "+referral+"\n";
	}
	
	
	
	

}
