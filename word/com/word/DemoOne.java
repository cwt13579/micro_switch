package com.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class DemoOne {

	public static void main(String args[]) throws Exception {
		String docxSourceFilePath = "E:\\ht\\scf\\任务\\通知书\\template.docx";
		String docxTargetFilePath = "E:\\ht\\scf\\任务\\通知书\\test.docx";
		InputStream in =new FileInputStream(new File(docxSourceFilePath));
	    IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity );

	    IContext context = report.createContext();
	    Map<String,Object> map= new HashMap<String,Object>();
	    
	    map.put("value1", "王小波");
	    map.put("value2", "申亚科技");
	    map.put("value3", "a333");
	    map.put("value4", "四川省成都市金牛区交大路266号");//address1
	    map.put("value5", "四川省成都市天府新区天府五街13号");//address2
//	    map.put("value6", "D:/pictures/titan-elasticsearch.png");//image
//	    map.put("value7", "D:/pictures/shopping3.png");//image
	    map.put("value88", "false");
	    map.put("value99", "true");
	    FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	    if(map!=null){
	    	 replaceSingleDateTemplate(context,map);
	    	 fieldsMetadata.addFieldAsImage("value6");
	    	 fieldsMetadata.addFieldAsImage("value7");
	    	 fieldsMetadata.addFieldAsImage("value8");
	    	 fieldsMetadata.addFieldAsImage("value9");
	    	 report.setFieldsMetadata(fieldsMetadata);

	    	 IImageProvider value6 = new FileImageProvider(new File("D:/pictures/titan-elasticsearch.png"));
	    	 context.put("value6", value6);

	    	 IImageProvider value7 = new FileImageProvider(new File("D:/pictures/shopping3.png"));
	    	 context.put("value7", value7);
	    	 
	    	 IImageProvider value8 = new FileImageProvider(new File("D:/pictures/rYBZVrq.png"));
	    	 context.put("value8", value8);

	    	 IImageProvider value9 = new FileImageProvider(new File("D:/pictures/eQJ32y6.png"));
	    	 context.put("value9", value9);
		}
	    
	    
	    
	  //保存数据填充后生成新的文档
        OutputStream out = new FileOutputStream(new File(docxTargetFilePath));
        report.process(context, out );
	}
	
	/**	
     * DOCX
     * 针对单个文本占位符的数据自动填充
     * @param template
     * @param singleMap
     * @throws CommonException
     */
    public static void  replaceSingleDateTemplate(IContext context,Map  singleMap) {
		if(singleMap!=null){
			Set  set = singleMap.entrySet();
		    try {
		        for (Iterator  it = set.iterator(); it.hasNext();) {
		            Map.Entry  entry = (Map.Entry) it.next();
		            	//StringUtils.strToRtf 专为解决中文编码在rtf模版中乱码问题。
		            context.put(entry.getKey().toString(),entry.getValue()!=null?entry.getValue().toString():"");
		        }
		    } catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
