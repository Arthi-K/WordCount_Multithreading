import static java.lang.Math.ceil;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.Thread;

public class WordCount{
    public static void main(String[] args) throws IOException {
    
        //Get the value of N "the number of threads"
        System.out.println("Enter the number of threads N:");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        //Get input file name from user
        System.out.println("Enter name of the input file:");
        Scanner keyboard = new Scanner(System.in);
        String filename = keyboard.nextLine();

        //Create input stream and scanner
        FileInputStream fin = new FileInputStream(filename);
        Scanner fileInput = new Scanner(fin);
        
        //Create an Arraylist to store the words
        ArrayList<String> fileReads = new ArrayList<String> ();
       
        //Store the words into the ArrayList
        while(fileInput.hasNext()){
            String nextRead = fileInput.next();
            fileReads.add(nextRead);
        }

        //Close the input file stream
        fileInput.close();
        fin.close();

        //Create an object to invoke method of the thread class
        WordCount wc = new WordCount();
        wc.threadCreation(N, fileReads);
        
    }

    public void threadCreation(int N, ArrayList<String> fileReads){
        int start = 0;
        int size = fileReads.size();    //size of the arraylist to be split 
        
        //Arraylists to store the words and their counts
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        
        int n = (int)ceil(size/N);   //number of words to be passed to the thread
        int remaining = size % N;          //number of remaining words 
        
        for (int x=0; x < N; x++)   //run this loop for "N" threads
       {
         ArrayList<String> splitReads = new ArrayList<String> ();

         //Spliting the "reads" ArrayList to "copyRead" ArrayList to pass to the thread 
         int end = start + n + remaining;     
         for(int j= start; j < end; j++)
          {
            splitReads.add(fileReads.get(j));
          }
        
        start = end;
        remaining = 0;    
         
        //thread method for word count
        Thread t = new Thread(new Runnable() 
        {
          public synchronized void run(){  
        
            //Iterate through the copyRead ArrayList
            Iterator a = splitReads.iterator();
            //Read through file and find the words
            while(a.hasNext())
              {
              // Get the next word
              String nextWord = (String)a.next();

              // Determine if the word is in the Arraylist
              if(words.contains(nextWord))
                {
                int index = words.indexOf(nextWord);
                count.set(index, count.get(index)+1);
                }
              //otherwise add the word to the arraylist and add count plus one
              else{
                words.add(nextWord);
                count.add(1);
               }
               }
               //Displaying the output of each thread 
               System.out.println("The output from each individual thread is displayed here, while consolidated output is written to a file:\n");
               for(int d = 0; d < words.size(); d++)
               {
                System.out.println(words.get(d)+ " occurred " +count.get(d)+ " times\n"); 
               }
               System.out.println("----------------------------------------------------");
          }
        });
        t.start();       //Begin execution of the thread
        try {
            t.join();     //Waits for all the threads to complete their execution
            } 
        catch (InterruptedException e) {
            e.printStackTrace();
            }
       }
       try {
        //Write the consolidated output to a file 
        FileWriter myWriter = new FileWriter("output.txt");
           for(int i=0; i < words.size(); i++)
            {
              myWriter.write(words.get(i)+ " occurred " +count.get(i)+ " times\n"); 
            }
        myWriter.close();
        }
       catch(IOException e){
          e.printStackTrace();
          }
    }
    }


