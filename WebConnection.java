import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

public class WebConnection {

    public static String quote;
    static String quoteRemove;
    
    //Website URLS (Constants)
    final static String dailyWebsiteURL = "https://www.horoscope.com/us/horoscopes/general/horoscope-general-daily-today.aspx?sign=" + mainMethod.zodiacSign + "&ladate=";
    final static String weeklyWebsiteURL = "https://www.horoscope.com/us/horoscopes/general/horoscope-general-weekly.aspx?sign=" + mainMethod.zodiacSign + "&ladate=";
    final static String monthlyWebsiteURL = "https://www.horoscope.com/us/horoscopes/general/horoscope-general-monthly.aspx?sign=" + mainMethod.zodiacSign + "&ladate=";
    final static String yesterdaysWebsiteURL = "https://www.horoscope.com/us/horoscopes/general/horoscope-general-daily-yesterday.aspx?sign=" + mainMethod.zodiacSign + "&ladate=";
    
    //websiteURL, the date (yyyymmdd), and whether the website requires a birthday at the end
    public static void openConnection(String websiteURL, String date, boolean birthDay, String messageTitle) {
        String URL;
    	try {
            StringBuilder content = new StringBuilder();
            
            //Some websites require a birthday in front, some dont
            if (birthDay == true) {
            	URL = (websiteURL + date);
            }  else {
            	URL = (websiteURL);
            }
            URL url = new URL(URL);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
                    if(line.contains("<p><strong>")) {
                    	if (line.toString().contains("<i>")) {
                    		quoteRemove = (String) line.toString().subSequence(line.toString().indexOf("<i>", 0), line.toString().indexOf("</i>", 0) + 4);
                    	} else {
                    		quoteRemove = "";
                    	}
                    	
                    	//Removes all of the random symbols created through HTML
                    	quote = (String) line.toString().subSequence(line.toString().indexOf(" - ", 0)+2,line.toString().length()-4).toString()
                    			.replace(quoteRemove, "").replace("<br>", " ").replace(".", ".\n").replace("✨", "").replace("â", "")
                    			.replace("œ", "").replace("€™", "'").replace("¨", "").replace("’", "'");    
                    	break;
                    }
                }
                bufferedReader.close();	
        		
        		JOptionPane.showMessageDialog(null, quote + "\nThis information was pulled from "+URL, messageTitle, JOptionPane.INFORMATION_MESSAGE); //Prints out what is pulled from the website.

                
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
