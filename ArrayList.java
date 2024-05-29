// written by Junnior Lucero Jaramillo (lucer045) && Jesus Romero Rivera (romer309)
public class ArrayList<T extends Comparable<T>> implements List<T> {
    public ArrayList() {
        // since we are implementing generics, we have to use a type cast to create an array of T
        array = (T[]) new Comparable[2]; // setting the array to fixed length of 2
        isSorted = true;
    }
    public boolean add(T element) {
        if (element == null) { //check if the element we want to add is null, if it is then instantly return false
            return false;
        }
        if (arraySize == 0) { // If arraySize == 0 and we want to add an element then we proceed
            array[0] = element; //set first index to 0
            arraySize = 1; //set array size to 1 since there is now an element
            return true;
        } else if (element != null && arraySize == array.length) { // else if the element is not null & the arraySize is equal to the array.length, we execute the body
            T[] newArray = (T[]) new Comparable[(arraySize * 2 + 1)]; //create a new array with 2 times the length + 1 of the original
            for (int i = 0; i < arraySize; i++) { //loop through length of old array and copy over old elements into new list elements
                newArray[i] = array[i];
            }
            newArray[arraySize] = element; //set the new element we want to add to the end of the newlist based on size, should we do -1?
            this.array = newArray; // set our array to the newArray
            arraySize++; //increase arraySize by 1 since we added a new one
            isSorted = this.isSorted();
            return true;
        }
        //this scenario then handles a case in which our array has enough space
        array[arraySize] = element; // set our index at the end (which is arraySize) to the new element
        arraySize++; // increment arraySize since we will be adding an element (this case is reached if our array has enough space
        isSorted = this.isSorted();
        return true;
    }
    public boolean add(int index, T element) {
        // Let's start off my checking for any false test cases
        // Check element given && index for bounds
        if (element == null || index < 0 || index >= arraySize) {
            return false;
        } else if (array[index] == null) {
            array[index] = element; // if given input is within bonds and the element is actually something then add it.
            arraySize++; // increment the size, since we added a new element
            isSorted = this.isSorted(); //updating isSorted
            return true; // return the status of the add operation
        } else {
            // create a new array
            T[] newArray = (T[]) new Comparable[(arraySize*2 + 1)];
            // copy over information
            for (int i = 0; i < arraySize; i++) {
                newArray[i] = array[i];
            }
            this.array = newArray; // reassign new Array
            for (int i = arraySize - 1; i >= index; i--) {   // shift elements so that we have room to insert at index
                array[i + 1] = array[i];
            }// add in the element given at the index
            array[index] = element;
            arraySize++;
            isSorted = this.isSorted();
            return true; // if the addition was successful or not
        }
    }

    public void clear() {
        this.arraySize = 0; // reset array size to 0
        isSorted = true; // reset isSorted to true since there are no elements
        this.array = (T[]) new Comparable[2]; // here we reset the length to 2 as in the constructor\
    }

    public T get(int index) {
        // Let's take care of the index out of bounds situation
        if (index < 0 || index >= arraySize) {
            return null;
        } else {
            // return that element
            return array[index];
        }
    }

    public int indexOf(T element) {
        if (element == null) { //if element is null then instantly returns -1
            return -1;
        }
        for (int i = 0; i < array.length; i++) { //iterate through array until the arraySize
            if (array[i] == element) { // if array[i] == element, we instantly return the first index of element
                return i;
            }
        }
        return -1; // if we go through the entire loop without returning the index, we then return -1
    }

    public boolean isEmpty() {
        if (arraySize == 0) {
            this.isSorted = true; // Update isSorted accordingly
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return arraySize;
    }

    // update isSorted
    public void sort() {
        // "If isSorted is true, do NOT re-sort."- soo.... lets sort
        if (!isSorted) {
            // use bubble sort
            for (int i = 0; i < arraySize; i++) {
                for (int j = 0; j < arraySize - i - 1; j++) {
                    // if a > b, then it's not sorted ASC so let's change that ...
                    if (array[j].compareTo(array[j + 1]) > 0) {
                        // let's store our current element
                        T temp = array[j];
                        // let's move the RHS element over to the LHS
                        array[j] = array[j + 1];
                        // Lets reassigned the RHS to the temp variable we saved earlier
                        array[j + 1] = temp;
                    }
                }
            }
        }
        isSorted = true; // updates isSorted
    }

    public T remove(int index) {
        if (index >= 0 && index <= arraySize - 1) {
            T removedElement = array[index];
            for (int i = index; i < arraySize-1; i++) { //we start iterating at index i, we will continue until we reach the end
                // of the array with elements in it, in this case
                //arraySize accounts for that but since indexes start at 0, we must do arraySize-1
                array[i] = array[i + 1]; // we now shift elements to the left by taking for example
                //array[2] = array[3]
            }
            array[arraySize-1] = null; // we set the last element to null to ensure consistency
            arraySize--; // decrease the arraySize by 1 since we removed an element
            isSorted = this.isSorted();
            return removedElement;
        } else {
            return null;
        }
    }
    public void removeDuplicates() {
        T[] newArray = (T[]) new Comparable[arraySize]; // Let's just keep the same size

        int uniqueElement = 0;
        newArray[uniqueElement] = array[0]; // copy the first element to the new array
        uniqueElement++;

        for (int i = 1; i < arraySize; i++) { // Iterate through the remaining elements of the array
            boolean isDuplicate = false; // instantly set isDuplicate to false by default
            for (int j = 0; j < uniqueElement; j++) { //we will iterate up to our uniqueElement which keeps track of our unique elements
                if (!isDuplicate) { //continue if isDuplicate is not the case
                    if (array[i].compareTo(newArray[j]) == 0) { //compare the element at index[i]  to the element in newArray[j]
                        isDuplicate = true; //if its the same then we set isDuplicate to true and exit the loop
                    }
                }
            }
            if (!isDuplicate) { // If the element is not a duplicate, add it to the new array
                newArray[uniqueElement] = array[i]; //we then set the element of newArray[uniqueElement] = array[i]
                uniqueElement++; //increase uniqueElement
            }
        }
        arraySize = uniqueElement;  // update the array size to the number of unique elements
        for (int i = 0; i < newArray.length; i++) { // iterate through the length of newArray
            array[i] = newArray[i]; //copy over the elements from the newArray to array itself
        }
    }

    public void reverse() {
        // Let's create a temp element to store our element and our last element index
        T temp;
        int arraylength = arraySize - 1;
        // Let's iterate through the halfway point
        for (int i = 0; i < arraySize / 2; i++) {
            // save the start of the list in our temp variable
            temp = array[i];
            // let's now swap the start with the end
            array[i] = array[arraylength - i];
            // let's now swap the end with the start
            array[arraylength - i] = temp;
        }
    }

    public void exclusiveOr(List<T> otherList) {
        // The List interface states "If otherList is null, do not make any modifications."
        // So let's only have our working code in our conditional statement
        if (otherList != null) {
            // Type cast
            ArrayList<T> other = (ArrayList<T>) otherList;
            // Let's sort and remove duplicates from both
            this.sort();
            this.removeDuplicates();
            other.sort();
            other.removeDuplicates();

            // let's create a new list
            // let's make the size of because  worst case scenario,
            // we need to add every single element in both lists
            T[] newArray = (T[]) new Comparable[arraySize + other.size()];
            // let's make iterative variables for both lists
            int thisCounter = 0;
            int otherCounter = 0;
            // Let's keep track of the index at the new array
            int openSpot = 0;
            // traverse through both....
            while (thisCounter < arraySize && otherCounter < other.size()) {
                // --- Case 1 --- (both elements are the same)
                // Let's assume that both elements are the same
                if (array[thisCounter].compareTo(other.get(otherCounter)) == 0) {
                    // If both elements from both lists are the same
                    // then we don't want to add them into the new one
                    // so, let's just move on down their line
                    thisCounter++;
                    otherCounter++;
                    // --- Case 2 --- (the elements are not the same and the values in this are greater than other)
                } else if (array[thisCounter].compareTo(other.get(otherCounter)) > 0) {
                    // if the element in this list is greater than other, then lets add the lower valued element from other
                    newArray[openSpot] = other.get(otherCounter);
                    // Let's also increment the counter for our other
                    // that way when we loop through we will add all the lower values
                    otherCounter++;
                    // let's also not forget to move our index in our new list
                    openSpot++;
                    // Note that this can be simplified to else
                    // we decided to include it, since this is how we broke the method down.
                    // --- Case 3 --- (the elements are not the same and the values in other are greater than this)
                } else if (array[thisCounter].compareTo(other.get(otherCounter)) < 0){
                    // if we have a greater value at in this other, then...
                    // let's first store that value into the new list
                    newArray[openSpot] = array[thisCounter];
                    // let's move down this list
                    thisCounter++;
                    // let's move down the new list
                    openSpot++;
                }
            }
            // let's just add in the remaining elements from each list (if they have any remaining elements)
            while (thisCounter < arraySize) {
                newArray[openSpot] = array[thisCounter];
                // incrementing the counters for both the while loop above and our new list
                openSpot++;
                thisCounter++;
            }
            // let's just add in the remaining elements
            while (otherCounter < other.size()) {
                newArray[openSpot] = other.get(otherCounter);
                // incrementing the counters for both the while loop above and our new list
                openSpot++;
                otherCounter++;
            }
            // reassign the appropriate variables
            this.array = newArray;
            this.arraySize = openSpot;
            this.isSorted = true;
        }
    }

    public T getMin() {
        // Let's make the assumption that the first one is the min
        T min = array[0];
        // Let's iterate through and compare
        for (int i = 0; i < arraySize; i++) {
            // Let's test  if a > b
            if (min.compareTo(array[i]) > 0) {
                // so that would make the i th element new min.
                min = array[i];
            }
        }
        // return the min
        return min;
    }

    public T getMax() {
        // Let's make the assumption that the first one is the max
        T max = array[0];
        // Let's iterate through and compare
        for (int i = 0; i < arraySize; i++) {
            // Let's test  if a < b
            if (max.compareTo(array[i]) < 0) {
                // so that would make i th element the new max.
                max = array[i];
            }
        }
        return max;
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < arraySize; i++) {
            out += "Index " + i + ": " + array[i] + "\n";
        }
        return out;
    }
    // iterate through the array and check for ASC order
    // return true if the list is sorted from smallest to largest
    // iterated for the false test case, that way it can be done ASAP if the array is not correctly sorted
    public boolean isSorted() {
        // Special case if our list has 0 or 1 elements
        if (arraySize == 0 || arraySize == 1) {
        }
        // Go through the entire array and compare elements
        for (int i = 0; i < arraySize - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                isSorted = false;
                return isSorted;
            }
        }
        // assuming the list did not fail the previous loop, then our list is sorted and return that.
        isSorted = true;
        return isSorted;
    }
    private T[] array;
    private boolean isSorted;
    private int arraySize; //implemented to keep track of an array's size
}
