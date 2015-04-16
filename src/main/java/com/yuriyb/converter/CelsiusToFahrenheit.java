package com.yuriyb.converter;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

/**
 * CelsiusToFahrenheit class. It provides the ability to convert a temperature from Celsius to Fahrenheit. 
 * It contains one static method for converting. 
 * @version 1.1
 * @author  Yuriy B
 */
public class CelsiusToFahrenheit {

	private static final Logger log = Logger.getLogger(CelsiusToFahrenheit.class);
	
	/**
	 * * static method for value converting
	 * */
	public static float convertCelsiusToFahrenheit(String number) throws SOAPException {
	
	float result = 0;
	
    try {
        String endpoint = "http://www.w3schools.com/webservices/tempconvert.asmx";
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();

        MimeHeaders mimeHeader = message.getMimeHeaders();
        mimeHeader.addHeader("Host", "www.w3schools.com");
        mimeHeader.addHeader("Content-Type", "text/xml; charset=utf-8");
        mimeHeader.addHeader("SOAPAction", "http://www.w3schools.com/webservices/CelsiusToFahrenheit");

        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
        
        SOAPBody body = message.getSOAPBody();
        SOAPElement temp = body.addChildElement("CelsiusToFahrenheit", "","http://www.w3schools.com/webservices/");
        SOAPElement fahr = temp.addChildElement("Celsius");
        fahr.addTextNode(number);

        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage response = connection.call(message, endpoint);
        
        connection.close();
        result = Float.parseFloat(response.getSOAPPart().getEnvelope().getTextContent());
        
        } catch (SOAPException ex) {
    	log.error("The SOAPException has appeared in convertCelsiusToFahrenheit method : "+ex.getMessage());
    	}
    
    return result;
    
    }
}