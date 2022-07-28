package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    // "protected" means to allow the class itself to access them
    protected int fillCount;
    protected int capacity;


    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    /*
     *   Such abstract method should contain an "abstract" keyword
     *      without implementation
     *        (no braces, followed by a semicolon)
     */
}
