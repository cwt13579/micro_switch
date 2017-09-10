package com.pattern.templatemethod;

public class Withdraw extends BankTemplateMethod
{
	public void transact()
	{
		System.out.println("取款");		
	}
}