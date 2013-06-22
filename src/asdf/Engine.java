import java.lang.*;  
import java.io.*;  
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;
import java.util.Scanner;

public class Engine  
{  
        static Logger log = Logger.getLogger(Engine.class.getName());
        /* Logger class is used for logging the information in the file.*/
       static long avgTime=0;
       /* avgTime is used to calculate the average time for specified Processes.*/
       static int count=0;
       /*count specified the process for e.g.200.*/
       static int logProcesses=0;
       /*logProcesses is the given value after which we have to log the information about average time.*/
 /* Below function craete a pool of Processes.*/
       public void initialize(int totalProcess,int activeProcess,int logProcess)  
    {  
        
        ExecutorService executor = Executors.newFixedThreadPool(activeProcess);//C:\\Users\\Gaurav\\Documents\\NetBeansProjects\\asdf\\src\\asdf\\jquery-google-maps-fusion-tables.html
        for (int i = 0; i < totalProcess; i++) {
        /* these are the threads which creats processes*/
            Runnable worker = new testing("cmd /c start C:\\Users\\Gaurav\\Documents\\NetBeansProjects\\asdf\\src\\asdf\\jquery-google-maps-fusion-tables.html",i);
           /* jquery-google-maps-fusion-tables.html is a file which performs Google based Api task*/
            /*Note---Give Your Workspace path as a first parameter here. for file jquery-google-maps-fusion-tables.html */
            executor.execute(worker);
            
            
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        }  
  
    public static void main(String[] args)  
    {  
        int totalProcess;
        int activeProcess;
        int logProcess;
        Scanner in = new Scanner(System.in);
 /* here  we take input from console where u can give total number of processes and etc.*/
      System.out.println("Enter Total number of Processes");
      totalProcess = in.nextInt();
      
      System.out.println("Enter number of Active Processes");
      activeProcess = in.nextInt();
      
      System.out.println("Enter number of Processes for Average time");
      logProcess = in.nextInt();
      logProcesses=logProcess;
      new Engine().initialize(totalProcess,activeProcess,logProcess);  
    }  
  
    class testing implements Runnable  
    {  
        String name;
        int processNo;
        testing(String name,int processNo)  
        {  this.processNo=processNo;
            this.name = name;  
          }  
   public void run()  
   {   
       try  
     {  

   FileHandler fh = new FileHandler("TestLog.log");
   fh.setFormatter(new SimpleFormatter());
   log = Logger.getLogger("TestLog");
   log.addHandler(fh);
   long time;
   time = System.currentTimeMillis();
   Runtime rt = Runtime.getRuntime();  
                Process p = rt.
                        exec(this.name);  
                try  
                {  
                    p.waitFor();  
                }  
                catch(Exception e)  
                {  
                    e.printStackTrace();  
                }     
 
  long time1 = System.currentTimeMillis() - time;
  avgTime+=time1;
  int abc=++count;
  log.log(Level.INFO," The Process "+ this.processNo +" start at " + time + " milliseconds and End at " + System.currentTimeMillis() + " milliseconds and took " + time1 + " milliseconds.");
  
  
  
  if(abc==logProcesses)
  {
  long avg=avgTime/logProcesses;
  log.log(Level.INFO,logProcesses + "Processes took   "+ avg +" milliseconds per Process. ");
   avgTime=0;
   count=0;
  }
            }  
            catch(Exception e)  
            {  
                e.printStackTrace();  
            }     
        }  
    }  
}  