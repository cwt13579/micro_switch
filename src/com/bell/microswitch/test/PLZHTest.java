
package com.bell.microswitch.test; 

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.*;
/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月31日 下午1:24:06 

 * 类说明 

 */

public class PLZHTest{
	 public static void main(String...args){
	  ArrayList<String> l=new ArrayList<String>();
	  out.println("请输入要排序的字母,输入$结束:");
	  while(true){
	   Scanner sc=new Scanner(in);
	   String str=sc.nextLine();
	   if(str.equals("$"))break;
	   else 
	    l.add(str);
	    
	  }
	  String s="";
	  System.out.println(s.isEmpty());
	  f(s,l);
	 }
	 static void f(String s,ArrayList<String> l){
	  if(s.isEmpty()){
	   for(int i=0;i<l.size();i++){
	    out.println(s+l.get(i));
	    if(i==l.size()-1)return;
	    f(s+l.get(i),l);
	   }
	  }else{
	   char[]c=new char[1];
	   c[0]=s.charAt(s.length()-1);
	   String str=new String(c);
	   for(int i=l.lastIndexOf(str);i<l.size();i++){
	    out.println(s+l.get(i+1));
	    if(i==l.size()-2)return;
	    f(s+l.get(i+1),l);
	   }
	  }
	 }
	} 
 