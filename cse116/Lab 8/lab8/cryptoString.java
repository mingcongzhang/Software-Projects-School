package lab8;

public class cryptoString {
	private String _letter= "XYZABCDEFGHIJKLMNOPQRSTUVWXYZABC";
	private String _letter2= "xyzabcdefghijkmnopqrstuvwxyzabc";
	private String _num= "8901234567890123";
	private String s;
	private static int sizeL=29;
	private static int sizeN=13;
	public cryptoString(String a){
		s= a;
	}
	public String encrypt(){
		for(int i=3;i<sizeL;++i){
			if(s.equals(_letter.substring(i,i+1))){
				return _letter.substring(i+3,i+4);
			}
		}
		for(int i=3;i<sizeL;++i){
			if(s.equals(_letter2.substring(i,i+1))){
				return _letter2.substring(i+3,i+4);
			}
		}
		for(int i=3;i<sizeN;++i){
			if(s.equals(_num.substring(i,i+1))){
				return _num.substring(i+3,i+4);
			}
		}
		return "?";
	}
	public String decrypt(){
		for(int i=3;i<sizeL;++i){
			if(s.equals(_letter.substring(i,i+1))){
				return _letter.substring(i-3,i-2);
			}
		}
		for(int i=3;i<sizeL;++i){
			if(s.equals(_letter2.substring(i,i+1))){
				return _letter2.substring(i-3,i-2);
			}
		}
		for(int i=3;i<sizeN;++i){
			if(s.equals(_num.substring(i,i+1))){
				return _num.substring(i-3,i-2);
			}
		}
		return " ";
	}
}
