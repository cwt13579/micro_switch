
package com.bell.microswitch.test; 

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月31日 上午10:01:22 

 * 类说明 

 */

import java.math.BigInteger;
import java.util.Scanner;
//斐波那契堆1~200
//由于当N较大时（如N=200时），所得的结果会超过Integer和Long,所以本题求解需要用到BigInteger
public class FibonacciHeap {
    int max = 201;
    BigInteger [] fun = new BigInteger[max];

    public static void main(String args[]) {
        FibonacciHeap m = new FibonacciHeap();
        m.getInput();
    }

    public void getInput() {
        Scanner cin = new Scanner(System.in);
        getSequence();
        while (cin.hasNext()) {
                int N = cin.nextInt();
                if(N >= 1 && N <= 200)
                    System.out.println(fun[N]);
                else 
                    System.out.println();

        }
        cin.close();
    }
    public void getSequence(){
        fun[0] = new BigInteger("1");
        fun[1] = new BigInteger("1");
        for(int i = 2; i < max; i++){
            fun[i] = (fun[i - 1]).add( fun[i - 2]);
        }
    }
}
 