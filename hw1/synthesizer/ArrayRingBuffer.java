package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    private T[] rb;

    private int first;

    private int last;

    // Construtor
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = last = 0;
    }
    // Abstract classes allow deletion of "@Override"

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }


    public T dequeue() {
        if (isEmpty()) {
            // throw a runtime exception
            throw new RuntimeException("Ring Buffer Underflow!");
        }
        T opi = rb[first];
        first++;
        if (first == capacity) {
            first = 0;
        }
        fillCount--;
        return opi;
    }

    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow!");
        }
        rb[last] = x;
        last++;
        if (last == capacity) {
            last = 0;
        }
        fillCount++;
    }


    @Override
    public Iterator<T> iterator() {
        Iterator<T> rbi = new RingBufferIterator();
        return rbi;
    }

    /*
     *  a private iterator class
     * */
    private class RingBufferIterator implements Iterator<T> {
        private int pos;
        private int count;

        private RingBufferIterator() {
            pos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            T nextItem = rb[pos];
            pos++;
            if (pos == capacity) {
                pos = 0;
            }
            count++;
            return nextItem;
        }
    }
}
