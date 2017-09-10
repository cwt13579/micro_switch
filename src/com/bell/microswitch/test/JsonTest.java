
package com.bell.microswitch.test; 

import com.bell.buss.bean.TestInputBean;
import com.google.gson.Gson;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月14日 下午8:48:24 

 * 类说明 

 */

public class JsonTest {

	public static void main(String args[]) {
		Gson gson = new Gson();
		String jsonStr = "{\"headBean\": { \"ver_no\":\"001\",\"snd_chnl_no\":\"29\"},\"version\":\"10\",\"xtype\":\"10\",\"xclass\":\"1\",\"pan\":\"1111111111111111119\",\"merchant\": {\"acqBIN\":\"1\",\"merID\":\"1\",\"password\":\"11111118\",\"name\":\"name\",\"country\":\"CNY\"}}";
		TestInputBean bean = gson.fromJson(jsonStr, TestInputBean.class);
		System.out.println(bean);
	}
}
 