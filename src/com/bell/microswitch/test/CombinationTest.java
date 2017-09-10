
package com.bell.microswitch.test; 

import java.util.ArrayList;
import java.util.List;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月31日 下午2:31:40 

 * 类说明 

 */

public class CombinationTest {
	public static void main(String args[]) {
		CombinationTest test = new CombinationTest();
		List<String> inputList = new ArrayList<String>();
		inputList.add("ABC");
		inputList.add("123");
		inputList.add("abc");
		List<String> resList = test.permutation(inputList);
		for(String item : resList) {
			System.out.println(item);
		}
	}

	public List<String> permutation(List<String> inputList){
	    List<String> resList = new ArrayList<>();
	    permutationInt(inputList, resList, 0, 
	            new char[inputList.size()]);
	    return resList;
	}



	private void permutationInt(List<String> inputList, List<String> resList,
	        int ind, char[] arr) {
	    if(ind == inputList.size()){
	        resList.add(new String(arr));
	        return;
	    }

	    for(char c: inputList.get(ind).toCharArray()){
	        arr[ind] = c;
	        permutationInt(inputList, resList, ind + 1, arr);
	    } 
	}
}
 