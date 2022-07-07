public class LinkedListDeque<T> implements Deque<T> {

    private class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;

        public Node(Node<T> p, T i, Node<T> n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }

        public Node(Node<T> p, Node<T> n) {
            this.prev = p;
            this.next = n;
        }
    }

    private Node<T> sentinel;

    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(sentinel, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> toBeAdded = new Node<>(sentinel, item, sentinel.next);
        sentinel.next.prev = toBeAdded;
        sentinel.next = toBeAdded;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (sentinel.prev == null) {
            Node<T> teBeAdded = new Node<>(sentinel, item, sentinel);
            sentinel.prev = teBeAdded;
            sentinel.next = teBeAdded;
        }
        Node<T> toBeAdded = new Node<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = toBeAdded;
        sentinel.prev = toBeAdded;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> cuurentNode = sentinel.next;
        while (cuurentNode.next != sentinel && cuurentNode != null) {
            StdOut.print(cuurentNode.item + " ");
            cuurentNode = cuurentNode.next;
        }
        StdOut.println();
    }

    @Override
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

    @Override
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

    @Override
    public T get(int index) {
        isValidIndex(index);
        int count = 0;
        Node<T> cuurentNode = sentinel.next;
        while (count < index && cuurentNode != null && cuurentNode != sentinel) {
            cuurentNode = cuurentNode.next;
            count++;
        }
        return cuurentNode.item;
    }

    private void isValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Error: Input invalid index!");
        }
    }
}
