package lab7;

public class vMem {
	
	private int size;
	private String[] a;
	
	public vMem(){
		size = 100;
		a = new String[size];
		for(int i=0;i<size;++i){
			this.setV("                    ",i);
		}
	}
	
	public vMem(int n){
		size = n;
		a = new String[size];
		for(int i=0;i<size;++i){
			this.setV("                    ",i);
		}
	}
	
	public void setV(String x, int n){
		a[n]=x;
	}
	
	public String getV(int n){
		return a[n];
	}

}
