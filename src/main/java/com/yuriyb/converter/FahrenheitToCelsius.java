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

/**
 * FahrenheitToCelsius class. It provides the ability to convert a temperature from Fahrenheit to Celsius. 
 * It contains one static method for converting. 
 * @version 1.1
 * @author  yuriy2204
 */
public class FahrenheitToCelsius {

/**
* static method for value converting
*/
public static float convertFahrenheitToCelsius(String number) throws SOAPException {
	
	float result = 0;
   
	try {
		String endpoint = "http://www.w3schools.com/webservices/tempconvert.asmx";
		MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();

        MimeHeaders mimeHeader = message.getMimeHeaders();
        mimeHeader.addHeader("Host", "www.w3schools.com");
        mimeHeader.addHeader("Content-Type", "text/xml; charset=utf-8");
        mimeHeader.addHeader("SOAPAction", "http://www.w3schools.com/webservices/FahrenheitToCelsius");

        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        envelope.addNamespaceDeclaration("soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");

        SOAPBody body = message.getSOAPBody();
        SOAPElement temp = body.addChildElement("FahrenheitToCelsius", "","http://www.w3schools.com/webservices/");

        SOAPElement fahr = temp.addChildElement("Fahrenheit");
        fahr.addTextNode(number);

        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage response = connection.call(message, endpoint);
        connection.close();
        
        result = Float.parseFloat(response.getSOAPPart().getEnvelope().getTextContent());
       } catch (SOAPException ex) {
        ex.printStackTrace();
       }
	return result;
}

}
