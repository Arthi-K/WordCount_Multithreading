# WordCount_Multithreading
Implemented a program to count the frequency of words in a text file. The text file is partitioned into N segments. Each segment is processed by a separate thread that outputs the intermediate frequency count for its segment. The main process waits until all the threads complete; then it computes the consolidated word-frequency data based on the individual threads’ output.

 Documentation Program name: WordCount.java
General Logic : The program accepts N (number of threads to be executed) and file name (the input file from which words are to be counted) as input. The words in the input file is copied into an Arraylist and then split into multiple Arraylist, according to the value of N. The split Arraylist is passed as input to each individual thread. The threads run in a synchronized manner to find the word count. The output of each individual thread is displayed on the terminal whereas the main thread waits till all the threads complete, to write the consolidated output to a file named “Output.txt”.
Main method:
User has to enter the number of threads N and the input file (in our case, “Input.txt”), when prompted. Once this is done, the main method creates an input file stream to get the words from the file one by one and store it in a newly created Arraylist “fileReads”. Then the input file stream is closed and an object is created to invoke the method of the thread class.
ThreadCreation method:
This method accepts the number of threads “N” and Arraylist “fileReads”. It then proceeds to split the “fileReads” into multiple smaller Arraylists “splitReads", to be passed to each individual thread. The size of the fileReads is stored in a variable called “size”. Two Arraylists are created to store the words and their counts. “n” which is the number of words from “fileReads” is found by dividing size of the fileReads- “size” with the number of threads “N”. The number of remaining words is stored in “remaining”. A “for” loop is implemented to run for “N” number of threads. The “fileReads” is split inside the for loop by assigning variables start with 0 and end with start + n + remaining. The start is then replaced with the end value and end value will be replaced by the start + n + remaining in the next loop, but remaining will be 0 in the second loop because it is passed to the first thread and assigned 0, at the end of first loop.
The “splitReads” is passed as input to the thread method and iterated by using Iterator. Two Arraylists to store the words and their counts, which were created earlier is used here. It first checks if “words” Arraylist contains the words read from “splitReads”, if it does only the corresponding count is incremented by 1. Else, if the “words” Arraylist does not contain the words read from “splitReads”, it is added to the list and corresponding count is increased by 1. The output of each individual thread is printed on the terminal. The main thread waits for all the threads to complete execution before

 writing the consolidated output to file named “output.txt” using the “FileWriter” class. The output in the file contains the words and their corresponding count values.
Compilation and execution:
Extract the contents of the Zip file onto a folder. Open the terminal and go to the current directory in which the files are saved by using “cd” command. Then use the following command for compiling and executing the file:
javac WordCount.java
java WordCount
Then enter the number of threads to be executed and also the input file name.
The output of each individual thread is displayed on the terminal, whereas the consolidated output is written to the text file “Output.txt”
The “output.txt” file contains the consolidated output of all the threads
 
 
 
