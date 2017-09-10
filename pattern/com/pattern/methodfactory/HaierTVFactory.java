package com.pattern.methodfactory;

public class HaierTVFactory implements TVFactory
{
    public TV produceTV()
    {
    	System.out.println("produceTV HaierTVFactory");
    	return new HaierTV();
    }
}