package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }


    /** Tests removing from an empty deque */
    @Test
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }


    /** Check if you can create LinkedListDeques with different parameterized types*/
    @Test
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }


    /** check if null is return when removing from an empty LinkedListDeque. */
    @Test
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }


    /** Add large number of elements to deque; check if order is correct. */
    @Test
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void equalsTest() {
        LinkedListDeque<String> strDeq = new LinkedListDeque<>();
        strDeq.addLast("corey");
        strDeq.addLast("schafer");

        int a = 10;
        Object b = null;
        LinkedListDeque<String> c = new LinkedListDeque<>();
        c.addLast("josh");
        LinkedListDeque<String> d = new LinkedListDeque<>();
        d.addFirst("schafer");
        d.addFirst("corey");
        ArrayDeque<String> e = new ArrayDeque<>();
        ArrayDeque<String> f = new ArrayDeque<>();
        f.addFirst("corey");
        f.addLast("schafer");
        ArrayDeque<Double> g = new ArrayDeque<>();
        g.addLast(100.0);
        g.addLast(2.1);

        assertFalse(strDeq.equals(a));
        assertFalse(strDeq.equals(b));
        assertFalse(strDeq.equals(c));
        assertTrue(strDeq.equals(d));
        assertFalse(strDeq.equals(e));
        assertTrue(strDeq.equals(f));
        assertFalse(strDeq.equals(g));

        LinkedListDeque<Integer> intDeq = new LinkedListDeque<>();
        intDeq.addLast(10);
        intDeq.addLast(100);
        assertFalse(intDeq.equals(strDeq));
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<Integer> intDeq = new LinkedListDeque<>();
        intDeq.addLast(3);
        intDeq.addLast(4);
        for (int item : intDeq) {
            System.out.print(item + " ");
        }
        System.out.println();

        intDeq.removeFirst();
        intDeq.removeFirst();
        for (int item : intDeq) {
            System.out.print(item + "..");
        }
        System.out.println();
    }
}
