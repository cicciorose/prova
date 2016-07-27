package test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class SiwDb {
	public static final String DRIVER= "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/Siw";
	
	private Connection connection;
	private Statement statement;
	/**
	 * @throws ClassNotFoundException class jdbc not found
	 * @throws SQLException 
	 * */
	SiwDb() throws ClassNotFoundException, SQLException{
		
			Class.forName(DRIVER);
			this.connection=DriverManager.getConnection(DB_URL, "root", "123456");
			this.statement = connection.createStatement();			 
	}
	
	List<User> getUsers() throws SQLException, ParseException{
		CopyOnWriteArrayList<User> list=new CopyOnWriteArrayList<User>();
		ResultSet resultset = statement.executeQuery(
				 ""
				 + "SELECT utenti.*  "
				 + "FROM utenti "
				 );
		while(resultset.next()){
			User u=new User(resultset);
			list.add(u);
		}
		
		
		return list;
	}
	User getUser(String username) throws SQLException, ParseException{
		ResultSet resultset = statement.executeQuery(
				 ""
				 + "SELECT utenti.*  "
				 + "FROM utenti "
				 + "WHERE username = '"+username+"'"
				 );
		
		if(resultset.next()){
			return new User(resultset);
		}
		else{
			return null;
		}
	}
	List<User> getUsersFromType(String Type)throws SQLException, ParseException{
		CopyOnWriteArrayList<User> list=new CopyOnWriteArrayList<User>();
		
		ResultSet resultset = statement.executeQuery(
				 ""
				 + "SELECT utenti.*  "
				 + "FROM utenti,categoria_utenti "
				 + "WHERE categoria_utente=id and tipo = '"+Type+"' "
				 );
		while(resultset.next()){
			User u=new User(resultset);
			list.add(u);
		}
		
		return list;
	}
	
	boolean aviableUsername(String username) throws SQLException{
		ResultSet resultset = statement.executeQuery(
				 ""
				 + "SELECT utenti.*  "
				 + "FROM utenti "
				 + "WHERE username='"+username+"'"
				 );
		if(resultset.next()){
			return false;
		}
		
		return true;
	}
	
	String get2MostPresentUser() throws SQLException{
		ResultSet resultset = statement.executeQuery(
				""								+
				"SELECT "						+
				"    tipo, count(*) as numero "	+
				"FROM "							+
				"    utenti, "					+
				"    categoria_utenti "			+
				"WHERE "						+
				"    utenti.categoria_utente = categoria_utenti.id "+
				"GROUP BY tipo "				+
				"HAVING numero < (SELECT "		+
				"        max(g.num) "			+
				"    FROM "						+
				"        (SELECT "				+
				"            count(*) as num "	+
				"        FROM "					+
				"            utenti, categoria_utenti "+
				"        WHERE "				+
				"            utenti.categoria_utente = categoria_utenti.id "+
				"        GROUP BY tipo) as g) "
				);
			if(resultset.next()){
				return resultset.getString(1);
			}		
		return null;
	}
	
	
	
	
	public static void main(String[] args) throws SQLException {
		try {
			SiwDb s=new SiwDb();
			
//			for(User u:s.getUsersFromType("ROOT")){
//				System.out.println(u);
//				System.out.println("--------------------------------------------------------");
//			}
			System.out.println(s.get2MostPresentUser());
			
		} catch (ClassNotFoundException /*| ParseException*/ e) {
			e.printStackTrace();
		}
		
		 
	}

}
