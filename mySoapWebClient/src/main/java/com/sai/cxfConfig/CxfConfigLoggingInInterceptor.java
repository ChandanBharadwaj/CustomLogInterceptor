package com.sai.cxfConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.springframework.beans.factory.annotation.Value;

public class CxfConfigLoggingInInterceptor extends LoggingInInterceptor {
	
	
	@Override
	protected String transform(String originalLogString) {
		 MaskUil.maskFields(originalLogString);
		 return super.transform( MaskUil.maskFields(originalLogString));
	}
}
class MaskUil{
	
	
	public static String maskFields(String original){
		String newMsg=original;
		Utils Utils = new Utils();
	List<String>secureFieldsList=Utils.getSecuredFields();	
	
	for(String currentElement :secureFieldsList){
		Pattern pattern = Pattern.compile("<"+currentElement+">(.+?)</"+currentElement+">");
		if(isElementExist(newMsg,currentElement)){
			int start=newMsg.indexOf("<"+currentElement+">")+currentElement.length()+3;
		}		
	}
		return original;
		
		
	}

	private static boolean isElementExist(String newMsg, String currentElement) {
		Pattern pattern = Pattern.compile("<"+currentElement+">(.+?)</"+currentElement+">");
		return (pattern.matcher(newMsg)).matches();
	}

}
class Utils{
	
	 @Value("${secure.fields}")
		public  String securedFields;
	public  List<String> getSecuredFields() {
			return Arrays.stream(securedFields.split("#")).collect(Collectors.toList());
	}
}
