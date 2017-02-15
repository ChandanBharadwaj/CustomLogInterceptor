
package com.sai.cxfConfig;


import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class CxfConfigLoggingOutInterceptor extends LoggingOutInterceptor {
	
	
	@Override
	protected String transform(String originalLogString) {
		
		
		 MaskUil.maskFields(originalLogString);
		 return super.transform(MaskUil.maskFields(originalLogString));

		
	}


}


