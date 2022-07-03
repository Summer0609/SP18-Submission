import edu.princeton.cs.algs4.StdOut;



public class LinkedListDeque<T> {
    /*
    * Use inner class method to create the linked-based data structure
    * */
    private class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;

        /*
        * Node constructor
        * */
        public Node(Node<T> prev, T item, Node<T> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public Node(Node<T> prev, Node<T> next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> sentinel;
    //use the sentinel method, the node "sentinel.next" is the first element of the deque

    private int size;

    /*
    * Constructor of the linkedlist deque
    * */

    public LinkedListDeque() {
        sentinel = new Node<>(sentinel, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T oldFirstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return oldFirstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T oldLastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return oldLastItem;
    }

    public void printDeque() {
        Node<T> currentNode = sentinel.next;
        while (currentNode.next != sentinel) {
            StdOut.print(currentNode.item + " ");
            currentNode = currentNode.next;
        }
    }

    public T get(int index) {
        isValidIndex(index);
        int count = 0;
        Node<T> currentNode = sentinel.next;
        while (count < index) {
            currentNode = currentNode.next;
            count++;
        }
        return currentNode.item;
    }


    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /*
    * Use helper method to do the recursion
    * */

    private T getRecursive(Node<T> beginning, int index) {
        isValidIndex(index);
        if (index == 0) {
            return beginning.item;
        }
        return getRecursive(beginning.next, index - 1);
    }
    /*
    * Judge whether the index is valid or not
    * */
    private void isValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Error: invalid index!");
        }
    }
}
