package com.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * leaf  component  composite
 * @author lijin
 *
 */
public  class Composite  extends Component{

	private List<Component> children = new ArrayList<Component>();
	@Override
	public void add(Component c) {
		children.add(c);
	}

	@Override
	public void remove(Component c) {
		children.remove(c);
		
	}

	@Override
	public Component getChild(int i) {
		return children.get(i);
	}

	@Override
	public void operation() {
		 for(Component cpt : children) {
			 cpt.operation();
		 }
	}

}