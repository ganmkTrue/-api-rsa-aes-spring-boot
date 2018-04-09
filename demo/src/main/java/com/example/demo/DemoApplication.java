package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import  java.text.SimpleDateFormat;
import  java.util.Date;

@Controller
@SpringBootApplication
@Configuration
public class DemoApplication extends SpringBootServletInitializer {

	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello world！";
	}

	@RequestMapping("GetTime")
	@ResponseBody
	public  String GetTime(String s)
	{
		String str="";
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
			Date date=sd.parse(s);
			str=sdf.format(date);
		}
		catch(Exception e)
		{
			//LoggerInfo.logerror.error(e.getMessage());
			//System.out.println(e.getMessage());
			return str;
		}
		return str;
	}

	@RequestMapping("Encr")
	@ResponseBody
	public  String ReturnEncr(String sSrc)
	{

		//String sSrc="11111111111111111111111111111";
		String sKey="v7EmW3OLTI4BS/RImWp/qg==";
		String privateKey="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlc/oCyuO1Jp7CYNpO1jJGRcMwbrPACYzjOJNMDhMNWsJmO8xpGVIm+rV9cR9lFp5UqiBBwOzyNkJ4JjbkI5K5wIDAQABAkAW9IZ/v6rhFNKpgINNXqthttBzluZFBZV8juh47QdwX7opJ1+a3xkEGk0bHKR6w5IxTmuj1/PeFp8Z0UMkR/2RAiEA/+/l+uCFHLjmVThBI2IP2t4aHXS/MUlZ1+cZlBG/qzsCIQCV2VTbi5HCD36iWtyjlIo5lTj2qKkpyBaXyDxPdfUsRQIhAL2lU/LMP5aC5Botrwa6+Sii/xLomWu6AJrdIbT4WeBVAiAjK61lEi8V0zkvtg/PPQ/sT//ctN1vdw0GdvLg25i92QIgCrYvk4uPoBpaRhsdmaTJGOx7us0ZnnFhJ87hkOqYHH8=";

		String s= new CommonMesSigEncr().ToMesEncr(sSrc,sKey);

		String j=new CommonMesSigEncr().ToSigEncr(sSrc,privateKey);

		String json="{\"Mes\":\""+s+"\",\n" +
				"\"Sig\":\""+j+"\"}";

		return  json;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return  builder.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		System.out.println("server is running at 8080....");
		SpringApplication.run(DemoApplication.class, args);
	}
}
