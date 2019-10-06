import java.util.Scanner;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * @author Daniel Blackley -- 160007728
 *
 * This class is responsible for exception handling, saving and Loading files
 * A lot of this I have used in my previous work and is just light modifications
 * on what has been done before, nothing too complicated
 * 
 * Also credit to https://my.dundee.ac.uk/webapps/blackboard/content/listContent.jsp?course_id=_57691_1&content_id=_5023268_1
 * which had examples of reading and writing to files which i used to jog my memory, hence why there are a lot of similar
 * variable names
 */


public class InputOutput {
	
	
	/**
	 * Takes asks the user to input a string and attempts to catch any potential errors
	 * There are not many errors one can catch when a string is being inputed however I have
	 * included a line to make sure the user doesn't just leave it blank
	 * @return Valid String
	 */
	public String validateStringInput() {
		
		String input = "No Name";
		boolean valid = false;
		
		while (!valid) {
			Scanner s1 = new Scanner(System.in);
			
			try {
				input = s1.next();
				
				if (input.length() > 0) {
					valid = true;
				}
				
			} catch (Exception e) {
				
				System.out.println("Please Input a valid String");
				valid = false;
			}
		}
		
		return input;
	}
	
	/**
	 * This method was not able to run Independently from the Node class, so most of the saving was done
	 * in the Node class and this was just used to set up the initial parts to be passed on to Node
	 * @param fileName The filename that the file will be saved as
	 * @param nodeToSave The tree that we want to save
	 */
	public void setupFileSaving(String fileName, Node nodeToSave) {
		
		fileName = fileName + ".txt";    //add this so that the user and load method can read
		
		FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        
        try {
        	outputStream = new FileOutputStream(fileName);
        	printWriter = new PrintWriter(outputStream); 
        	
        	nodeToSave.saveToFile(fileName, printWriter);
            
        	printWriter.close();
            
        } catch (IOException e) {
        	
        	System.out.println("Error writing to file: " + e);
        	 
        }
        
        System.out.println("Success saving to file");
		
	}
	/**
	 * Fairly basic method that opens the file then splits up the file and sends it to the Node
	 * class line by line
	 * @param fileName The name of the file the user wishes to open
	 * @return The Tree that the user has loaded
	 */
	public Node readFromFile(String fileName) {
		
		Node nodeToRead = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String fileOutput;
        String[] information;

        try {
		
			fileReader = new FileReader(fileName + ".txt");
			bufferedReader = new BufferedReader(fileReader);
			fileOutput = bufferedReader.readLine();
			information = splitString(fileOutput);    //split the file into relevant parts (ID, Name, Mark)
			nodeToRead = setUpNode(information);      
			
			fileOutput = bufferedReader.readLine();    //read next line in the file
			
			while (fileOutput != null) {
				
				information = splitString(fileOutput);
				addNodes(nodeToRead, information);
				fileOutput = bufferedReader.readLine();
			}
			
		} catch (IOException e) {
			
			System.out.println("Error reading file: " + e);
			
			return null;
		}
        
        
        try {
			bufferedReader.close();
		} catch (IOException e) {
			
			System.out.println("Error closing Buffered Reader: " + e);
		}
        
        return nodeToRead;
		
	}
	
	/**
	 * method used when opening and reading a file that adds a node the the tree
	 * Primary function is to change strings to integers
	 * @param students The tree we want to add to
	 * @param information The information of the student at the relevant node
	 */
	public void addNodes(Node students, String[] information) {
		
		int ID, mark;
		String name;
		
		ID = Integer.parseInt(information[0]);
		mark = Integer.parseInt(information[2]);
		name = information[1];
		
		students.insertNode(ID, mark, name);
		
	}
	/**
	 * Sets up the root node, so that other nodes can be added
	 * @param information Information of the student at the root node
	 * @return The tree we are adding nodes to
	 */
	public Node setUpNode(String[] information) {
		
		int ID, mark;
		String name;
		
		ID = Integer.parseInt(information[0]);
		mark = Integer.parseInt(information[2]);
		name = information[1];
		
		Node students = new Node();
		
		students.initialNode(ID, mark, name);
		return students;
		
	}
	
	/**
	 * basic method that changes a string to a list of strings, split by a ","
	 * @param stringToSplit The string we want to split
	 * @return The split up string
	 */
	public String[] splitString(String stringToSplit) {
		
		String[] splitString = new String[3];
		
		
		splitString = stringToSplit.split(",");
		
		return splitString;
		
		
	}
	
	/**
	 * Makes sure the user enters a integer in the Correct range of values and also
	 * makes sure that the user doesn't attempt to enter a string
	 * 
	 * @param max the maximum number the user may enter
	 * @return the validated input
	 */
	public int validateIntegerInput(int max) {
		
		int input = 0;
		boolean valid = false;
		
		while (!valid) {
			Scanner s1 = new Scanner(System.in);
			
			try {
				input = s1.nextInt();
				
				if (input > 0 && input <= max) {
					valid = true;
				} else {
					System.out.println("Number not in valid range of 1 to " + max);
				}
				
			} catch (Exception e) {
				
				System.out.println("Please Input a valid Integer");
			}
		}
		return input;
	}
	
}

