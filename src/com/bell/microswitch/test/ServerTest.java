package com.bell.microswitch.test; 
import com.bell.microswitch.ctl.GeneratorControl;

/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年3月14日 上午10:13:48 

 * 类说明 

 */

public class ServerTest {

	public static void main(String[] args) {
		try{
		Test test = new Test();
		String noticePublishPath =test.setPath();
		System.out.println(noticePublishPath);
		if(noticePublishPath.endsWith("/")){
			noticePublishPath=noticePublishPath.substring(1, noticePublishPath.indexOf("micro_switch")-1);
		}else{
			noticePublishPath=noticePublishPath.substring(1, noticePublishPath.indexOf("micro_switch"));
		}
		System.out.println(noticePublishPath);
		GeneratorControl.init("E:\\ht\\dev\\micro_switch\\resource\\microswitch");
		GeneratorControl.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
 