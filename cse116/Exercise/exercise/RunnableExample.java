package exercise;

public class RunnableExample implements Runnable

{ 
      private int aa;
 
      public RunnableExample(int a){
    	  aa = a;
    	  aa =11*2;
      }
      public void run()

      { 
//    	  Thread th2 = new Thread(new RunnableExample2(2));
//    	  th2.start();
    	 
    	  aa=77;
    	  
      } 
      
      public int getaa(){
    	  return aa;
      }
}