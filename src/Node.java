import java.io.PrintWriter;

/**
 * @author Daniel Blackley -- 160007728
 *
 * * This class is responsible for creating the Binary Tree
 * This class points to leftNode and rightNode, the rightNode is a student
 * with a ID higher than the root and the leftNode holds students with ID's
 * lower than the roots.
 * This class also contains the method for printing as attempting to use the
 * inputOutput class leaded to errors as recursion was impossible
 *
 */


public class Node {
	
	//variables that hold the information for the Students
	private Node leftNode, rightNode;
	private int studentID, examMark;
	private String studentName;
	
	
	/**
	 * This is the function that you have to call for the first node in the tree
	 * You cannot call insertNode immediately as insertNode only inserts nodes into
	 * the left or the right and presumes the first node is set up before hand
	 * @param ID The students ID
	 * @param mark The students Mark
	 * @param name The students Name
	 */
	public void initialNode(int ID, int mark, String name) {
		this.setExamMark(mark);
		this.setStudentID(ID);
		this.setStudentName(name);
	}
	
	/**
	 * Method used to insert Node into the binary Tree
	 * Works by detecting whether the ID passed in is bigger than, smaller than or larger than
	 * the ID of the current Node, it then uses recursion to loop through until it finds a 
	 * null node and then sets up the node with the relevant information
	 * 
	 * @param ID Students ID which is used to find the correct node to set up
	 * @param mark Students Mark
	 * @param name Students Name
	 */
	public void insertNode(int ID, int mark, String name) {
		
		if (ID == studentID) {    //if the user has entered a duplicate ID
			System.out.println("ERROR: Student is already in list");
			System.out.println("Would you like to override this student?");
			this.duplicateStudent(ID, mark, name);
			
		}
		
		if (ID < studentID) {	//if the ID is less than this Nodes ID
			if (leftNode != null) {
				leftNode.insertNode(ID, mark, name);	//Node already exists, so continue to go deeper
			} else {
				leftNode = new Node();	//Node doesn't exist, so set one up
				leftNode.setExamMark(mark);
				leftNode.setStudentID(ID);
				leftNode.setStudentName(name);
			}
		} 
		
		
		if (ID > studentID) {
			
			if (rightNode != null) {
				rightNode.insertNode(ID, mark, name);
			} else {
				rightNode = new Node();
				rightNode.setExamMark(mark);
				rightNode.setStudentID(ID);
				rightNode.setStudentName(name);
			}
		}
	}
	
	/**
	 * If the user has tried to create a Student that is already found in the
	 * tree this method is called to ask the user if they want to overwrite
	 * the user with this current ID
	 * 
	 * @param ID Students duplicate ID
	 * @param mark Duplicate students Mark
	 * @param name Duplicate students name
	 */
	public void duplicateStudent(int ID, int mark, String name) {
		InputOutput duplicate = new InputOutput();	//initialise this object to deal with input
		boolean valid = false;
		
		
		System.out.println("the following Information will be changed: ");
		
		this.displayStudent();
		
		System.out.println("\nwill be changed to:\n");
		
		System.out.println(name + " Who Has an ID of " + ID + " and an Exam mark of " + mark);
		
		System.out.println("If this is OK input \'Y\',");
		System.out.println("Else if you do not want these changes to occur, Input \'N\'");
		
		while(!valid) {
			
			String userChoice = duplicate.validateStringInput();
			
			userChoice.toLowerCase();	//change user choice to lower case 
			
			if (userChoice.equals("y")) {
				
				this.initialNode(ID, mark, name);
				System.out.println("Overwrrite Successful");
				valid = true;
				
			} else if (userChoice.equals("n")) {
				
				System.out.println("Discarding Information");
				valid = true;
				
			} else {
				System.out.println("Unidentified Input, please retry...");
			}
		}
	}
	
	/**
	 * Prints the tree In order of the students ID 
	 * from lowest ID to highest ID using recursion
	 */
	public void treeInOrder() {
		
		if (leftNode != null) {   //continue going into this method until we have hit the lowest ID
			leftNode.treeInOrder();
		}
		this.displayStudent();	// display the leftmost ID value student
		if (rightNode != null) {    //check if there is any nodes on the right of the lowest
			rightNode.treeInOrder();
		}
	}
	
	/**
	 * Prints the tree in post order, first the leftmost node, then the right
	 * node and then the root node
	 */
	public void treePostOrder() {
		
		if (leftNode != null) {
			leftNode.treePostOrder();
		}
		if (rightNode != null) {
			rightNode.treePostOrder();
		}
		
		this.displayStudent();
	}
	
	/**
	 * Prints the tree in Pre-order in which the tree visits all the nodes from
	 * the root node then moves down the left, printing each one on the way
	 * then visits the right nodes on the left side and finally the right side of the tree
	 */
	public void treePreOrder() {
		
		this.displayStudent();
		
		if (leftNode != null) {
			leftNode.treePreOrder();
		}
		if (rightNode != null) {
			rightNode.treePreOrder();
		}
	}
	
	/**
	 * This method takes in an ID from the user and checks if it is in the tree
	 * by checking the whether the node is smaller than or larger than the current nodes
	 * ID, allowing the tree to cut off half the potential results with every step
	 * @param ID The ID the user wants to find
	 * @return the Node that is found or not found
	 */
	public Node containsStudent(int ID) {
		
		Node foundStudent = null;
		
		if (ID < studentID) {
			if (leftNode != null) {
				foundStudent = leftNode.containsStudent(ID);
			}
		} 
		
		if (ID > studentID) {
			if (rightNode != null) {
				foundStudent = rightNode.containsStudent(ID);
			} 
		}
		
		if (ID == studentID) {
			foundStudent = this;
		}
		return foundStudent;
	}

	/**
	 * Finds and prints the details of the student with the ID that has been passed in.
	 * Works by calling the containsStudent() method to find the details of the student and informs the
	 * user if There are no students with that ID. otherwise it calls the display details method
	 * 
	 * @param ID ID of the student method will attempt to find
	 */
	public void findDetail(int ID) {
		
		Node foundStudent = containsStudent(ID);
		
		if (foundStudent == null) {    //if the student was not found
			System.out.println("Student with ID " + ID + " not found in database");
		} else {
			System.out.println("Student has been found:");
			foundStudent.displayStudent();
		}
	}

	/**
	 * Prints out all the information of the student at the current node
	 */
	public void displayStudent() {
		
		int mark, ID;
		ID = this.getStudentID();
		mark = this.getExamMark();
		String name = this.getStudentName();
		
		System.out.println(name + " Who Has an ID of " + ID + " and an Exam mark of " + mark);
		
	}
	
	/**
	 * Searches for and removes a Student with the given ID, works similarly to the
	 * findDetail() method by using the constainsStudent() method then calls the removeNode()
	 * method
	 * @param ID ID of the student the method will attempt to remove
	 */
	public void removeStudent(int ID) {
		
		Node studentToRemove = this.containsStudent(ID);
		
		if (studentToRemove == null) {
			System.out.println("Student not found");
		} else {
			
			this.removeNode(ID);
			
		}
	}
	
	/**
	 * Fairly complex method that Removes the student with the passed in ID.
	 * Works by checking if the ID is smaller then going down the leftNode
	 * via Recursion of this method. Once it finds the Node it wants to remove it returns the node
	 * and it sets the parent nodes leftNode or rightNode, depending on whether the ID is bigger or smaller,
	 * to be equal to the node we want to deletes leftNode or rightNode. Effectively deleting it by not having the parent
	 * be able to reference it anymore. By itself however this would also delete any of the information that was being held by the
	 * Child, this is dealt with in the countChildren methods.
	 * 
	 * Note that the countChildren methods both return null, this is to stop a stack overflow from infinite recursion.
	 * 
	 * @param ID ID of the student we want to delete
	 * @return The found node that we want to remove
	 */
	public Node removeNode(int ID) {
		
		Node newNode;
		newNode = null;
		
		
		if (ID < studentID) {
			if (leftNode != null) {
				newNode = leftNode.removeNode(ID);
				
				
				if (newNode != null) {	//if the newNode has become null, then we can continue
					this.leftNode = newNode.leftNode;
					newNode = this.leftNode.countChildrenRight(newNode);
				}
			}
		} 
		
		if (ID > studentID) {
			if (rightNode != null) {
				newNode = rightNode.removeNode(ID);
				
				if (newNode != null) {
					this.rightNode = newNode.rightNode;
					newNode = this.rightNode.countChildrenLeft(newNode);
				}
			} 
		}
		
		if (ID == studentID) {
			return this;
		}
		return null;
	}
	
	

	/**
	 * If the ID to remove was smaller than it's parent's ID this method gets called
	 * as the parent will still have all of the leftNodes but the rightNodes are 
	 * at risk of being lost. Works by setting the current object (this) to being equal to the copy
	 * of the removed nodes right children. uses recursion to make sure to add them at the far bottom
	 *  of the right node, so that the tree still remains balanced.
	 * @param newNode This is a copy of the Node that was removed
	 * @return returns Null to stop the infinite recursion that would occur in the method that called this
	 */
	public Node countChildrenRight(Node newNode) {
		
		if (this.rightNode != null) {
			this.rightNode.countChildrenRight(newNode);
		} else {
			this.rightNode = newNode.rightNode;
		}
		return null;
	}
	
	/**
	 * Same as countChildrenRight, but works instead if the ID was Larger than the parents ID
	 * @param newNode copy of the node we have removed
	 * @return returns Null to stop the infinite recursion that would occur in the method that called this
	 */
	public Node countChildrenLeft(Node newNode) {
		
		if (this.leftNode != null) {
			this.leftNode.countChildrenLeft(newNode);
		} else {
			this.leftNode = newNode.leftNode;
		}
		return null;
	}
	
	/**
	 * Credit to https://my.dundee.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_57691_1&content_id=_5023268_1
	 * of which contains the slides we used last semester that we used for reading or writing to a file. 
	 * I didn't copy any of the code directly but i used it to jog my memory on how to do it which is why the
	 * variable names will be the same as used in the example given last semester
	 * @param fileName The filename selected by the User to be passed in
	 * @param printWriter The PrintWriter passed in from the InputOutput class
	 */
	public void saveToFile(String fileName, PrintWriter printWriter) {
		
        int ID, mark;
        String name;
        
        try {
        	
        	if(this != null) {
        		
        		ID = this.getStudentID();
        		name = this.getStudentName();
        		mark = this.getExamMark();
        		
        		
        		printWriter.print(ID + "," + name + "," + mark + "\r\n");	//use /r/n as this was compiled on windows
        		
        	
        		
        		if (leftNode != null) {
        			leftNode.saveToFile(fileName, printWriter);
        		}
        		if (rightNode != null) {
        			rightNode.saveToFile(fileName, printWriter);
        		}
        	}
        	
        } catch (Exception e) {
        	System.out.print("Error Writing to File: " + e);
        }
	}
	
	//Various Getters and Setters for private Student information below this point.
	
	public int getStudentID() {
		return studentID;
	}


	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}


	public int getExamMark() {
		return examMark;
	}


	public void setExamMark(int examMark) {
		this.examMark = examMark;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}
