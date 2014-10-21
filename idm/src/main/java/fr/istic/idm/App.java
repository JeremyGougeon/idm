package fr.istic.idm;

import static com.pretech.jooq.Tables.TABLETESTIDM1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
 
 
public class App {
 
    /**
     * @param args
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	Connection conn = getConnection();
		DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
		
		create.insertInto(TABLETESTIDM1,TABLETESTIDM1.COL1IDM1,TABLETESTIDM1.COL2IDM1, TABLETESTIDM1.COL3IDM1, TABLETESTIDM1.COL4IDM1)
		.values("valeur4","valeur3","","")
		.values("valeur2","","","valeur7")
		.values("valeur5","","valeur10","")
		.values("valeur0","valeur1","","")
		.values("valeur9","","valeur8","")
		.execute();
		
		
		
		Result<Record> result = create.select().from(TABLETESTIDM1).fetch();
		System.out.println("***************Première requête**************************");
		for (Record r : result) {
		  String col1idm1 = r.getValue(TABLETESTIDM1.COL1IDM1);
          String col2idm1 = r.getValue(TABLETESTIDM1.COL2IDM1);
          String col3idm1 = r.getValue(TABLETESTIDM1.COL3IDM1);
          String col4idm1 = r.getValue(TABLETESTIDM1.COL4IDM1);
          
          System.out.println("***************************************************************");
          System.out.println("col 1 : " + col1idm1 + ",col 2 : " + col2idm1 + ",col 3 : " + col3idm1 + ",col 4 : " + col4idm1);
          
      }
		
		Result<Record2<String, String>> result2 = create.select(TABLETESTIDM1.COL1IDM1,TABLETESTIDM1.COL3IDM1)
	      .from(TABLETESTIDM1)
	      .orderBy(TABLETESTIDM1.COL1IDM1)
		  .fetch();
		 
		for (Record r : result2) {
			  String col1idm1 = r.getValue(TABLETESTIDM1.COL1IDM1);
	          String col3idm1 = r.getValue(TABLETESTIDM1.COL3IDM1);
	          
	          System.out.println();
	          System.out.println("*****************Deuxième requête***********************");
	          System.out.println("col 1 : " + col1idm1 + ",col 2 : " + col3idm1);   
	      }
		
		conn.close();
		System.exit(0);
    }
	
	
	private static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Connection conn;
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetestidm", "root", null);
        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }
		return conn;
	}
				
}