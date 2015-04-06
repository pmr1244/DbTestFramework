package framework;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import generated.Database;
import generated.Database.Db;
import generated.Database.Db.Datatype;
import generated.Database.Tablereq;
import generated.Database.Tablereq.Col;

import javax.xml.bind.JAXBContext;
public class XmlReader
{
	static String dtname;
	static String syntax;
	static int tblcount;
	static List details=new ArrayList();
	static List list=new ArrayList();
	
	public static void main(String args[]) throws javax.xml.bind.JAXBException
	{
		 File file = new File("C:\\Users\\mureddy\\Desktop\\eclipse\\temp2\\src\\framework\\inputfile.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Database.class);
 
		javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Database db = (Database) jaxbUnmarshaller.unmarshal(file);
		Db dbd=db.getDb();
		//String dbName=dbd.name;
		
		
		Datatype dt=new Datatype();
		String dbname=dbd.getName();
		
		syntax=dbd.getCreateSyntax();
		tblcount=dbd.getTablecount();
		//System.out.println(tblcount);
		
		list=dbd.getDatatype();
		for(int i=0;i<list.size();i++)
		{
			dt=(Datatype) list.get(i);
			dtname=dt.getName();
			String min=dt.getMin();
			String max=dt.getMax();
			
		}

		Tablereq tr=db.getTablereq();
		if (tr.getCol()!=null)
		{
			details=tr.getCol();
			Col c=new Col();
			
		}
		
		
		/*for(int i=0;i<details.size();i++)
		{
			c=(Col) details.get(i);
			String s=c.getValue();
			System.out.println(s);
			int j=c.getNumOfCols();
			System.out.println(j);
			
		}*/
		
	}
}
