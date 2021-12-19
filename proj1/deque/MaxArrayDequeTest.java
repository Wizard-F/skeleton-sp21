package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest extends ArrayDequeTest{
    private static class IntComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class IntComparatorR implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    @Test
    public void maxTest() {

        MaxArrayDeque<Integer> di = new MaxArrayDeque<>(new IntComparator());
        di.addFirst(3);
        di.addFirst(4);
        di.addFirst(6);
        di.addFirst(0);
        di.addFirst(-3);

        assertEquals(6, (int) di.max());
        assertEquals(-3, (int) di.max(new IntComparatorR()));

    }
}
