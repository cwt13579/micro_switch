import java.lang.reflect.Field;


public class OutClass {

	private String name="cwt";
	private Integer value = 20;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public static class InnerStaticClass {
		protected OutClass instance = new OutClass();
		static {
			System.out.println("good");
		}
		
		public  InnerStaticClass print() {
			instance.setName("cwtt");
			System.out.println("print good");
			return this;
		}
		
		public OutClass build() {
			return instance;
		}
	}
	
	public static void main(String args[]) throws Exception {
		OutClass oc = new OutClass.InnerStaticClass().print().build();
		System.out.println(oc.getName());
	    String str = null;
		while(true) {
			Field field = OutClass.class.getDeclaredField("name");
			field.setAccessible(true);
			str = (String) field.get(oc);
			Thread.sleep(400);
			System.out.println(str);
		}
	}
}
