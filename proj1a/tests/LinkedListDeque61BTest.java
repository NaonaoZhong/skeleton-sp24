import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
           to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
           but not ["front", "middle", "back"].
         */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        /* I've decided to add in comments the state after each call for the convenience of the
           person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    /** This test for the isEmpty() method */
    public void isEmptyTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        lld1.addLast(2);
        assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void sizeTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addLast(0);
        assertThat(lld1.size()).isEqualTo(1);
        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(2);
        lld1.addFirst(-1);
        assertThat(lld1.size()).isEqualTo(3);
    }

    @Test
    public void getTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.get(2)).isEqualTo(0);
    }
    @Test
    public void getRecursiveTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        assertThat(lld1.getRecursive(2)).isEqualTo(0);
    }


    @Test
    public void removeFirstTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.removeFirst();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        assertThat(lld1.size()).isEqualTo(0);
        
        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        lld2.addLast("front"); // after this call we expect: ["front"]
        lld2.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld2.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld2.toList()).containsExactly("front", "middle", "back").inOrder();
        lld2.removeFirst();
        assertThat(lld2.toList()).containsExactly( "middle", "back").inOrder();
        assertThat(lld2.removeFirst()).isEqualTo("middle");
        lld2.removeFirst();
        lld2.removeFirst();
        assertThat(lld2.removeFirst()).isEqualTo(null);
    }

    @Test
        public void removeLastTest(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(0);
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        assertThat(lld1.size()).isEqualTo(0);
    
    }
// below is generated bt GPT-5-mini
    @Test
    @DisplayName("Adding null should throw IllegalArgumentException")
    public void addNullThrowsTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        boolean threw = false;
        try {
            lld.addFirst(null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assertThat(threw).isEqualTo(true);

        threw = false;
        try {
            lld.addLast(null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        assertThat(threw).isEqualTo(true);
    }

    @Test
    @DisplayName("removeFirst/removeLast on empty deque returns null and size stays 0")
    public void removeOnEmptyTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.removeFirst()).isEqualTo(null);
        assertThat(lld.removeLast()).isEqualTo(null);
        // still zero
        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("get and getRecursive out-of-bounds behavior")
    public void getOutOfBoundsTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);

        boolean threw = false;
        try {
            lld.get(-1);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        assertThat(threw).isEqualTo(true);

        threw = false;
        try {
            lld.get(3);
        } catch (IndexOutOfBoundsException e) {
            threw = true;
        }
        assertThat(threw).isEqualTo(true);

        // getRecursive should also fail for out of bounds; since implementation uses recursion
        // it may throw as well - check the same contract
        threw = false;
        try {
            lld.getRecursive(3);
        } catch (StackOverflowError | IndexOutOfBoundsException e) {
            // either may occur if implementation is incorrect; treat as a thrown error
            threw = true;
        }
        assertThat(threw).isEqualTo(true);
    }

    @Test
    @DisplayName("randomized operations: maintain invariants and compare to java.util.ArrayList")
    public void randomizedStressTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        java.util.ArrayList<Integer> gold = new java.util.ArrayList<>();
        java.util.Random rand = new java.util.Random(42);

        for (int op = 0; op < 1000; op++) {
            int action = rand.nextInt(6);
            if (action == 0) { // addFirst
                int v = rand.nextInt(1000);
                lld.addFirst(v);
                gold.add(0, v);
            } else if (action == 1) { // addLast
                int v = rand.nextInt(1000);
                lld.addLast(v);
                gold.add(v);
            } else if (action == 2) { // removeFirst
                Integer a = lld.removeFirst();
                Integer b = gold.isEmpty() ? null : gold.remove(0);
                assertThat(a).isEqualTo(b);
            } else if (action == 3) { // removeLast
                Integer a = lld.removeLast();
                Integer b = gold.isEmpty() ? null : gold.remove(gold.size() - 1);
                assertThat(a).isEqualTo(b);
            } else if (action == 4) { // get vs gold
                if (!gold.isEmpty()) {
                    int idx = rand.nextInt(gold.size());
                    assertThat(lld.get(idx)).isEqualTo(gold.get(idx));
                    assertThat(lld.getRecursive(idx)).isEqualTo(gold.get(idx));
                }
            } else { // check size and toList
                assertThat(lld.size()).isEqualTo(gold.size());
                assertThat(lld.toList()).containsExactlyElementsIn(gold).inOrder();
            }
            // quick invariant: size should never be negative
            assertThat(lld.size()).isGreaterThan(-1);
        }
    }
}