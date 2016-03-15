/*
 * 		this class doesn't do much on traversing and reading data. it basically
 * call another method from node class. this class is focusing on the playing section.
 * it controls the playing part and make adjustment if user wants to add more data in.
 */


package play;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MyTree {
	Node root;
	Scanner user;
	String input;
	
	public MyTree(){	//constructor
		root = null;
	}
	
	public void makeTree(){							//this a method is the hard-wiring of the data
													//while developing code. it is not used 
		root = new Node("Is it an animal?");		//at this point
		
		root.yes = new Node("Does it have legs?");
		(root.yes).yes = new Node("Elephant");
		(root.yes).yes.question = false;
		
		(root.yes).no = new Node("Snake");
		(root.yes).no.question = false;
			
		root.no = new Node("Is it expensive?");
		(root.no).yes = new Node("Smart Watch");
		(root.no).yes.question = false;

		(root.no).no = new Node("Bacon");
		(root.no).no.question = false;
	}
	
	public void readFrmFile(Scanner stream){	//read method
		
		if(root==null){							//if tree is empty make a node
			root = new Node("");
		}
		root = root.readNode(stream);			//call readNode method frm node class
	}
	
	public void traverseTree(){		//traverse method (not in used at this point)
		if(root==null){	//empty tree
			return;
		}
		root.traverseNode();		//call traverseNode method frm node class
	}
	
	public void tWriteToFile(PrintWriter pw) throws IOException{	//write method
		if(root==null){	//empty tree
			return;
		}
		root.nWriteToFile(pw);	//write to file
	}

	public void play(){		//play method
		Node preNode = root;
		Node tempNode = root;
		
		System.out.println("---Welcome to 20 Questions---\n");	//welcome mseg

		while(true){	//infinite loop until the game it is done
			
			if(tempNode.question){
				System.out.println(tempNode.text);	//question for user
			} else {
				System.out.println("I Think It is ... "+tempNode.text+" ...?");	//final guess
			}
			
			user = new Scanner(System.in);		
			input = user.next();	//catch 'yes/no' frm user
			
			if(input.equalsIgnoreCase("yes")){
				if(tempNode.question){	//question node
					preNode = tempNode;	//remember parent node
					tempNode = tempNode.yes;	//move to 'yes child'
				} else {	//answer node (right guess)
					System.out.println("...gotcha!!! Thanks For Playing.");	//exit mseg
					user.close(); return;
				}
				
			} else if(input.equalsIgnoreCase("no")){
				if(tempNode.question){	//question node
					preNode = tempNode;	//remember parent node
					tempNode = tempNode.no;		//move to 'no child'
				} else {	//answer node (wrong guess)
					System.out.println("what was you thinking?");
					user = new Scanner(System.in);
					input = user.nextLine();	//save new answer frm user
					Node answerFrmUser = new Node(input);	//node for new answer
					answerFrmUser.question = false;	//answer flag

					System.out.println("what question should be answered 'yes' for that?");
					user = new Scanner(System.in);	
					input = user.nextLine();	//save new question frm user
					Node questionFrmUser = new Node(input);

					System.out.println("Thanks, I will remember it.");	//exit mseg

					if(preNode.yes == tempNode){	//change on 'yes-tree'
						preNode.yes = questionFrmUser;	//re-adjust the tree
						questionFrmUser.yes = answerFrmUser;
						questionFrmUser.no = tempNode;
					} else if(preNode.no == tempNode){	//change on 'no-tree'
						preNode.no = questionFrmUser;	//re-adjust the tree
						questionFrmUser.yes = answerFrmUser;
						questionFrmUser.no = tempNode;
					}
					user.close(); return;
				}

			} else {	//typo mseg
				System.out.println("\nplease, make sure you spell 'yes/no' correctly.\n");
			}
		}		
	}
}	