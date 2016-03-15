/*
 * 		this is a tree-node class that each node holds a text field,
 * pointer to two children, yes and no child, and the question flag.
 * 		it contains three methods two of which are for reading input
 * from the file and writing the data back to the original file, and
 * the last one is for traversal. Traverse method doesn't do much at this 
 * point and was used for testing the output format while developing the program. 
 */

package play;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Node {
	String text;
	Node yes, no;
	boolean question;
	
	int QFlag;	//flag to check if the input file start with question
	
	public Node(String text){	//constructor
		this.text = text;
		yes = no = null;
		question = true;
	}
	
	public void traverseNode(){		//traverse method (use while developing program, especially for 
									//testing output format. (Not in use at this point)
		System.out.println(text);	//NLF order traversal
		
		if(yes!=null){
			yes.traverseNode();
		}
		if(no!=null){
			no.traverseNode();
		}
	}
	
	public void nWriteToFile(PrintWriter pw) throws IOException{	//write data to the file 
	
		if(question){
			pw.write("Q:\n");	//write question
			pw.write(text+"\n");

			yes.nWriteToFile(pw);	//recursion
			no.nWriteToFile(pw);	//
			return;
		} else {
			pw.write("A:\n");	//write answer
			pw.write(text+"\n");
			return;
		}
	}
	
	public Node readNode(Scanner stream){	//read data from the file
	
		String input = stream.nextLine();	//catch 'Q: or A:'

		if(input.equalsIgnoreCase("Q:")!=true && QFlag==0){		//check if the file starts 
			System.out.println("Ooops! File Corrupted");		//with question
			System.exit(0);	//exit program
		}
		
		Node root = new Node(stream.nextLine());	//catch text
		
		if(input.equals("A:")){
			root.question = false;	//change question flag
			return root;
		}
		QFlag++;	//file start with question flag

		try{	//checking for file format error
			root.yes = readNode(stream);	//recursion
			root.no = readNode(stream);		//
		} catch(Exception e){
			System.out.println("Ooops! File Corrupted");
			System.exit(0);	//exit program
		}
		return root;
	}
}
