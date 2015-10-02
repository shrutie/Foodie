import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;

public class DreamBitSMS
{
	public static String send(String sNumber, String message)
	{
		try
		{
		   String sUserID = "ishashank22"; 
	        String sPwd = "ishashank22";
	        String code = "TP";
	        String sSID = "DBTECH"; 
	        String sMessage = message; 
	        String sURL ="http://sms.dreambit.in/SendSMS.aspx?username="+ sUserID + "&";
	        sURL +=  "password=" + sPwd + "&mobile=" + sNumber + "&sid=" + sSID + "&code="+code+"&msg=" + URLEncoder.encode(sMessage) ;
	        
			URL url = new URL(sURL);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
			String retval = "";
            while ((line = rd.readLine()) != null) {
                // Process line...
                retval += line;
            }
            //wr.close();
            rd.close();
            
            System.out.println(retval);
           return retval;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
		
		
	}

}