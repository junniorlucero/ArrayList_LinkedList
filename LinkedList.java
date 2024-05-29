// written by Junnior Lucero Jaramillo (lucer045) && Jesus Romero Rivera (romer309)
public class LinkedList<T extends Comparable<T>> implements List<T> {
    public LinkedList() {
        this.head = null;
        this.isSorted = true;
    }
    public boolean add(T element) {
        // Let's check if the provided element is null
        if (element == null) {
            return false;
        }
        // Let's check if the list is empty
        // If so ...
        // create a new node
        if (head == null) {
            head = new Node<>(element);
        }
        // Let's add to the end of the list and return true
        // Let's make a new node (currentNode and set it to the head of the list)
        else {
            Node<T> currentNode = head;
            // Let's traverse through the entire list
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            // Let's add the new element, after we have already looped traverse through the array
            currentNode.setNext(new Node<>(element));
        }
        // Let's update isSorted
        isSorted = this.isSorted();
        // Let's increment the size of the list
        size++;
        return true;
    }
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size) { //check if index is within bounds
            return false;
        }
        Node<T> newNode = new Node<T>(element); //create new Node<T> with our element passed in
        if (index == 0) { // adding to the start
            newNode.setNext(head); // set the next pointer of the new node to the head
            head = newNode; // update head/head to point to the new node that we created
            size++; // increment the size
            return true;
        } else {
            Node<T> currentNode = head; // let's make a dummy variable
            for (int i = 0; i < index - 1; i++) { //let's traverse through to the end
                currentNode = currentNode.getNext();
            } // update the newNode and currNode accordingly
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
            // update size and isSorted accordingly
            size++;
            isSorted = this.isSorted();
            return true;
        }
    }
    public void clear() {
        this.head = null; // Set the head of the array to null.
        this.size = 0; // set the "size" amount of elements in the LinkedList to 0
        this.isSorted = true; // since there are no elements within the LinkedList we assume it is sorted
    }

    public T get(int index) {
        // let's test for out of bounds
        if (index < 0 || index >= size) {
            return null;
        } else {
            Node<T> currNode = head; //head at the beginning of the list
            for (int i = 0; i < index; i++) { //we will head iterating at the head of the list and will continue until the index
                currNode = currNode.getNext(); //get the next node with each iteration
            }
            return currNode.getData(); //return the value at the node that we specified
        }
    }
    public int indexOf(T element) {
        if (element == null) { // Check if the element is null
            return -1;
        } else {
            Node<T> currNode = head; // create a current node for now
            int index = 0; // let's start out index at 0
            while (currNode != null) { // traverse through the list
                if (currNode.getData() == element) { // let's find the element we are trying to loop for
                    return index;// let's retrieve that index
                }
                currNode = currNode.getNext(); // move down the list
                index++;// increment our index if we haven't found it yet
            }
            return -1; // if the element is not found within the LinkedList
        }
    }
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }
    public int size() {
        return size;
    }
    public void sort() {
        if (!isSorted) {
            for (int i = 0; i < size; i++) { // iterate from the head until the size
               Node<T> currNode = head; //head at the head of the list
                for (int j = 0; j < size - i - 1; j++) { //number of elements to be considered during each iteration
                    Node<T> nextNode = currNode.getNext(); //head our nextNode one after Curr Node
                    if (currNode.getData().compareTo(nextNode.getData()) > 0) { //compare the Nodes
                        T temp = currNode.getData(); //set a temporary variable of type T
                        currNode.setData(nextNode.getData()); //We now swap our data with the Node to the right of us
                        nextNode.setData(temp); //Set the data in nextNode to temp
                    }
                    currNode = nextNode;
                }
            }
            isSorted = true; //return true once we finish
        }
    }
    public T remove(int index) {
        // First, let's check if the provided index is not within bounds
        if (index < 0 || index >= size) {
            return null;
        }
        // Let's create a dummy variables and object of T variable to store the element we will be returning at the end
        Node<T> currentNode = head;
        T object = null;
        Node<T> anotherNode = null;
        // we only care about finding the index (the item to remove so lets traverse to there)
        for(int i = 0; i < index; i++) {
            // Let's save the current node we are looking at in another variable
            anotherNode = currentNode;
            // move to the next node
            currentNode = currentNode.getNext();
        } // let's store the object we will be removing
        object = currentNode.getData();
        // if the user wants to remove the head
        if(anotherNode == null){
            // let's update the head
            head = currentNode.getNext();
        } else {
            // If they do not want to then, we set the anotherNode pointer to the next node in the list
            anotherNode.setNext(currentNode.getNext());
        }
        // decrement the size, update isSorted, and return the removed object
        size--;
        isSorted = this.isSorted();
        return object;
    }
    public void removeDuplicates() {
        // let's create a dummy node
        Node<T> currentNode = head;
        while (currentNode != null) { //iterate through the list
            Node<T> pointer = currentNode;  //create a new pointer in order to ensure all combinations are covered
            while (pointer.getNext() != null) { //iterate through the list, while the pointer's next element is not null
                if (currentNode.getData().compareTo(pointer.getNext().getData()) == 0) { //compare the elements
                    pointer.setNext(pointer.getNext().getNext()); // we are skipping the duplicate node and in a way, delinking it from the list
                    size--; // decrement the size of the list once we finish the loop, this means we found a duplicate and size should be decreased
                } else { //else case where they are not the same
                    pointer = pointer.getNext(); //simply move on to the next node
                }
            }
            currentNode = currentNode.getNext(); //move on to the next node
        }
    }
    public void reverse() {
        // Let's create a node that points to the head
        Node<T> currentNode = head;
        // Let's also create a previous node that will help us move down the list
        Node<T> previousNode = null;
        // We also need to keep track of the nextNode
        Node<T> nextNode = currentNode.getNext();

        // We can only really reverse a list if it contains elements so
        // let's make sure there's at least two nodes with data
        // Let's traverse down the list
        while (nextNode != null) {
        // Let's save the node to the right of the current node in nextNode
            nextNode = currentNode.getNext();
        // Let's now save our new current node to the previous node
        // (the reversing part)
            currentNode.setNext(previousNode);
        // Let's now say that the old current node will now be the previous node
            previousNode = currentNode;
        // let's now move the next node as our current node
            currentNode = nextNode;
        // update the head of the list
            head = previousNode;
        }
    }
    public void exclusiveOr(List<T> otherList) {
        // we can only run exclusiveOr if our otherList is not null, so let's just put that as our conditional statement
        if (otherList != null) {
            // type cast
            LinkedList<T> other = (LinkedList<T>) otherList;
            // Let's sort and remove duplicates from both
            this.sort();
            this.removeDuplicates();
            other.sort();
            other.removeDuplicates();

            // let's create dummy nodes for this and other head
            Node<T> currentThisPtr = head;
            Node<T> currentOtherPtr = other.head;

            // let's create an intermediate
            LinkedList<T> exclusiveList = new LinkedList<>();
            // let's traverse through the list
            while (currentThisPtr != null || currentOtherPtr != null) {
                // let's check if elements in other are greater than this
                if (currentThisPtr != null && (currentOtherPtr == null || currentThisPtr.getData().compareTo(currentOtherPtr.getData()) < 0)) {
                   // if so, let's add in the elements from this list to our new exclusive list
                    exclusiveList.add(currentThisPtr.getData());
                    // let's also move up our pointer only for this list, so we can finish adding these elements before moving onto the other list
                    currentThisPtr = currentThisPtr.getNext();
                    // let's check if the elements in this are greater than this
                } else if (currentOtherPtr != null && (currentThisPtr == null || currentThisPtr.getData().compareTo(currentOtherPtr.getData()) > 0)) {
                   // if they are let's add in the other elements first
                    exclusiveList.add(currentOtherPtr.getData());
                    // let's move our pointer for other down the line
                    currentOtherPtr = currentOtherPtr.getNext();
                } else {
                    // in the event we find duplicates, we don't want to add them, soo.. let's just move both pointers down the line.
                    currentThisPtr = currentThisPtr.getNext();
                    currentOtherPtr = currentOtherPtr.getNext();
                }
            }
            // reassign the head
            this.head = exclusiveList.head;
            // update the size
            this.size = exclusiveList.size;
        }
    }
    public T getMin(){
        // Let's create a currentNode
        Node<T> currentNode = head;

        // --- Case 1 ---
        // If our current list is empty
        // then we stop there
        // and simply return null
        if(currentNode == null){
            return null;
        }
        // --- Case 2 ---
        // If we have an unknown amount of elements in our list then....
        // Let's create a T object that references the data of the first element in the list
        // Let's also assume that the first element in the list is the min
        T min = currentNode.getData();

        // Traverse through the list
        while(currentNode.getNext() != null){
            // Check if the current node's data is larger than the next node
            if(min.compareTo(currentNode.getNext().getData()) > 0){
                // If so, update min
                min = currentNode.getNext().getData();
            }
            // Move down the list
            currentNode = currentNode.getNext();
        }
        // Return the min from either Case 1 || Case 2 || Case 3
        return min;
    }
    public T getMax(){
        // Let's create a currentNode
        Node<T> currentNode = head;

        // --- Case 1 ---
        // If our current list is empty
        // then we stop there
        // and simply return null
        if(currentNode == null) {
            return null;
        }

        // --- Case 2 ---
        // If we have an unknown amount of elements in our list then....
        // Let's create a T object that references the data of the first element in the list
        // Let's also assume that the first element in the list is the max
        T max = currentNode.getData();

        // --- Case 3 ---
        // Traverse through the list
        while(currentNode.getNext() != null){
            // Check if the current node's data is smaller than the next node
            if(max.compareTo(currentNode.getNext().getData()) < 0) {
                // If so, update max
                max = currentNode.getNext().getData();
            }
            // Move down the list
            currentNode = currentNode.getNext();
        }
        // Return the max from either Case 1 || Case 2 || Case 3
        return max;
    }
    public String toString(){
        // Let's create node
        Node<T> currentNode = head;
        // Let's create an output String
        String output = "";

        // Let's iterate through all elements in the list
        for(int i= 0; i < size; i++){
            // Each line should have the index and the data from that node
            output += "Index " + i + ": " + currentNode.getData() + "\n";
            // Let's move down the list
            currentNode = currentNode.getNext();
        }
        // Simply return the string housing the list
        return output;
    }
    public boolean isSorted() {
       //  If our list has nothing ....
        //   return true since, we have nothing
        if(head == null){
            isSorted = true;
            return isSorted;
        }
        // Go through the entire array and compare elements
        Node<T> currentNode = head;
        // Let's traverse through the entire list
        while (currentNode != null && currentNode.getNext() != null) {
            // Let's link the nodes together
            // assuming the list did not fail the previous loop, then our list is sorted and return that.
            if(currentNode.getData().compareTo(currentNode.getNext().getData()) > 0){
                isSorted = false;
                return false;
            }
            currentNode = currentNode.getNext();
        }
        isSorted = true;
        return isSorted;
    }
    // attributes
    private boolean isSorted; // only true when the list is sorted in ascending order SMALLEST TO LARGEST
    private Node <T> head;
    private int size;
}