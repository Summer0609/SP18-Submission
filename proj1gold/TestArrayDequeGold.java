import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {

    @Test
    public void randomTestStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String cmd = "";
        for (int i = 0; i < 1000; i++) {
            if (sad.size() == 0) {
                // if the deque is empty, add a random item in two optional ways
                Integer itemToBeAdded = StdRandom.uniform(1000);
                int head_or_back = StdRandom.uniform(2);
                /*
                *   0 means add the item in the front, 1 represent the back
                * */
                if (head_or_back == 0) {
                    cmd = cmd + "addFirst " + itemToBeAdded + "\n";
                    sad.addFirst(itemToBeAdded);
                    ads.addFirst(itemToBeAdded);
                }
                else {
                    cmd = cmd + "addLast " + itemToBeAdded + "\n";
                    sad.addLast(itemToBeAdded);
                    ads.addLast(itemToBeAdded);
                }
            }
            else {
                /*
                *   If the deque is not empty, we have the following available operations:
                *    0. addFirst
                *    1. addLast
                *    2. removeFirst
                *    3. removeLast
                * */
                int op = StdRandom.uniform(4);
                Integer itemToBeAdded = StdRandom.uniform(1000);
                Integer adsRemoveItem = 1;
                Integer sadRemoveItem= 1;
                switch (op) {
                    case 0:
                        cmd = cmd + "addFirst " + itemToBeAdded + "\n";
                        sad.addFirst(itemToBeAdded);
                        ads.addFirst(itemToBeAdded);
                        break;
                    case 1:
                        cmd = cmd + "addLast " + itemToBeAdded + "\n";
                        sad.addLast(itemToBeAdded);
                        ads.addLast(itemToBeAdded);
                        break;
                    case 2:
                        cmd = cmd + "removeFirst " + "\n";
                        adsRemoveItem = ads.removeFirst();
                        sadRemoveItem = sad.removeFirst();
                        break;
                    case 3:
                        cmd = cmd + "removeLast " + "\n";
                        adsRemoveItem = ads.removeLast();
                        sadRemoveItem = sad.removeLast();
                        break;
                    default:
                }
                assertEquals(cmd, adsRemoveItem, sadRemoveItem);
            }
        }
    }

}
