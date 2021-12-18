package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        assertTrue(ad.isEmpty());
        assertEquals(0, ad.size());
        ad.addFirst("item No.1");
        assertFalse(ad.isEmpty());
        assertEquals(1, ad.size());
        ad.addLast("No.2");
        assertEquals(2, ad.size());
        ad.addLast("last");
        assertEquals(3, ad.size());
        ad.printDeque();
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertTrue(ad.isEmpty());
        ad.addFirst(10);
        assertFalse(ad.isEmpty());
        ad.removeFirst();
        assertTrue(ad.isEmpty());
        ad.addLast(100);
        assertFalse(ad.isEmpty());
        ad.removeLast();
        assertTrue(ad.isEmpty());
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int N = 10_000;
        for (int i=0; i<N; i+=1) {
            int opNum = StdRandom.uniform(0, 6);
            switch (opNum) {
                case 0: // addFirst
                    int randVal = StdRandom.uniform(0, 100);
                    lld.addFirst(randVal);
                    ad.addFirst(randVal);
                    break;
                case 1: // addLast
                    randVal = StdRandom.uniform(0, 100);
                    lld.addLast(randVal);
                    ad.addLast(randVal);
                    break;
                case 2: // removeFirst
                    assertEquals(lld.removeFirst(), ad.removeFirst());
                    break;
                case 3: // removeLast
                    assertEquals(lld.removeLast(), ad.removeLast());
                    break;
                case 4: // isEmpty & size
                    assertEquals(lld.size(), ad.size());
                    assertEquals(lld.isEmpty(), lld.isEmpty());
                case 5: // get
                    if (lld.isEmpty()) {
                        break;
                    }
                    int index = StdRandom.uniform(0, lld.size());
                    assertEquals(lld.get(index), ad.get(index));
                    break;
            }
        }
    }
}
