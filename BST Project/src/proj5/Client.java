package proj5;

/*
I affirm that I have carried out the attached academic endeavors with full academic honesty, in
accordance with the Union College Honor Code and the course syllabus.
 */

/**
 * Driver for the index maker project
 * 
 * @author Aidan Goroway
 * @version 1.08
 */
public class Client
{
    public static void main(String[] args)
    {
    	makeIndex("uscons.txt"); //replace with correct path
    }
    
    /**
     * Makes an index and a dictionary out of fileName. Gradescope needs this function.
     * 
     * @param fileName path to text file that you want to convert
     */
    public static void makeIndex(String fileName) {
    	FileReader inputText = new FileReader(fileName);
        inputText.convertToIndex();
    }
}
