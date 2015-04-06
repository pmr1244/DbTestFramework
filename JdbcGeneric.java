package framework;

import framework.DbUpdate;

import java.sql.*;
import java.util.*;
class ConnDetails
{
	 static String driver,user,pass,url;
	
	 static int dbno;
	public void connectionDetails() throws SQLException
	{
		
		  
		   List li;
		   
			
			System.out.println(" 1.Oracle\n 2.MySQL\n 3.DB2 \n 4.PostgreSQL \n 5.Progress OpenEdge \n 6.SQL Server \n 7.Sybase \n 8.Informix \n 9.Greenplum \n");
			System.out.print("\nSelect your database from the above options: ");
			@SuppressWarnings("resource")
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			dbno=choice;
			switch(choice)
			{
				case 1:
					driver="com.ddtek.jdbc.oracle.OracleDriver";
					url="jdbc:datadirect:oracle://127.0.0.1:1521;ServiceName=XE";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 2:
					driver="com.ddtek.jdbc.mysql.MySQLDriver";
					url="jdbc:datadirect:mysql://nc-lnx22:3306;DatabaseName=jvs";
					user="jvs001";
					pass="sqlnk001";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 3:
					driver="com.ddtek.jdbc.db2.DB2Driver";
					url="jdbc:datadirect:db2://127.0.0.1:50000;DatabaseName=FrameworkTest";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 4:
					driver="com.ddtek.jdbc.postgresql.PostgreSQLDriver";
					url="jdbc:datadirect:postgresql://127.0.0.1:5432;DatabaseName=FrameworkTest";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 5:
					driver="com.ddtek.jdbc.openedge.OpenEdgeDriver";
					url="jdbc:datadirect:openedge://127.0.0.1:2003;DatabaseName=FrameworkTest";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 6:
						driver="com.ddtek.jdbc.sqlserver.SQLServerDriver";
						url="jdbc:datadirect:sqlserver://127.0.0.1:1433;DatabaseName=FrameworkTest";
						user="adapter";
						pass="adapter";
						li=getDatatypeInfo(driver,url,user,pass);
						
						break;
						
				case 7:
					driver="com.ddtek.jdbc.sybase.SybaseDriver";
					url="jdbc:datadirect:sybase://nc-lnx54:5000;DatabaseName=jvs";
					user="jvs001";
					pass="sqlnk001";
					li=getDatatypeInfo(driver,url,user,pass);
					
						break;
						
				case 8:
					driver="com.ddtek.jdbc.informix.InformixDriver";
					url="jdbc:datadirect:informix://127.0.0.1:2003;DatabaseName=FrameworkTest";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
							break;
							
				case 9:
					driver="com.ddtek.jdbc.greenplum.GreenplumDriver";
					url="jdbc:datadirect:greenplum://127.0.0.1:5432;DatabaseName=FrameworkTest";
					user="adapter";
					pass="adapter";
					li=getDatatypeInfo(driver,url,user,pass);
					
							break;
				
				default:
					System.out.println("Please select any one of the databases !");
						
								
			} 
	}
		
	public List<Comparable> getDatatypeInfo(String dname,String dburl,String uname,String pwd)
	{
		
		String JDBC_DRIVER = dname;  
		String DB_URL = dburl;
		String USER = uname;
		String PASS =pwd;
		Connection conn = null;
		Statement stmt = null;
		DatabaseMetaData dbmd;
		
		List<Comparable> ll=new ArrayList();
		/*List<Comparable> ll_prefix=new ArrayList();
		List<Comparable> ll_suffix=new ArrayList();*/
		try
	   {
		
		  
		  Class.forName(JDBC_DRIVER);
		  conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  dbmd=conn.getMetaData();
		  ResultSet rs = dbmd.getTypeInfo();
		  while (rs.next())
		  {
			  
			  	String typeName = rs.getString("TYPE_NAME");
			  
			  	if(typeName.contains("IDENTITY")||typeName.contains("(")||typeName.contains("identity")||typeName.contains("time")||typeName.contains("LONG")||typeName.contains("TIME")||typeName.contains("UNSIGNED")||typeName.contains("unsigned"))
			  		continue;
			  	else
			  	{
			  		
			  	
			  	
				  		ll.add(typeName);
					  
				       String createParams = rs.getString("CREATE_PARAMS");
				        ll.add(createParams);
				        int nullable = rs.getInt("NULLABLE");
				        ll.add(nullable);
				        long size=rs.getInt("PRECISION");
				        ll.add(size);
				        boolean caseSensitive = rs.getBoolean("CASE_SENSITIVE");
				        ll.add(caseSensitive);
				        String pre=rs.getString("LITERAL_PREFIX");
				        ll.add(pre);
				       
				        String suf=rs.getString("LITERAL_SUFFIX");
				        ll.add(suf);
			  		
			  	}
		      
		  }  
	 
		 
	   }
		
		
	   catch(SQLException se)
	   {
		  se.printStackTrace();
	   }
	   catch(Exception e)
	   {
		  e.printStackTrace();
	   }
		
	   finally
	   {
		  
		  try
		  {
			 if(stmt!=null)
				stmt.close();
		  }
		  catch(SQLException se2)
		  {
		  }
		  try
		  {
			 if(conn!=null)
				conn.close();
		  }
		  catch(SQLException se)
		  {
			 se.printStackTrace();
		  }
	   }
		 return ll;
	}
}

public class JdbcGeneric 
{
   
   
   public static void main(String[] args) throws SQLException 
   {
	  ConnDetails cd=new ConnDetails();
	  cd.connectionDetails();
	   
	}
}
