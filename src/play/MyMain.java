/*
 * Soklong Lim
 * CSE 223/Programming Assignment 3 (20Questions Game)
 * May 15, 2015
 * 
 * Description
 * 		This game is kind of similar to the 20Questions game but without question limitation. First it processes
 * the data from a file, interacts with user in the playing process and writes all the data, including any change
 * from user back to the original file. On the next run, it will remember all the modifications from previous run and 
 * gets even smarter as the running time increases.
 */

package play;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MyMain {
	public static void main(String[] args) throws IOException{
		MyTree yoyo = new MyTree();
		Scanner stream = null;
		
		String database = "C:\\tmp\\simple.txt";	//input file 
				
		try{	//check if file exist
			stream = new Scanner(new File(database));	
		} catch (Exception e){
			System.out.println("Ooops! File Not Found.");
			System.exit(0);	//exit program when file not found
		}
		yoyo.readFrmFile(stream);	//open file and read data from the file
		stream.close();	//close file after reading
	
		yoyo.play();	//playing the game
		
		PrintWriter pw = new PrintWriter(database);
		yoyo.tWriteToFile(pw);	//open file and write data to the file
		pw.close();	//close file after writing
	
	}
}
