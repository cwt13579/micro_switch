package com.pattern.methodfactory;

public class HisenseTVFactory implements TVFactory
{
    public TV produceTV()
    {
    	System.out.println("HisenseTVFactory produceTV");
    	return new HisenseTV();
    }
}