package exercise;

public class RunnableExample2 implements Runnable

{ 
      public RunnableExample2(int a){
      }
      public void run()

      { 
//    	  Thread th1 = new Thread(new RunnableExample(2));
//    	  th1.start();
    	  try{
           for(int i = 0; i < 500; i++)

           { 
        	  
        	   //JOptionPane.showMessageDialog(null, "Thread " + threadName + aa +" : " + i);
        	  
        	   System.out.println("Thread 2");
        	  
               
               Thread.sleep(500);
//               th1.join();
        	   }
           
    	  }
    	  catch(InterruptedException ex){
    		  
    	  }
      } 
}