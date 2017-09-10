package com.outofmemory;

public class CalcuteSin {

	public static void main(String args[]) {
		CalcuteSin sin = new CalcuteSin();
		sin.threadCalc(6);
	}
	
	 public void threadCalc(int seconds)
	  {
	    double x = 0;
	    double start = 0D;
	    long t0 = System.currentTimeMillis();

	    System.out.println((System.currentTimeMillis() - t0) / 1000L);
	    while ((System.currentTimeMillis() - t0) / 1000L < seconds)
	      x = Math.sin(start++);
	  }
}
