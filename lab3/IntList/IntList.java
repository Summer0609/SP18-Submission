import jh61b.junit.In;
import java.util.Formatter;
public class IntList {
    private int first;
    private IntList rest;
    //Constructor
    public IntList(int f, IntList r) {
        this.first = f;
        this.rest = r;
    }




    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null)
            return B;
        IntList C =A;
        while (C.rest != null) {
            C = C.rest;
        }
        C.rest = B;
        return A;
    }

    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList ptrA = A;
        IntList res = new IntList(0, null);
        IntList ptrRes = res;
        while (ptrA != null) {
            ptrRes.rest = new IntList(ptrA.first, null);
            ptrRes = ptrRes.rest;
            ptrA = ptrA.rest;
        }
        ptrRes.rest = B;
        return res.rest;
    }

    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    public static IntList squareListRecursive(IntList L) {
        if (L == null)
            return null;
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    public static IntList reverse(IntList L) {
        if (L == null)
            return null;
        IntList first = new IntList(L.first, null);
        IntList rest = reverse(L.rest);
        return catenate(rest, first);
    }
    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not expected to
     * read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints as
     * THIS. Cannot handle IntLists with cycles. You are not expected to read or
     * understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method returns an integer equal to the
     * item number of the location where the cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a utility
     * method for lab2. You are not expected to read, understand, or even use this
     * method. The point of this method is so that if you convert an IntList into a
     * String and that IntList has a loop, your computer doesn't get stuck in an
     * infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;

        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /**
     * Outputs the IntList as a String. You are not expected to read or understand
     * this method.
     */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}