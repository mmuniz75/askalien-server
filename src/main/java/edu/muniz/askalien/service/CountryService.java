package edu.muniz.askalien.service;

import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.muniz.askalien.dao.CountryRepository;
import edu.muniz.askalien.model.Country;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepo;
	
	
	public String getCountryFromIP(String ip) {
		String country = "";
		
		try{
			Country countryObj =  countryRepo.findByIp(ip);
			
			if(countryObj==null){
				country= getCountryFromIPFinder(ip);
				
				if(country.indexOf(",")>-1)
					country = country.substring(0,country.indexOf(","));
				
				if(country.equals("KOREA"))
					country = "SOUTH KOREA";
				
				countryObj = new Country(ip,country);
				countryRepo.save(countryObj);
			}else
				country = countryObj.getCountry(); 
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return country;
	}
		
	private String getCountryFromIPFinder(String ip) {
		String country="Unknown Country";
		try{
			ip = ip.trim();
			URL url = new URL("http://www.iplocationfinder.com/" + ip);
			
			String page = readPage(url);
			if(page.indexOf("Unknown Country") == -1 && page.indexOf("No information found") == -1) {
			    int starts = page.indexOf("Country");
			    page = page.substring(starts);
			    starts = page.indexOf("title") + 7;
			    page = page.substring(starts);
			    			    
			    int end = page.indexOf("(");
			    country = page.substring(0,end-1);
			    country = country.toUpperCase();
			}
			
		}catch(Exception ex){
			System.out.println("not possible find country for IP = " + ip);
			System.out.println("country=" + country);
			ex.printStackTrace();
		}
		return country;
	}
	
	
	/** 
	 * Get using iplocation.net
	 */
	private String getCountryFromIPLocation(String ip) {
		
		String country="Unknown Country";
		try{
			ip = ip.trim();
			URL url = new URL("http://www.iplocation.net/index.php?query=" + ip);
			
			String page = readPage(url);
			if(page.indexOf("Unknown Country") == -1) {
			    int starts = page.indexOf(ip) + ip.length();
			    page = page.substring(starts);
			    starts = page.indexOf(ip) + ip.length();
			    page = page.substring(starts);
			    
			    starts = page.indexOf(ip) + ip.length() + 9;
			    page = page.substring(starts);
			    int end = page.indexOf("</td>");
			    country = page.substring(0,end);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return country;
	}	
	
	
	
	private String getCountryFromIPGeoBytes(String ip) {
		
		String country="Unknown Country";
		try{
			
			URL url = new URL("http://www.geobytes.com/IpLocator.htm?GetLocation&template=php3.txt&IpAddress=" + ip.trim());
			
			String page = readPage(url);
			if(page.indexOf("Unknown Country") == -1) {
			    int starts = page.indexOf("<meta name=\"country\" content=\"") + 30;
			    page = page.substring(starts);
			    int end = page.indexOf("\r\n") - 2;
			    country = page.substring(0,end);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return country;
	}	
	
	
	private String readPage(URL url) throws Exception {
        InputStream in = url.openStream();
	    StringBuffer sb = new StringBuffer();

	    byte [] buffer = new byte[256];

	    while(true){
	        int byteRead = in.read(buffer);
	        if(byteRead == -1)
	            break;
	        for(int i = 0; i < byteRead; i++){
	            sb.append((char)buffer[i]);
	        }
	    }
	    return sb.toString();

    }
	
}
