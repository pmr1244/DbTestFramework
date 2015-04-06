package framework;

import java.util.*;
 
public class RandStrItr
{
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static int count=0;
	static Random rnd = new Random();
	
	
	static void setCount(int cnt)
	{
		count=cnt;
	}
	static String randomString( int len )  
	{
		//Scanner s=new Scanner(System.in);
		
	   StringBuilder sb = new StringBuilder( len );
	   //System.out.println(sb);
	  
	   
	   for( int j = 0; j < len; j++ )
		{	
		   Boolean rep=false;
			char c=AB.charAt( rnd.nextInt(AB.length()));
			if(sb.length()>0)
			{
				
				for(int k=0;k<sb.length();k++)
				{
					if(sb.charAt(k)==c)
					{
						rep=true;
						break;
					}
					else
						continue;
				}
				if(rep==true)
				{
					j--;
					continue;
				}
				else
				{
					sb.append(c);
					continue;
				}	
			}
			else
			{
				sb.append(c);
				continue;
				//sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			}	
		}
		return sb.toString();
	}
		
    public static ArrayList<String> PermutationFinder(String s) 
    {
	    ArrayList<String> perm = new ArrayList<String>();
	    if (s == null)
	    { // error case
	        return null;
	    } 
	    else if (s.length() == 0) 
	    { 
	        perm.add(""); // initial 
	        return perm;
	    }   
	    char initial = s.charAt(0); // first character
	    String rem = s.substring(1); // Full string without first character
	    ArrayList<String> words = PermutationFinder(rem);
	    for (String str : words) 
	    {
	        for (int i = 0; i <= str.length(); i++) 
	        {
	            perm.add(charinsert(str, initial, i));
	        }
	    }   
	    return perm;
    }
    public static String charinsert(String str, char c, int j) {
    String begin = str.substring(0, j);
    String end = str.substring(j);
    return begin + c + end;
    }
    public static  void main(String[] args)
    {
    	Scanner sc=new Scanner(System.in);
		System.out.println("Enter String length");
		int lent=sc.nextInt();
		System.out.println("How many Strings you want");
		int cnt=sc.nextInt();
		setCount(cnt);
        String s =randomString(lent);
        
        ArrayList<String> value = PermutationFinder(s);
        for(int i=0;i<cnt;i++)
        {
        	System.out.println(value.get(i));
        }
}
}
