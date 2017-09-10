
package com.bell.microswitch.test; 

import java.math.BigDecimal;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2016年12月6日 下午8:43:05 

 * 类说明 

 */

public class Test {

	public String setPath() throws Exception{
		return this.getClass().getResource("").getPath();
	}
	
	public static void main(String[] args) {
		String str = "1221|+|中国|+|dd";
		System.out.println(str.split("\\|\\+\\|")[2]);
		
		BigDecimal num1 = new BigDecimal("1000.00");
		num1 = num1.multiply(new BigDecimal("40.00"));
		System.out.println(num1);
		num1 = num1.multiply(new BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(num1);
	}
}
 