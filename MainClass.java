package framework;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import framework.*;
import generated.Database.Db;

public class MainClass {
	static int rows;
	public static void main(String[] args) throws SQLException, JAXBException, ParseException
	{
		Scanner sc=new Scanner(System.in);
		
		JdbcGeneric.main(null);
		
		
		DbUpdate du=new DbUpdate(ConnDetails.driver,ConnDetails.url,ConnDetails.user,ConnDetails.pass);
		XmlReader xr=new XmlReader();
		xr.main(null);
		Db db=new Db();
		String stx=xr.syntax;
		int tblcnt=xr.tblcount;
		//System.out.println(tblcnt);
		System.out.println("How many rows you want to insert:");
		rows=sc.nextInt();
				
		du.createTable(stx,tblcnt);
		System.out.println("\n Inserting data into tables...");
		du.insertData();
		System.out.println("Insertion completed");
	}

}
