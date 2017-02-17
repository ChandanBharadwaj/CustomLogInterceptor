package com.sai;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sai.cxfConfig.CxfConfigLoggingInInterceptor;
import com.sai.cxfConfig.CxfConfigLoggingOutInterceptor;

import mysoap.Add;
import mysoap.AddValue;


@RestController
class Controller{
	@Autowired 
	Add add;
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public AddResponse get( @RequestBody AddRequest req){
		
		AddValue addReq = new AddValue();
		AddResponse res=new AddResponse();
		addReq.setValue(req.getValue());
		 Client client = ClientProxy.getClient(add);
		 client.getRequestContext().put(Message.ENDPOINT_ADDRESS,"http://localhost:8080/mySoap/services/Add");
		 float resss=add.addValue(addReq.getValue());
		 res.setValue(resss);
		return res;
		
	}
	
	
}

@Configuration
class Config{
	
	@Bean
	public Add getAdd(){
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(Add.class);
		jaxWsProxyFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
		jaxWsProxyFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		jaxWsProxyFactoryBean.setAddress("http://localhost:8080/mySoap/services/Add");
		return (Add) jaxWsProxyFactoryBean.create();
		
	}
	
}


@SpringBootApplication
public class MySoapWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySoapWebClientApplication.class, args);
	}
}
