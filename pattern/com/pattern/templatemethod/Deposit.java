package com.pattern.templatemethod;

public class Deposit extends BankTemplateMethod
{
	public void transact()
	{
		System.out.println("存款");		
	}
}