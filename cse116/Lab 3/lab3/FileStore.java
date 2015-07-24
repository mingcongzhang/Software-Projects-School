package lab3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileStore {
	
	private static RandomAccessFile store;
	final private static int MAX_RECORD_NUMBER = 10;
	final private static int RECORD_LENGTH = 46;
	
	public FileStore() throws IOException{
		File loc = new File("C:\\Users\\Alan with Talent\\Desktop\\test.txt");
		store = new RandomAccessFile(loc, "rw");
		String Dummy = "      1                            None None";
		for (int i = 0; i<MAX_RECORD_NUMBER; ++i){
			store.seek((RECORD_LENGTH+2)*i);
			store.writeUTF(Dummy);
		}
		
	}
	
	public void writeIn(int loc, String des) throws IOException {
		System.out.println(des+" is stored in file");
		store.seek((RECORD_LENGTH+2)*(loc-1));
		store.writeUTF(des);
	}
	
	public String readOut(int loc) throws IOException{
		store.seek((RECORD_LENGTH+2)*(loc-1));
		String readOut = store.readUTF();
		System.out.println(readOut+" is retrieved in file");
		return readOut;
	}

}

