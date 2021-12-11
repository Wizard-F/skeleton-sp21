package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> rightList = new AListNoResizing<>();
        rightList.addLast(4);
        rightList.addLast(5);
        rightList.addLast(6);
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        buggyList.addLast(4);
        buggyList.addLast(5);
        buggyList.addLast(6);
        assertEquals(rightList.removeLast(), buggyList.removeLast());
        assertEquals(rightList.removeLast(), buggyList.removeLast());
        assertEquals(rightList.removeLast(), buggyList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> expectedList = new AListNoResizing<>();
        BuggyAList<Integer> actualList = new BuggyAList<>();
        int N = 5000;
        for (int i=0; i<N; i+=1) {
            int opNum = StdRandom.uniform(0, 4);
            switch (opNum) {
                case 0: // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    expectedList.addLast(randVal);
                    actualList.addLast(randVal);
                    break;
                case 1: // size
                    assertEquals(expectedList.size(), actualList.size());
                    break;
                case 2: // getLast
                    if (expectedList.size()>0 && actualList.size()>0) {
                        assertEquals(expectedList.getLast(), actualList.getLast());
                    }
                    break;
                case 3: // removeLast
                    if (expectedList.size()>0 && actualList.size()>0) {
                        assertEquals(expectedList.removeLast(), actualList.removeLast());
                    }
                    break;
            }
        }
    }
}
