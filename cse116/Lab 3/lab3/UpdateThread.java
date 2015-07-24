package lab3;

import java.io.IOException;

public class UpdateThread extends Thread{
	

	private String Description = "";
	private int location;

	
	public UpdateThread() throws IOException{

	}
	

	public void run(){
		
		while(true){
			try {
				Thread.sleep(800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Description = Main._queue.MTGet();
			if(Description != null){
				for(int i = 0; i < 7; i++){
					if(Description.charAt(i) != ' '){
						location = Integer.parseInt(Description.substring(i,7));
						break;
					}
				}
		
				try {
					Main._FS.writeIn(location, Description);	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

