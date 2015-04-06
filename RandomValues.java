package framework;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class RandVal
{
	
	Scanner sc=new Scanner(System.in);
	int num;
	
	
	public ArrayList binaryNumber(int ncols)
	{
		ArrayList ll =new ArrayList();
		int num=MainClass.rows;
		for(int i=0;i<num*ncols;i++)
		{
			ll.add(Integer.toBinaryString(i));
		}
		
		return ll;
		    	
	}
	
	
	
	public ArrayList intNumber(String type,int ncols)
	{
		ArrayList ll =new ArrayList();
		int num=MainClass.rows;
		
		if(type.equalsIgnoreCase("tinyint"))
		{
			for(int i=0;i<num*ncols;i++)
			{
					ll.add(i);
			}
			return ll;
			
		}
		else if(type.equalsIgnoreCase("smallint"))
		{
			
			for(int i=0;i<num*ncols;i++)
			{
			  
				ll.add(1000+i);
			}
			return ll;
		
		}
		else if(type.equalsIgnoreCase("int"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				ll.add(2147483647-i);
			}
			return ll;
		}
		else
		{
			for(int i=0;i<num*ncols;i++)
			{
				ll.add(2147483647+i);
			}
			return ll;
		}
		
		
		/*for(int i=0;i<num;i++)
		{
		    int n= rg.nextInt(1000);
			ll.add(n);
		}
		for(int i=0;i<ll.size();i++)
		{
			System.out.println(ll.get(i));
			
		} */
	}
	
	
	
	public ArrayList dateTime(int ncols) throws ParseException 
	{
		/*System.out.println("Enter how many date values you want:");
		num=sc.nextInt();*/
		ArrayList ll =new ArrayList();
		for(int i=0;i<1;i++)
		{
			 DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
	         Calendar cal=Calendar.getInstance();
	         String str_date1="10-June-90";
	         String str_date2="10-June-14";
	         
	        /* cal.setTime(formatter.parse(str_date1));
	         Long value1 = cal.getTimeInMillis();*/
	
	        /* cal.setTime(formatter.parse(str_date2));
	         Long value2 = cal.getTimeInMillis();
	*/
	         /*long value3 = (long)(value1 + Math.random()*(value2 - value1));
	         cal.setTimeInMillis(value3);*/
	         ll.add(formatter.format(cal.getTime()));
		}
		return ll;
	}
	
	
	
	public ArrayList bitNum(int ncols)
	{
		
		ArrayList ll =new ArrayList();
		int num=MainClass.rows;
		for(int i=0;i<num*ncols;i++)
		{
				ll.add(i%2);
		}
		
		return ll;
		
	}
	
	
	public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
	
	
	
	
	
	
	public ArrayList floatNum(String type,int ncols)
	{
		
		ArrayList ll =new ArrayList();
		int num=MainClass.rows;
		float f;
		if(type.equalsIgnoreCase("float"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (i+0.55);
				
				ll.add(round(f,2));
			}
		}
		else if(type.equalsIgnoreCase("number"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (i+0.56);
				
				ll.add(round(f,2));
			}
		}
		else if(type.equalsIgnoreCase("numeric"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (i+0.57);
				
				ll.add(round(f,2));
			}
		}
		else if(type.equalsIgnoreCase("real"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (i+0.58);
				
				ll.add(round(f,2));
			}
		}
		else if(type.equalsIgnoreCase("decimal"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (i+0.59);
				
				ll.add(round(f,2));
			}
		}
		else if(type.equalsIgnoreCase("money"))
		{
			for(int i=0;i<num*ncols;i++)
			{
				
				f=(float) (1000*i+0.59);
				
				ll.add(round(f,2));
			}
		}
		
		
		return ll;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	public static ArrayList combString(String s,int ncols)
	{
	    ArrayList ll=new ArrayList();
	    ArrayList ll1=new ArrayList();
	    int num=MainClass.rows;
	   // System.out.println(s);   
	    char[] a = s.toCharArray();
	    int n = a.length;
	    int[] p = new int[n];  // Index control array initially all zeros
	    int i = 1;
	    while (i < n) {
	        if (p[i] < i) {
	            int j = ((i % 2) == 0) ? 0 : p[i];
	            swap(a, i, j);
	            
				
	            ll.add(join(a));
				
	            p[i]++;
	            i = 1;
	        }
	        else {
	            p[i] = 0;
	            i++;
	        }
	    }
		for(int k=0;k<num*ncols;k++)
		{
			ll1.add(ll.get(k));
		}
		return ll1;
	}

	private static String join(char[] a) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(a);
	    return builder.toString();
	}

	private static void swap(char[] a, int i, int j) {
	    char temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* public ArrayList PermutationFinder(String s) 
	    {
		 
		    ArrayList ll = new ArrayList();
		    if (s == null)
		    { // error case
		        return null;
		    } 
		    else if (s.length() == 0) 
		    { 
		        ll.add(""); // initial 
		        return ll;
		    }   
		    char initial = s.charAt(0); // first character
		    String rem = s.substring(1); // Full string without first character
		    ArrayList words = PermutationFinder(rem);
		   // for (String str : words)
		    for(int j=0;j<words.size();j++)
		    {
		    	String str=(String) words.get(j);
		        for (int i = 0; i <= str.length()&&i<MainClass.rows;i++) 
		        {
		            ll.add(charinsert(str, initial, i));
		        }
		    }   
		    return ll;
	    }
	    public static String charinsert(String str, char c, int j) {
	    String begin = str.substring(0, j);
	    String end = str.substring(j);
	    return begin + c + end;
	    }
	
	
	public void chars()
	{
		ArrayList ll =new ArrayList();
		int num=MainClass.rows;
		String str="progress";
		String str1;
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<str.length();j++)
			{
				int c=str.charAt(j);
				
			}
		}
	}*/
	
	
	
	
	
	
	
}
public class RandomValues
{
	public static void main(String args[]) throws ParseException
	{
		
		RandVal rv=new RandVal();
		//rv.dateTime();
		/*RandVal rv=new RandVal();
		rv.binaryNumber();
		System.out.println("Enter the integer type:");
		Scanner sc=new Scanner(System.in);
		String type=sc.next();
		rv.intNumber(type);
		rv.dateTime();
		rv.bitNum();
		rv.floatNum();*/
	}
}
