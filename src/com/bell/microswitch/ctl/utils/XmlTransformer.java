package com.bell.microswitch.ctl.utils;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlTransformer {
	public static String format(String templateStr, String data) throws TransformerException {
		StringWriter writer = new StringWriter();
			
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    
	    Reader reader=new StringReader(templateStr.trim());
	    
	    Templates template=null;
		try {
			template = tFactory.newTemplates(new StreamSource(reader));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    Transformer xformer = template.newTransformer();
	    
	    Reader msgReader=new StringReader(data);
	    Source source = new StreamSource(msgReader);
	    
        Result result = new StreamResult(writer);

        xformer.transform(source, result);
            
		return writer.toString();
	}


}
