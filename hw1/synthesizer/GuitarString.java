package synthesizer;

public class GuitarString {

    private static final int SR = 44100;

    private static final double FACTOR = 0.996;

    //Bound Queue to store data
    private BoundedQueue<Double> bq;

    public GuitarString(double frequency) {
        bq = new ArrayRingBuffer<>((int) Math.round(SR / frequency));
        for (int i = 0; i < bq.capacity(); i++) {
            bq.enqueue(0.0);
        }
    }


    public double sample() {
        return bq.peek();
    }

    public void pluck() {
        // the first step of The Karplus-Algorithm
        for (int i = 0; i < bq.capacity(); i++) {
            bq.dequeue();
            // Generate a random number from 0 to 1
            bq.enqueue(Math.random() - 0.5); // special generation
        }
    }

    public void tic() {
        double dqi = bq.dequeue();
        bq.enqueue(FACTOR * 0.5 * (sample() + dqi));
    }
}
