package com.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.word.fontfactory.ExtITextFontRegistry;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;

public class GenerateDocx {

	public static void main(String[] args) throws Exception {
		generateDocx();
	}
	
	public static void generateDocx() throws Exception{
		//路径自定义
		String docxSourceFilePath = "E:\\ht\\scf\\任务\\通知书\\应收账款转让通知书-lz005(13).docx";
		String docxTargetFilePath = "E:\\ht\\scf\\任务\\通知书\\test.docx";
		 try {

	        InputStream in =new FileInputStream(new File(docxSourceFilePath));
	        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity );

	        IContext context = report.createContext();
			Map<String,String> map= new HashMap<String,String>();
			Map<String,Object> mutilMap= new HashMap<String,Object>();
			//获取通知书类路径
			map.put(NoticeConstants.NUM, "a111");
			map.put(NoticeConstants.APP_YEAR,"2017");
			map.put(NoticeConstants.APP_MONTH, "12");
			map.put(NoticeConstants.APP_DAY, "23");
			//增加应收账款日期
			map.put(NoticeConstants.START_YEAR, "2017");
			map.put(NoticeConstants.START_MONTH, "12");
			map.put(NoticeConstants.START_DAY, "24");
			map.put(NoticeConstants.BILL_NUM,"1");
			map.put(NoticeConstants.BRCODE_NM, "七里河支行");
			map.put(NoticeConstants.MAST_CONTNO,"GBL123456");
			map.put(NoticeConstants.CNAME,"甘肃建投");
			map.put(NoticeConstants.BUYER_NM,"甘肃六建");
			map.put(NoticeConstants.MONEY, "￥100,000");
			map.put(NoticeConstants.CURCD, "人民币");	//币种
			map.put(NoticeConstants.ACCOUNT_OWNER_NM, "甘肃建投");
			map.put(NoticeConstants.ACCOUNT_NO, "612121212121");
			//map.put(NoticeConstants.BRNAME,accountBrNm);
			map.put(NoticeConstants.BUSS_TYPE, "13");
			map.put(NoticeConstants.OPEN_BANK_NAME, "七里河营业部");	//开户行
			map.put(NoticeConstants.BRNAME, "七里河营业部");
			map.put(NoticeConstants.CONSIGNEE, "cwt1");
			map.put(NoticeConstants.ID_NO, "12121211");	//收件人身份证号
			map.put(NoticeConstants.ADDRESS, "test address");
			map.put(NoticeConstants.POSTCODE, "test postcode");
			map.put(NoticeConstants.PHONE, "18900000000");
			map.put(NoticeConstants.LINKMAN, "cwt2");
			map.put(NoticeConstants.TELEPHONE, "18888888888");
			map.put(NoticeConstants.FAX, "012-1212121"); 
			map.put("testName", "cwt");
			List<NoticeBean>  noticeList = new ArrayList<NoticeBean>();
			List<NoticeBean> sumList=new ArrayList<NoticeBean>();
			for(int i = 0 ; i < 1 ; i++) {
				NoticeBean noticeBean = new NoticeBean();
				noticeBean.setBillsNo("JT-121");
				noticeBean.setBussContcode("SJT-2017");
				noticeBean.setMemo("memo");
				noticeBean.setInsertDate("20171214");
				noticeBean.setBillsDate("20171214");
				noticeBean.setDebtEnd(" ");
				noticeBean.setBillsAmount("3000000");
				noticeBean.setBillsAmountView("3000000");
				noticeBean.setBussContAmt("2000000");
				noticeBean.setBussPayAmt("0");
				noticeBean.setBillsType("发票");
				noticeBean.setBillsAmount("4000000000000000000000000000000000000");
				noticeBean.setBillsAmountView("5000000000000000000000000000000000000");
				
				noticeList.add(noticeBean);
				sumList.add(noticeBean);
			}
			mutilMap.put("noticeList", noticeList);
			mutilMap.put("sumList", sumList);
			
			 if(map!=null){
				 FieldsMetadata metadata = report.createFieldsMetadata();
				 metadata.addFieldAsImage("value6");
				 replaceSingleDateTemplate(context,map);
			 }
			 if(mutilMap!=null){
				 FieldsMetadata metadata = report.createFieldsMetadata();
		            loadMetadata(metadata,mutilMap);
				 replaceListDataTemplate(context,mutilMap);
			 }
			

			//保存数据填充后生成新的文档
	        OutputStream out = new FileOutputStream(new File(docxTargetFilePath));
	        report.process(context, out );
		 }catch(Exception e) {
			    e.printStackTrace();
		 }
		 
		//将目标DOCX文件转换成PDF文件
		 String pdfFilePath = "E:\\ht\\scf\\任务\\通知书\\test.pdf";
		 File pdfFile=new File(pdfFilePath);
		 if(!pdfFile.exists()) {
			 pdfFile.createNewFile();
		 }
		 //convertPdf(docxTargetFilePath,pdfFilePath);
	}
	
	/**
	 * DOCX
	 * 根据docx转换pdf
	 * @param docxFilePath
	 * @param pdfFilePath
	 * @throws Exception
	 */
	public static void convertPdf(String docxFilePath,String pdfFilePath) throws Exception{
		File docxFile=new File(docxFilePath);
		File pdfFile=new File(pdfFilePath);

		//转换pdf文件
		if(docxFile.exists()){
			 
			//if(!pdfFile.exists()){
			InputStream inStream=null;
			OutputStream out = null;
				try {
					inStream=new  FileInputStream(docxFilePath);
					XWPFDocument document = new XWPFDocument(inStream);    
					out = new FileOutputStream(pdfFilePath);
					PdfOptions options = PdfOptions.create();
					ExtITextFontRegistry fontProvider=new ExtITextFontRegistry();
					options.fontProvider(fontProvider);
					PdfConverter.getInstance().convert(document, out, options); 
				} catch (RuntimeException e) {
					e.printStackTrace();
				}finally{
					if(inStream!=null){
						inStream.close();
					}
					if(out!=null){
						out.close();
					}
				}
			//}else{
				//log.info("PDF文件已存在，无需再次转换");
			//}
		}else{
			System.out.println("DOCX文件不存在，无法转换");
		}
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

    public static void  replaceSingleDateTemplate(IXDocReport report,IContext context,Map  singleMap) {
		if(singleMap!=null){
			Set  set = singleMap.entrySet();
		   
		    try {
		        for (Iterator  it = set.iterator(); it.hasNext();) {
		            Map.Entry  entry = (Map.Entry) it.next();
		            	//StringUtils.strToRtf 专为解决中文编码在rtf模版中乱码问题。
		            if(entry.getValue().toString().endsWith(".png")) {
			   	    	 //插入图片
		            	 FieldsMetadata metadata = report.createFieldsMetadata();
			   			 metadata.addFieldAsImage(entry.getKey().toString(),NullImageBehaviour.RemoveImageTemplate);
			   			 IImageProvider provider = new FileImageProvider(new File(entry.getValue().toString()));
			   			 provider.setSize(400f, 400f);
			   	    	 context.put(entry.getKey().toString(), provider);
			   	    	 report.setFieldsMetadata(metadata);
			   	    	 continue;
		            }
		           
		            context.put(entry.getKey().toString(),entry.getValue()!=null?entry.getValue().toString():"");
		        }
		    } catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    /**
     * DOCX
     * 针对List的数据自动填充
     * @param mutilList
     * @param tablesList
     * @throws CommonException
     */
    public static void  replaceListDataTemplate(IContext context,Map map){
		if(map!=null){
		    try {
				Set  set = map.entrySet();
		        for (Iterator  it = set.iterator(); it.hasNext();) {
		                Map.Entry  entry = (Map.Entry) it.next();
		                context.put(entry.getKey().toString(),entry.getValue());
		         }
	        }catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    /**
     * DOCX
     * metadata取得类class
     * @param metadata
     * @param mutilMap
     * @throws CommonException
     */
    public static void  loadMetadata(FieldsMetadata metadata ,Map mutilMap) {
		if(mutilMap!=null){
		    try {
				Set  set = mutilMap.entrySet();
		        for (Iterator  it = set.iterator(); it.hasNext();) {
		                Map.Entry  entry = (Map.Entry) it.next();
		                metadata.load(entry.getKey().toString(),((List) entry.getValue()).get(0).getClass(), true );
		         }
	        }catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
   
}
