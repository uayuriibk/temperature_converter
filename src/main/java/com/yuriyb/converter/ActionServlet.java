package com.yuriyb.converter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * ActionServlet class. It provides the ability to process http-requests. 
 * It contains methods for processing of post/get requests. 
 * @version 1.1 
 * @author  yuriy2204
 */
public class ActionServlet extends HttpServlet {
	
 private static final long serialVersionUID = 1L;
 
 private static final Logger log = Logger.getLogger(ActionServlet.class);
 
/**
* constructor of ActionServlet
*/
 public ActionServlet() {
        // TODO Auto-generated constructor stub
 }

/**
* Method for get-request processing
*/
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
 }
 
 /**
 * Method for post-request processing
 */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  String number = null;
	  String resultScale = null;
	  JSONObject json = null;
	  float result = 0;
	  
	  number = request.getParameter("number");	  
	  resultScale =request.getParameter("scale");
	  
	  if(resultScale.equals("celsius")){
		try {
			  if (number!=null) result = FahrenheitToCelsius.convertFahrenheitToCelsius(number);
		} catch (SOAPException e) {
			log.error("The SOAPException in doPost method during conversion to Celsius : "+e.getMessage());	
		}	  
	  }
	  else {
		try {
			  if (number!=null) result = CelsiusToFahrenheit.convertCelsiusToFahrenheit(number);
		} catch (SOAPException e) {
			log.error("The SOAPException in doPost method during conversion to Fahrenheits : "+e.getMessage());			
		}  
	  }
		   
	  response.setContentType("application/json");  
	  response.setCharacterEncoding("UTF-8");
	  
      try {
    	json = new JSONObject();  
		json.put("result", Float.toString(result));
	  } catch (JSONException e) {
		  log.error("JSONException during conversion JSON : "+e.getMessage());
	  }
      
      response.getWriter().print(json);
 }    
}
