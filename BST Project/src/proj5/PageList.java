package proj5;

/**
 * This class models the pagelist. It can hold 4 different pages of a word.
 * After 4 usages, the word is no longer part of the index, and is entered into
 * the dictionary.
 */
public class PageList implements Comparable<PageList> {

    private static final int NUMBER_OF_INDEX_USAGES = 4;
    private static final int NOT_USED_BEFORE = 0;

    private Integer[] pageNumList;
    private String word;
    private int size;

    /**
     * Constructor for a pageList object.
     * @param word the word we are tracking the page occurrences of.
     */
    public PageList(String word){
        this.pageNumList = new Integer[NUMBER_OF_INDEX_USAGES];
        this.word = word;
    }

    /**
     * Comparison function between pageLists.
     * @param other the object to be compared.
     * @return Returns -1 if the pageList word is "lesser" than what its being compared to.
     * +1 if the pageList word is greater than its other.
     * 0 if the words of both pageLists are the same.
     */
    public int compareTo(PageList other) {
        if (this.wordGet().compareTo(other.wordGet()) < 0){ // lesser
            return -1;
        }
        else if (this.wordGet().compareTo(other.wordGet()) > 0){ // greater
            return 1;
        }
        else { // words are the same
            return 0;
        }
    }

    /**
     * Gets the word stored in a pageList
     * @return String representing the word.
     */
    public String wordGet(){
        return word;
    }

    /**
     * private getter for the word at a specified index.
     * @param index The index we are checking.
     * @return the index we checked.
     */
    private int pageNumGet(int index){
        return pageNumList[index];
    }

    /**
     * Gets the number of occurrences a word has. Also known as the size.
     * @return integer number of word occurrences.
     */
    public int occurrenceGet(){
        return size;
    }

    /**
     * Gets the pervious pageNum for a word.
     * @return previous pageNum for a word. Returns 0 if there is no previous page.
     */
    private int previousPageNum(){
        try {
            return pageNumList[occurrenceGet() - 1];
        }
        catch (ArrayIndexOutOfBoundsException e){
            return NOT_USED_BEFORE;
        }
    }

    /**
     * Increments the index of the pageList upwards by 1.
     */
    private void increment(){
        size += 1;
    }

    /**
     * Helper method, inserts the pageNumber into the pageList.
     * @param pageNum the page number to be inserted.
     */
    private void insert(int pageNum){
        pageNumList[occurrenceGet()] = pageNum;
    }

    /**
     * Checks if a pagelist is full.
     * @return true if list is full, otherwise false.
     */
    public boolean pageNumListFull(){
        return (occurrenceGet() == NUMBER_OF_INDEX_USAGES);
    }

    /**
     * inserts a usage into the pageList.
     * If a word is to be entered multiple times on the same page, does nothing.
     */
    public void insertWordUsage(int pageNum){
        if (!repeatPageNum(pageNum)){ // if pageNumber is not a repeat...
            insert(pageNum);
            increment();
        }
    }

    /**
     * Detects if a word has already been used on a page.
     * @param pageNum The page number the word is occurring on.
     * @return True if pageNum matches the page number of the previous occurrence.
     */
    public boolean repeatPageNum(int pageNum){
        return pageNum == previousPageNum();
    }

    /**
     * toString method for pageList.
     * @return String in (word {1,2,3,4}) format.
     */
    public String toString(){
        String pageListString = wordGet() + " {";
        for (int index = 0; index < occurrenceGet(); index++){
            pageListString += pageNumGet(index);
            if (index < occurrenceGet() - 1){
                pageListString += ", ";
            }
        }
        return pageListString + "}";
    }

}
