package lab4;

public class ATCControl {
	final private int INITIAL_ALLOCATION = 10;
	private aircraft tail = new aircraft("");
	private aircraft head = new aircraft("");
	private int size = INITIAL_ALLOCATION;
	
	public ATCControl(int n){
		tail.next = tail;
		head.next = tail;
		size = n;
		aircraft temp = head;
		for(int i = 0;i<size;++i){
			temp.next = new aircraft("");
			temp = temp.next;
		}
		temp.next = tail;
	}
	
	public ATCControl(){
		tail.next = tail;
		head.next = tail;
		aircraft temp = head;
		for(int i=0;i<size;++i){
			temp.next = new aircraft("");
			temp = temp.next;
		}
		temp.next = tail;
	}
	
	public String get(int index) throws IndexOutOfBoundsException{
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index >= size){
			fillTo(index);
		}
		aircraft temp = head;
		for(int i = 0; i <= index;++i){
			temp = temp.next;
		}
		return temp.get();
	}
	
	public void put(String value, int index) throws IndexOutOfBoundsException{
		if(index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index >= size){
			fillTo(index);
		}
		aircraft temp = head;
		for(int i = 0;i <= index; ++i){
			temp = temp.next;
		}
		temp.set(value);
	}
	
	private void fillTo(int index){
		aircraft temp = head;
		while(temp.next != tail){
			temp = temp.next;
		}
		while(size <= index){
			temp.next = new aircraft("");
			temp = temp.next;
			size += 1;
		}
		temp.next = tail;
	}

}
