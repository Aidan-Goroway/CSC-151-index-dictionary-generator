package proj5;

import org.junit.*;

import static org.junit.Assert.*;

public class AidanGorowayBSTTest {

    @Test // adds 3 items
    public void add3TestInt(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(13);
        tree.insert(1);
        tree.insert(5);
        String expected = "((  1  (  5  ))  13  )";
        // lesser items --> greater items
        String actual = tree.toString();
        assertEquals(expected,actual);
    }

    @Test // adds 6 items, deletes 3 of them
    public void add6del3Int(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(13);
        tree.insert(1);
        tree.insert(5);
        tree.insert(17);
        tree.insert(2);
        tree.insert(67);

        tree.delete(1);
        tree.delete(67);
        tree.delete(13);
        String expected = "((  2  )  5  (  17  ))";
        // lesser items --> greater items
        String actual = tree.toString();
        assertEquals(expected,actual);
    }

    @Test // searches for an item in the tree
    public void searchTrueSimpleInt(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(50);
        tree.insert(17);
        tree.insert(51);

        boolean expected = true;
        boolean actual = tree.search(51);
        assertEquals(expected,actual);
    }

    @Test // searches for an item in a big tree
    public void searchTrueComplexInt(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int index = 14; index <= 32; index = index + 2){
            tree.insert(index);
        }
        for (int index = 15; index > 0; index = index - 1){
            tree.insert(index);
        }

        boolean expected = true;
        boolean actual = tree.search(2);
        assertEquals(expected,actual);
    }

    @Test // searches for a non-existent item in the tree
    public void searchFalseSimpleInt(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        tree.insert(50);
        tree.insert(17);
        tree.insert(51);

        boolean expected = false;
        boolean actual = tree.search(6);
        assertEquals(expected,actual);
    }

    @Test // searches for a non-existent item in a big tree
    public void searchFalseComplexInt(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for (int index = 14; index <= 32; index = index + 2){
            tree.insert(index);
        }
        for (int index = 15; index > 0; index = index - 1){
            tree.insert(index);
        }

        boolean expected = false;
        boolean actual = tree.search(0);
        assertEquals(expected,actual);
    }

    @Test // adds 3 strings
    public void add3String(){
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("clementine");
        String expected = "(  apple  (  banana  (  clementine  )))";
        String actual = tree.toString();
        assertEquals(expected,actual);
    }

    @Test // adds 6 items, deletes 3 of them
    public void add6del3String(){
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("clementine");
        tree.insert("dragonfruit");
        tree.insert("e");
        tree.insert("fruit");

        tree.delete("banana");
        tree.delete("dragonfruit");
        tree.delete("fruit");
        String expected = "(  apple  (  clementine  (  e  )))";
        String actual = tree.toString();
        assertEquals(expected,actual);
    }

    @Test // test for advanced search (true)
    public void advancedSearchSimpleTrueString(){
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("clementine");

        String expected = "banana";
        String actual = (tree.advancedSearch("banana").toString());
        assertEquals(expected,actual);

    }

    @Test // test for advanced search (false)
    public void advancedSearchSimpleFalseString(){
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("clementine");

        BSTNode<String> expected = null;
        BSTNode<String> actual = (tree.advancedSearch("cherry"));
        assertEquals(expected,actual);

    }

    @Test // adds 6 items, deletes all of them
    public void add6delAllString(){
        BinarySearchTree<String> tree = new BinarySearchTree<String>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("clementine");
        tree.insert("dragonfruit");
        tree.insert("e");
        tree.insert("fruit");

        tree.delete("apple");
        tree.delete("banana");
        tree.delete("clementine");
        tree.delete("dragonfruit");
        tree.delete("e");
        tree.delete("fruit");

        String expected = "";
        String actual = tree.toString();
        assertEquals(expected,actual);
    }

}
