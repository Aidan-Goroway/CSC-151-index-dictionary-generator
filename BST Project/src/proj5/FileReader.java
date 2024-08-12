package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * File reader that takes any input text and converts it to an index.
 */
public class FileReader {

    private final int FIRST_PAGE = 1;

    private Scanner myReader;

    /**
     * Initializes the FileReader with the given file.
     * @param filename
     */
    public FileReader(String filename) {
        try {
            myReader = new Scanner(new File(filename));
            myReader.useDelimiter("[^a-zA-Z#]+");
        } catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
    }

    /**
     * Detects if the next word is a page marker.
     * @param word Word to check.
     * @return True if word is page marker, otherwise false.
     */
    private boolean isAPageMarker(String word){
        return word.equals("#");
    }

    /**
     * Converts an input file to a dictionary and an Index, including page numbers.
     */
    public void convertToIndex() {

        BinarySearchTree<PageList> index = new BinarySearchTree<>();
        BinarySearchTree<String> dictionary = new BinarySearchTree<>();
        int pageCount = FIRST_PAGE;

        while (myReader.hasNext()) { // as long as there are still words
            String nextWord = myReader.next();

            if (isAPageMarker(nextWord)){ // iterates page count
                pageCount += 1;
            }
            else if (nextWord.length() < 3){ // if word is 2 chars or less
                if (!dictionary.search(nextWord)){ // if word not already there
                    dictionary.insert(nextWord);
                }
            }
            else if (dictionary.search(nextWord)){
                // We don't actually need to do anything here, besides enter this else-if bracket
            }
            else { // if word is NOT a pageMarker (the vast majority of them)
                PageList pageListWithWord = new PageList(nextWord); // our next word as a pageList

                if (index.search(pageListWithWord)){ // word is in index
                    // if pageList does NOT have this page number
                    if (!index.advancedSearch(pageListWithWord).key.repeatPageNum(pageCount)) {
                        if (!index.advancedSearch(pageListWithWord).key.pageNumListFull()){ // if pageList isn't full
                            index.advancedSearch(pageListWithWord).key.insertWordUsage(pageCount);
                        }
                        else { // pageList is full
                            System.out.println("Deleting '" + index.advancedSearch(pageListWithWord).key + "' from index.");
                            dictionary.insert(index.advancedSearch(pageListWithWord).key.wordGet());
                            index.delete(index.advancedSearch(pageListWithWord).key);
                        }
                    }
                }
                else { // word is NOT in the index
                    pageListWithWord.insertWordUsage(pageCount); // updates word with its first page usage
                    index.insert(pageListWithWord);
                }
            }
        }
        String megaIndex = index.toString().replace("(", "").replace(")", "").trim();
        String[] megaIndexArray = megaIndex.split("    ");
        for (int len = 0; len < megaIndexArray.length; len++){
            System.out.println(megaIndexArray[len]);
        }
        String megaDic = dictionary.toString().replace("(", "").replace(")", "").trim();
        String[] megaDicArray = megaDic.split("    ");
        for (int len = 0; len < megaDicArray.length; len++){
            System.out.println(megaDicArray[len]);
        }
    }

}
