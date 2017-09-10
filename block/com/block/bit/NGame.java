package com.block.bit;

import java.util.Scanner;

public class NGame {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in); // reads from console
		long N = Long.parseLong(in.next());
		boolean whoWins = (Long.bitCount(N-1)&1) != 0;
		System.out.println(whoWins? "A" : "B");
	}
}
