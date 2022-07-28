package synthesizer;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity(); // return the size of the buffer
    int fillCount(); // return number of items currently
    void enqueue(T x); // add item x to the end
    T dequeue(); // delete and return item from the front
    T peek();  // return (not delete) item from the front

    /*
    *   default methods in the interface:
    *       method body should be added in the interface
    * */

    default boolean isEmpty() {
        // is the buffer empty (fillCount equals zero)?
        return fillCount() == 0;
    }

    default boolean isFull() {
        // is the buffer full (fillCount is same as capacity)?
        return fillCount() == capacity();
    }
}
