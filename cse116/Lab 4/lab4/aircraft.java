package lab4;

public class aircraft {
	
	private String value;
	public aircraft next = null;
	
	public aircraft(String v){
		value = v;
	}
	
	public String get(){
		return value;
	}
	
	public void set(String v){
		value = v;
	}

}
