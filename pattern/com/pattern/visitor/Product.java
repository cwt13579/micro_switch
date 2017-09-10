package com.pattern.visitor;

public interface Product
{
	void accept(Visitor visitor);
}