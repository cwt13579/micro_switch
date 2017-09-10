package com.pattern.visitor;

public class Apple implements Product
{
  public void accept(Visitor visitor)
  {
      visitor.visit(this);
  }	
}