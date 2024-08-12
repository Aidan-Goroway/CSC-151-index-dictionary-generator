package proj5;

/**
 * 
 * 
 * @author Chris Fernandes, Kristina Striegnitz
 * @version Fall 2022
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BSTNode<T> root;
	
	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}
    
    /**
	 * inserts a new value into this BST
	 * @param newValue value to insert
	 */
	public void insert(T newValue) {
		root = insert(root,newValue);
	}

	/**
	 * inserts value into tree rooted at subroot
	 * 
	 * @param subroot subroot of tree to insert into
	 * @param value the value to insert
	 * @return root of the subtree I've just finished inserting into
	 */
	private BSTNode<T> insert(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return new BSTNode<T>(value);
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = insert(subroot.rlink,value);
			return subroot;
		}
		else {
			subroot.llink = insert(subroot.llink, value);
			return subroot;
		}
	}

	/**
	 * Locates the largest node on the left side of a given subroot.
	 * @param subroot starting searchpoint.
	 * @return the data of the largest Node.
	 */
	private T findBiggestOnLeft(BSTNode<T> subroot){
		BSTNode<T> rightRoot = subroot;
		 T greatestData = rightRoot.key;

		while (rightRoot.rlink != null){ //as long as there's a right path to travel...
			rightRoot = rightRoot.rlink;
			greatestData = rightRoot.key; // greatestData is updated to be the rightest node's data
		}
		return greatestData;
	}

	/**
	 * deletes value from tree.  If value not there, do nothing.
	 * @param value value to delete
	 */
	public void delete(T value) {
		if (search(value)){
			root = delete(root,value);
		}
	}
	
	/**
	 * deletes value from tree rooted at subroot
	 * @param subroot  root of tree to be deleted from
	 * @param value  element to delete
	 * @return pointer to tree rooted at subroot that has value removed from it
	 */
	private BSTNode<T> delete(BSTNode<T> subroot, T value) {
		/**
		 * if subroot is an empty tree
		 *     return null
		 * else if victim is on the left of subroot
		 *     subroot's left link must become what recursion on subroot's left child gives you
		 * else if victim is on the right of subroot
		 *     subroot's right link must become what recursion on subroot's rlink gives you
		 * else
		 *     victim is found!
		 *     case 1) victim is a leaf
		 *         return null
		 *     case 2) victim has exactly one (right) subtree
		 *         return pointer to that right subtree
		 *     (case 2a - take care of just left subtree only)
		 *     case 3) victim has two subtrees
		 *         pick a replacement (largest value in the left subtree)
		 *         move the data from replacement node to victim node
		 *         delete the replacement
		 */
		if (subroot == null){
			return null;
		}
		else if ((value.compareTo(subroot.key)) < 0){ //value is less than or equal to data
			subroot.llink = delete(subroot.llink, value);
		}
		else if ((value.compareTo(subroot.key)) > 0){ //value is greater than data
			subroot.rlink = delete(subroot.rlink, value);
		}
		else { // value to delete is located
			if (subroot.isLeaf()){
				return null;
			}
			else if (subroot.hasRightChildOnly()){
				return subroot.rlink;
			}
			else { // value has 2 children or a single left child
				BSTNode<T> replacementNode = subroot.llink;
				T replacementData = replacementNode.key;

				if (replacementNode.rlink != null){ // if there is a right path to travel down
					BSTNode<T> startingPoint = replacementNode.rlink;
					replacementData = findBiggestOnLeft(startingPoint);
				}

				subroot.key = replacementData; // does not update rData if there is no right path
				subroot.llink = delete(subroot.llink,replacementData);
			}
		}
		return subroot; // line needed for proper syntax
	}

	/**
	 * Helper method that checks to see if the target and the subroot are the same.
	 * This method also accounts for the fact that an index's params will be
	 * completely different from the dictionary's params.
	 * @param subroot The subroot of our search.
	 * @param target The target of our search.
	 * @return true if they are equal, else false.
	 */
	private boolean equivalenceCheck(BSTNode<T> subroot, T target){
		String subrootString;
		String targetString;
		try { //shortens potential roots/subroots
			subrootString = subroot.toString().substring(0,subroot.toString().indexOf(" "));
		}
		catch (java.lang.StringIndexOutOfBoundsException e){
			subrootString = subroot.toString();
		}
		try { // shortens potential targets
			targetString = target.toString().substring(0,target.toString().indexOf(" "));
		}
		catch (java.lang.StringIndexOutOfBoundsException e){
			targetString = target.toString();
		}
		return subrootString.equals(targetString);
	}

    /**
     * checks whether the target value is in the tree
     * @return true or false to indicate whether the target value is in the tree
     */
    public boolean search(T target) {
		if (root == null){
			return false;
		}
		else if (root.key.equals(target)){
			return true;
		}
		else if (equivalenceCheck(root, target)){ // if roots are equal, but previous else-if does not pass
			return true;
		}
		else if ( (target.compareTo(root.key)) <= 0 ){
			return search(target, root.llink);
		}
		else { // if current value < target
			return search(target, root.rlink);
		}
    }

	/**
	 * Recursive search method.
	 * @param target the value we are searching for
	 * @param subroot the node we are searching
	 * @return true if value is found.
	 */
	private boolean search(T target, BSTNode<T> subroot){
		if (subroot != null) {
			if (equivalenceCheck(subroot, target)){
				return true;
			}
			else if (subroot.isLeaf()) {
				return false;
			} else if (target.compareTo(subroot.key) <= 0) {
				return search(target, subroot.llink);
			} else { // target is greater than node's data
				return search(target, subroot.rlink);
			}
		}
		else { // if the subroot is null, you will not find anything here
			return false;
		}
	}

	/**
	 * An advanced search method meant to
	 * @param target the data of the node we are searching for
	 * @return The node we are looking for.
	 */
	public BSTNode<T> advancedSearch(T target){
		if (root == null){
			return null;
		}
		else if (root.key.equals(target)){
			return root;
		}
		else if (equivalenceCheck(root, target)){
			return root;
		}
		else if ( (target.compareTo(root.key)) <= 0 ){
			return advancedSearch(target, root.llink);
		}
		else { // if current value < target
			return advancedSearch(target, root.rlink);
		}
	}

	/**
	 * Recursive helper for advancedSearch.
	 * @param target the target value we are searching for.
	 * @param subroot The subroot we start searching from.
	 * @return The node we are trying to extract a pageList from.
	 */
	private BSTNode<T> advancedSearch(T target, BSTNode<T> subroot){
		if (subroot != null) {
			if (equivalenceCheck(subroot, target)) {
				return subroot;
			} else if (subroot.isLeaf()) {
				return null;
			} else if (target.compareTo(subroot.key) <= 0) {
				return advancedSearch(target, subroot.llink);
			} else { // target is greater than node's data
				return advancedSearch(target, subroot.rlink);
			}
		}
		else { // if the subroot is null, you will not find anything here
			return null;
		}
	}

	/**
	 * returns tree as printable string
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString(){
		return toString(root);
	}

	/**
	 * recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N){
		String ret = "";
		if (N != null){
			ret += "(";
			ret += toString(N.llink);
			ret += "  " + N + "  ";
			ret += toString(N.rlink);
			ret += ")";
		}
		return ret;
	}

}