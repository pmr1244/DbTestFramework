package framework;


import generated.Database.Tablereq;
import generated.Database.Tablereq.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DbUpdate extends JdbcGeneric
{
	ConnDetails cd=new ConnDetails();
	XmlReader xr=new XmlReader();
	Col c=new Col();
	//RandomString rs=new RandomString();
	List ll2=new ArrayList();
	List tblnames=new ArrayList();
	List ll=new ArrayList<Integer>();
	Connection conn = null;
	Statement stmt = null;
	DbUpdate(String driver,String url,String user,String pass) throws SQLException
	{
		conn = DriverManager.getConnection(url, user, pass);
		stmt = conn.createStatement();
		ll2=cd.getDatatypeInfo(driver, url, user, pass);
	}

	public void createTable(String syntax,int tblcnt) throws SQLException
	{
		if(!xr.details.isEmpty())
		{
			String cols="";
		for(int i=0;i<xr.details.size();i++)
		{
			c=(Col) xr.details.get(i);
			ll.add(c.getValue());
			ll.add(c.getNumOfCols());
			
		}
	
		
			for(int i=0;i<ll.size();i=i+2)
				
			{
				
				if((ll.get(i)).equals("char")||(ll.get(i)).equals("varchar"))
					
				{
					
					for(int j=0;j<(Byte)ll.get(i+1);j++)
					{
						//cols=cols+"\""+"cols_"+j+ll.get(i)+"\""+" "+ll.get(i)+"(50)"+",";
						cols=cols+"cols_"+j+ll.get(i)+" "+ll.get(i)+"(50)"+",";
						
					}
				}
				else {
				
					for(int j=0;j<(Byte)ll.get(i+1);j++)
					{
						//cols=cols+"\""+"cols_"+j+ll.get(i)+"\""+" "+ll.get(i)+",";
						cols=cols+"cols_"+j+ll.get(i)+" "+ll.get(i)+",";
						
					}
				}
				
			}
			String cols1="";
			for(int i=0;i<cols.length()-1;i++)
			{
				cols1=cols1+cols.charAt(i);
			}
			
			
				for(int i=1;i<=tblcnt;i++)
				{
					String tablename="table_"+i;
					String query=syntax+" "+tablename+" ("+cols1+")";
					System.out.println(query);
					String drp="drop table "+tablename;
					//System.out.println(drp);
					try
					{
					stmt.executeUpdate(drp);
					}
					catch(SQLException e)
					{
						
					}
					stmt.executeUpdate(query);
					tblnames.add(tablename);
					System.out.println(tablename+" created successfully");
				}
			
		}
		else {
			String cols="";
			for(int i=0,j=1;i<ll2.size()&&j<ll2.size();i++)
			{
				
				String param = null;
				if(i%7==0)
				{
					param=(String) ll2.get(j);
					j=j+7;
					
				}
				
					if(((i%7)==0)&&param!=null&&(param.indexOf(',')!=-1))
						cols=cols+"col1_"+ll2.get(i)+" "+ll2.get(i)+"(30,2)";
					else if(((i%7)==0)&&param!=null&&(param.indexOf(",")==-1))
						cols=cols+"col_"+ll2.get(i)+" "+ll2.get(i)+"(30)";
					else if((i%7)==0)
						cols=cols+"col_"+ll2.get(i)+" "+ll2.get(i);
					
				
				if(i<(ll2.size()-7)&&(i%7)==0)
					cols=cols+",";
		
			}
			
			for(int i=1;i<=tblcnt;i++)
			{
				String tablename="table_"+i;
				String query=syntax+" "+tablename+" ("+cols+")";
				System.out.println(query);
				String drp="drop table "+tablename;
				//System.out.println(drp);
				try
				{
				stmt.executeUpdate(drp);
				}
				catch(SQLException e)
				{
					
				}
				stmt.executeUpdate(query);
				tblnames.add(tablename);
				System.out.println(tablename+" created successfully");
				
			}
			
		}
	}
	public void insertData() throws SQLException, ParseException
	{
		
		
		if(ll.isEmpty())
		{
			for(int i=0;i<ll2.size();i=i+7)
			{
 			ll.add(ll2.get(i));
			ll.add((byte)1);
			ll.add(ll2.get(i+5));
			ll.add(ll2.get(i+6));
			}
		}
		//System.out.println(ll);
		
		
	for(int f=0;f<tblnames.size();f++)	
	{	
		String query="";
		
		for(int j=0;j<MainClass.rows;j++)
		{
			
			String val="";
			
			
		for(int i=0;i<ll.size();i=i+4)
		{
			byte l=(byte) ll.get(i+1);
			if(((String) ll.get(i)).equalsIgnoreCase("bigint"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.intNumber("bigint",l);
				Object String;
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("int"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.intNumber("int",l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("tinyint"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.intNumber("tinyint",l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("smallint"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.intNumber("smallint",l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("decimal"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.floatNum("decimal",l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("numeric")||((String) ll.get(i)).equalsIgnoreCase("number"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.floatNum((String) ll.get(i),l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
				
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("float"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.floatNum("float",l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
				
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("real"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.floatNum("real",l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
				
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("binary")||((String) ll.get(i)).equalsIgnoreCase("binary_float"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.binaryNumber(l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
				
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("char"))
			{
				//System.out.println()
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.combString("abcde", l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					//val=val+null+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("varchar")||((String) ll.get(i)).equalsIgnoreCase("varchar2"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.combString("fghij", l);
				
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					//val=val+null+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("text"))
			{

				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.combString("klmno", l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
				
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("nchar"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.combString("pqrst", l);
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("nvarchar")||((String) ll.get(i)).equalsIgnoreCase("nvarchar2"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.combString("uvwxyz", l);
				
				
				
				if(ll.get(i+2)!=null)
					pp = (java.lang.String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("bit"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.bitNum(l);
				
				
				if(ll.get(i+2)!=null)
					pp = (String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
				
			}
			
			
			
			
			
			else if(((String) ll.get(i)).equalsIgnoreCase("money"))
			{
				ArrayList al=new ArrayList();
				String ss="",pp="";
				RandVal rv=new RandVal();
				al=rv.floatNum("money", l);
				
				
				if(ll.get(i+2)!=null)
					pp = (String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				
				
				
				
				
				for(int k=0;k<l;k++)
				{
				val=val+pp+al.get(l*j+k)+ss+",";
					
				}
				
			}
			
			
			
			
			else if(((String) ll.get(i)).equalsIgnoreCase("date"))
			{
				/*ArrayList al=new ArrayList();
				RandVal rv=new RandVal();
				al=rv.dateTime();
				val=val+al.get(0);*/
				for(int k=0;k<l;k++)
				{
				val=val+null+",";
				}
			}
			else if(((String) ll.get(i)).equalsIgnoreCase("sysname"))
			{
				String ss="",pp="";

				if(ll.get(i+2)!=null)
					pp = (String) ll.get(i+2);
				
				if(ll.get(i+3)!=null)
					ss= (java.lang.String) ll.get(i+3);
				for(int k=0;k<l;k++)
				{
				val=val+pp+0+ss+",";
				}
			}
			else
			{
				
				for(int k=0;k<l;k++)
				{
				val=val+null+",";
				}
			}
		}
		String val1="";
		for(int i=0;i<val.length()-1;i++)
		{
			val1=val1+val.charAt(i);
		}
		query="insert into "+tblnames.get(f)+" values("+val1+")";
		System.out.println(query);
		stmt.executeUpdate(query);
		query="";
	//	System.out.println("One row inserted successfully");
		}
		
	}
		
	
	}
	

}
