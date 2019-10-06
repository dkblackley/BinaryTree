/**
 * 
 */

import java.util.Scanner;

/**
 * 
 * Basic menu class used for testing  and using Binary tree
 * 
 * @author Daniel Blackley -- 160007728
 *
 */


public class Menu {
	
	private Node Students;
	private InputOutput helper;
	private boolean treeExists;
	
	public Menu() {
		Students = new Node();
		helper = new InputOutput();
		treeExists = false;
		
	}
	
	public static void main(String[] args) {
		
		Menu runProgram = new Menu();
		
		System.out.println("Please select what you would like to do: ");
		
		runProgram.mainMenu();
		
		System.out.println("Quitting program...");
		
	}
	
	/**
	 * Standard Menu method, uses InputOutput to deal with an exceptions thrown by the user
	 */
	public void mainMenu() {
		
		boolean quit = false;
		Integer userInput;
		
		while (!quit) {
			
			System.out.println("Input 1 to Run tests.");
			System.out.println("Input 2 to add a Student to the tree");
			System.out.println("Input 3 to Print the tree");
			System.out.println("Input 4 to Find a Student");
			System.out.println("Input 5 to remove a Student");
			System.out.println("Input 6 to Clear the tree");
			System.out.println("Input 7 to Save the tree");
			System.out.println("Input 8 to load the tree ");
			System.out.println("Input 9 to quit \n\n");
			
			userInput = this.helper.validateIntegerInput(8);
			
			
			if(userInput == 1) {
				this.allTests();
				
			} else if (userInput == 2) {
				this.addNode();
				
			} else if (userInput == 3) {
				
				System.out.println("How would you like to print the Tree?");
				System.out.println("Input 1 for In Order");
				System.out.println("Input 2 for Post Order");
				System.out.println("Input 3 for Pre Order");
				
				userInput = this.helper.validateIntegerInput(3);
				
				if(userInput == 1) {
					
					this.inOrder();
					
					
				} else if (userInput == 2) {
					
					this.postOrder();
					
				} else if (userInput == 3) {
					
					this.preOrder();
					
				} else {
					System.out.println("Unidentified Input, returning to menu...");   //this shouldn't be called due to helper class not allowing for a range of values > 3
				}
			} else if (userInput == 4) {
				this.findStudent();
				
			} else if (userInput == 5) {
				this.removeStudent();
				
			} else if (userInput == 6) {
				
				this.Students = null;
				treeExists = false;
				
				System.out.println("Tree cleared.");
				
			} else if (userInput == 7) {
				this.saveTree();

			} else if (userInput == 8) {
				this.loadTree();
				
			} else if (userInput == 9) {
				quit = true;
				
			} else {
				System.out.println("Unidentified Input please rety...");
			}
			
		}
	}
	
	/**
	 * Usie the InputOutput class to load in the tree
	 */
	public void loadTree() {

			System.out.println("Please enter the name of the file that you wish to load: ");
			
			String fileName = this.helper.validateStringInput();
			
			this.Students = this.helper.readFromFile(fileName);
			
			
			if (this.Students != null) {
				treeExists = true;
			}

	}
	
	/**
	 * Save the tree using the inputOutput class
	 */
	public void saveTree() {
		
		if (treeExists) {    //make sure we don't save a null tree
			
			System.out.println("Please enter the name under which you want to save your file: ");
			String fileName = this.helper.validateStringInput();
			
			this.helper.setupFileSaving(fileName, this.Students);
			
		} else {
			System.out.println("Tree doesn't exist, returning to Menu...");
		}
		
	}
	
	/**
	 * Calls the Node's removeStudent method
	 */
	public void removeStudent() {
		
		if (treeExists) {
			
			System.out.println("Please Enter the ID of the student you want to delete: ");
			
			int ID = this.helper.validateIntegerInput(9999);
			
			this.Students.removeStudent(ID);
			
		} else {
			System.out.println("Tree doesn't exist, returning to Menu...");
		}
		
		
	}
	/**
	 * Uses InputOutput to validate input then uses the Node's findDetail method
	 * to find and display the student
	 */
	public void findStudent() {
		
		if (treeExists) {
			
			System.out.println("Please enter the Id of the student you wish to find: ");
			
			int ID = this.helper.validateIntegerInput(9999);
			
			this.Students.findDetail(ID);
		} else {
			System.out.println("Tree doesn't exist, returning to Menu...");
		}
		
	}
	/**
	 * Prints tree in order
	 */
	public void inOrder() {
		
		if (treeExists) {
			this.Students.treeInOrder();
		} else {
			System.out.println("Tree doesn't exist, returning to Menu");
		}
	}
	/**
	 * Prints tree in PreOrder
	 */
	public void preOrder() {
		
		if (treeExists) {
			this.Students.treePreOrder();
		} else {
			System.out.println("Tree doesn't exist, returning to Menu");
		}
	}
	/**
	 * prints tree in postOrder
	 */
	public void postOrder() {
		
		if (treeExists) {
			this.Students.treePostOrder();
		} else {
			System.out.println("Tree doesn't exist, returning to Menu");
		}
	}
	
	/**
	 * uses InputOutput class to validate all input and then call's the relevant node methods
	 * to set the students details
	 */
	public void addNode() {
		
		int ID, mark;
		String name;
		
		System.out.println("Please Insert the Students ID: ");
		
		ID = this.helper.validateIntegerInput(9999);
		
		System.out.println("Please Insert the Students name: ");
		
		name = this.helper.validateStringInput();
		
		System.out.println("Please Insert the Students mark: ");
		
		mark = this.helper.validateIntegerInput(100);
		
		if (treeExists == false) {    //if the tree didn't exist, call initialNode instead of insertNode
			
			this.Students.initialNode(ID, mark, name);
			treeExists = true;
			
		} else {
			
			this.Students.insertNode(ID, mark, name);
			
		}
	}
	
	/**
	 * run all tests I used, this was for my own convenience while I was programmin, I left it in
	 * so that others could use it aswell
	 */
	public void allTests() {
		this.test1();
		this.test2();
		this.test3();
		this.test4();
	}
	
	
	/**
	 * basic tests of adding then displaying students
	 */
	public void test1() {
		this.Students.initialNode(4367, 69, "Daniel");
		this.Students.insertNode(4337, 99, "Sam");
		this.Students.insertNode(6317, 2, "James");
		this.Students.insertNode(4589, 51, "Teto");
		this.Students.insertNode(4372, 67, "Bowie");
		this.Students.insertNode(4616, 100, "Ian");
		this.Students.treePreOrder();
		System.out.println("\n");
	}
	/**
	 * tests of finding students
	 */
	public void test2() {
		this.Students.findDetail(4616);
		this.Students.findDetail(43);
	}
	/**
	 * tests of removing students
	 */
	public void test3() {
		
		this.Students.findDetail(4616);
		this.Students.removeNode(4616);
		this.Students.findDetail(4616);
		System.out.println("\n");
		
		this.Students.findDetail(4337);
		this.Students.removeNode(4337);
		this.Students.findDetail(4337);
		System.out.println("\n");
		
		this.Students.treeInOrder();
		
	}
	
	/**
	 * tests of saving files and loading files
	 */
	void test4() {
		
		this.helper.setupFileSaving("Please work", this.Students);
		this.Students = this.helper.readFromFile("Please work");
		this.Students.treeInOrder();
		this.Students = null;
		treeExists = false;
		
	}
	
}
