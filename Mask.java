package tesst;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Mask {
	private static boolean isElementExist(String newMsg, String currentElement) {
		Pattern pattern = Pattern.compile(".+<"+currentElement+">.+</"+currentElement+">.+");
		boolean x=(pattern.matcher(newMsg)).matches();
		return x;
	}
	public static String maskFields(String original){
		String newMsg=original;
	
	List<String>secureFieldsList=new ArrayList<>();	
	secureFieldsList.add("value");
	System.out.println("Elements to be Masked"+secureFieldsList);
		for(String currentElement :secureFieldsList){
			System.out.println("Start--Element Masking-"+currentElement);
		Pattern pattern = Pattern.compile("<"+currentElement+">.</"+currentElement+">");
		if(isElementExist(newMsg,currentElement)){
			System.out.println("element is existing");
			int start=newMsg.indexOf("<"+currentElement+">")+currentElement.length()+3;
		}
		
		
	}
		return original;
		
		
	}
public static void main(String[] args) {
	String nmsg;
	String msg="Payload: <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><addValue xmlns=\"http://mySoap\"><value>55.0</value></addValue></soap:Body></soap:Envelope>";
	System.out.println(msg);
	nmsg=maskFields(msg);
	System.out.println(nmsg);
}
}
