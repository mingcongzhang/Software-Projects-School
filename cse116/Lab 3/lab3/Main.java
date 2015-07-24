package lab3;

import java.io.*;

public class Main {
	
	static MTQueue _queue;
	static FileStore _FS;
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		_FS = new FileStore();
		_queue = new MTQueue();
		UpdateThread UT = new UpdateThread();
		UserInputThread UIT = new UserInputThread();
		UIT.start();
		UT.start();	
	}
}

