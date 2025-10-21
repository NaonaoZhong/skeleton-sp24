import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

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
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

        /* I've decided to add in comments the state after each call for the convenience of the
           person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addFirst(4);
        assertThat(lld1.toList()).containsExactly(4, -2, -1, 0, 1, 2, 1, 2, 3).inOrder();
    }

    // Below, you'll write your own tests for ArrayDeque61B.
    
    @Test
    public void sizeTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
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
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.get(2)).isEqualTo(0);
    }


    @Test
    public void removeFirstTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);
        lld1.removeFirst();
        assertThat(lld1.size()).isEqualTo(0);
        
        Deque61B<String> lld2 = new ArrayDeque61B<>();
        lld2.addLast("front"); // after this call we expect: ["front"]
        lld2.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld2.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        lld2.addFirst("prefront");
        assertThat(lld2.toList()).containsExactly("prefront","front", "middle", "back").inOrder();
        lld2.removeFirst();
        assertThat(lld2.toList()).containsExactly( "front","middle", "back").inOrder();
        assertThat(lld2.removeFirst()).isEqualTo("front");
        lld2.removeFirst();
        lld2.removeFirst();
        lld2.removeFirst();
        assertThat(lld2.removeFirst()).isEqualTo(null);
    }

    @Test
        public void removeLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isEqualTo(true);
        assertThat(lld1.size()).isEqualTo(0);
    
    }
// Tests bellow are generated by GPT-5-mini
    @Test
    public void removeUnderflowAndNulls() {
        Deque61B<Integer> d = new ArrayDeque61B<>();
        // removing from empty should return null and keep size 0
        assertThat(d.removeFirst()).isEqualTo(null);
        assertThat(d.removeLast()).isEqualTo(null);
        assertThat(d.size()).isEqualTo(0);

        // add then remove many times
        d.addFirst(1);
        d.addLast(2);
        assertThat(d.removeFirst()).isEqualTo(1);
        assertThat(d.removeLast()).isEqualTo(2);
        // now empty again
        assertThat(d.removeFirst()).isEqualTo(null);
        assertThat(d.size()).isEqualTo(0);
    }

    @Test
    public void interleavedOperationsStressResizeAndOrdering() {
        ArrayDeque61B<Integer> d = new ArrayDeque61B<>();
        java.util.ArrayList<Integer> model = new java.util.ArrayList<>();

        // Do a long sequence of interleaved ops to trigger multiple resizes and wraparound
        final int N = 500; // large enough to force several resizes with Factor=5
        for (int i = 0; i < N; i++) {
            if (i % 3 == 0) {
                d.addLast(i);
                model.add(i);
            } else if (i % 3 == 1) {
                d.addFirst(i);
                model.add(0, i);
            } else {
                d.addLast(i);
                model.add(i);
            }

            // occasionally remove to create holes and cause internal shifting logic to be exercised
            if (i % 10 == 0 && !model.isEmpty()) {
                Integer removedModel = model.remove(0);
                Integer removed = d.removeFirst();
                assertThat(removed).isEqualTo(removedModel);
            }
        }

        // Now remove randomly from front/back and compare to model
        java.util.Random rnd = new java.util.Random(42);
        while (!model.isEmpty()) {
            if (rnd.nextBoolean()) {
                Integer m = model.remove(0);
                Integer a = d.removeFirst();
                assertThat(a).isEqualTo(m);
            } else {
                Integer m = model.remove(model.size() - 1);
                Integer a = d.removeLast();
                assertThat(a).isEqualTo(m);
            }
        }

        // deque should be empty and behave correctly after being emptied
        assertThat(d.isEmpty()).isTrue();
        assertThat(d.removeFirst()).isNull();
        assertThat(d.removeLast()).isNull();
    }
}
