package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * JUnit test class for IntegerSet implementation.
 * Tests all public methods with various scenarios including edge cases.
 */
class IntegerSetTest {
    private IntegerSet set1;
    private IntegerSet set2;
    private IntegerSet emptySet;

    @BeforeEach
    void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
        emptySet = new IntegerSet();
        
        // Initialize set1 with values 1, 2, 3
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Initialize set2 with values 2, 3, 4
        set2.add(2);
        set2.add(3);
        set2.add(4);
    }

    @Test
    @DisplayName("Test clear method")
    void testClear() {
        assertFalse(set1.isEmpty());
        set1.clear();
        assertTrue(set1.isEmpty());
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length method")
    void testLength() {
        assertEquals(3, set1.length());
        set1.add(5);
        assertEquals(4, set1.length());
        set1.remove(1);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        IntegerSet set3 = new IntegerSet();
        set3.add(1);
        set3.add(2);
        set3.add(3);
        
        IntegerSet set4 = new IntegerSet();
        set4.add(3);
        set4.add(2);
        set4.add(1); // Same elements, different order
        
        assertTrue(set1.equals(set3));
        assertTrue(set1.equals(set4)); // Order shouldn't matter
        assertFalse(set1.equals(set2));
        assertFalse(set1.equals(emptySet));
        
        // Test with null and different class
        assertFalse(set1.equals(null));
        assertFalse(set1.equals("not a set"));
    }

    @Test
    @DisplayName("Test contains method")
    void testContains() {
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(4));
        assertFalse(set1.contains(0));
        assertFalse(emptySet.contains(1));
    }

    @Test
    @DisplayName("Test largest method")
    void testLargest() {
        assertEquals(3, set1.largest());
        set1.add(5);
        assertEquals(5, set1.largest());
        set1.add(-1);
        assertEquals(5, set1.largest());
    }

    @Test
    @DisplayName("Test largest method with empty set")
    void testLargestEmptySet() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.largest();
        });
        assertEquals("Set is empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test smallest method")
    void testSmallest() {
        assertEquals(1, set1.smallest());
        set1.add(0);
        assertEquals(0, set1.smallest());
        set1.add(-5);
        assertEquals(-5, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest method with empty set")
    void testSmallestEmptySet() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.smallest();
        });
        assertEquals("Set is empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test add method")
    void testAdd() {
        assertEquals(3, set1.length());
        set1.add(4); // New element
        assertEquals(4, set1.length());
        assertTrue(set1.contains(4));
        
        set1.add(2); // Duplicate element
        assertEquals(4, set1.length()); // Size shouldn't change
    }

    @Test
    @DisplayName("Test remove method")
    void testRemove() {
        assertEquals(3, set1.length());
        set1.remove(2); // Existing element
        assertEquals(2, set1.length());
        assertFalse(set1.contains(2));
        
        set1.remove(99); // Non-existing element
        assertEquals(2, set1.length()); // Size shouldn't change
    }

    @Test
    @DisplayName("Test union method")
    void testUnion() {
        set1.union(set2);
        assertEquals(4, set1.length());
        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
    }

    @Test
    @DisplayName("Test union with empty set")
    void testUnionWithEmpty() {
        int originalSize = set1.length();
        set1.union(emptySet);
        assertEquals(originalSize, set1.length());
        
        emptySet.union(set1);
        assertEquals(3, emptySet.length());
        assertTrue(emptySet.equals(set1));
    }

    @Test
    @DisplayName("Test union with self")
    void testUnionWithSelf() {
        int originalSize = set1.length();
        set1.union(set1);
        assertEquals(originalSize, set1.length()); // Should remain unchanged
    }

    @Test
    @DisplayName("Test intersect method")
    void testIntersect() {
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(4));
    }

    @Test
    @DisplayName("Test intersect with empty set")
    void testIntersectWithEmpty() {
        set1.intersect(emptySet);
        assertTrue(set1.isEmpty());
        
        // Reset and test empty.intersect(non-empty)
        set1.add(1);
        set1.add(2);
        set1.add(3);
        emptySet.intersect(set1);
        assertTrue(emptySet.isEmpty());
    }

    @Test
    @DisplayName("Test intersect with self")
    void testIntersectWithSelf() {
        int originalSize = set1.length();
        set1.intersect(set1);
        assertEquals(originalSize, set1.length()); // Should remain unchanged
    }

    @Test
    @DisplayName("Test diff method")
    void testDiff() {
        set1.diff(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }

    @Test
    @DisplayName("Test diff with empty set")
    void testDiffWithEmpty() {
        int originalSize = set1.length();
        set1.diff(emptySet);
        assertEquals(originalSize, set1.length()); // Should remain unchanged
        
        emptySet.diff(set1);
        assertTrue(emptySet.isEmpty());
    }

    @Test
    @DisplayName("Test diff with self")
    void testDiffWithSelf() {
        set1.diff(set1);
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test complement method")
    void testComplement() {
        set1.complement(set2);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(3));
    }

    @Test
    @DisplayName("Test complement with empty set")
    void testComplementWithEmpty() {
        // First operation: non-empty.complement(empty)
        set1.complement(emptySet);
        assertTrue(set1.isEmpty());
        
        // RESET set1 for the second operation
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        // Second operation: empty.complement(non-empty)
        emptySet.complement(set1);
        assertEquals(3, emptySet.length());
        assertTrue(emptySet.equals(set1));
    }

    @Test
    @DisplayName("Test complement with self")
    void testComplementWithSelf() {
        set1.complement(set1);
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty method")
    void testIsEmpty() {
        assertFalse(set1.isEmpty());
        assertTrue(emptySet.isEmpty());
        
        set1.clear();
        assertTrue(set1.isEmpty());
        
        emptySet.add(1);
        assertFalse(emptySet.isEmpty());
    }

    @Test
    @DisplayName("Test toString method")
    void testToString() {
        String result = set1.toString();
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
        assertTrue(result.contains("3"));
        
        assertEquals("[]", emptySet.toString());
    }

    @Test
    @DisplayName("Test duplicate prevention")
    void testDuplicatePrevention() {
        IntegerSet testSet = new IntegerSet();
        testSet.add(1);
        testSet.add(1);
        testSet.add(1);
        assertEquals(1, testSet.length());
        
        testSet.add(2);
        testSet.add(2);
        assertEquals(2, testSet.length());
    }

    @Test
    @DisplayName("Test comprehensive set operations")
    void testComprehensiveOperations() {
        IntegerSet A = new IntegerSet();
        IntegerSet B = new IntegerSet();
        
        A.add(1);
        A.add(2);
        A.add(3);
        
        B.add(2);
        B.add(3);
        B.add(4);
        
        // Test union
        A.union(B);
        assertTrue(A.contains(1));
        assertTrue(A.contains(2));
        assertTrue(A.contains(3));
        assertTrue(A.contains(4));
        assertEquals(4, A.length());
        
        // Reset A
        A.clear();
        A.add(1);
        A.add(2);
        A.add(3);
        
        // Test intersection
        A.intersect(B);
        assertFalse(A.contains(1));
        assertTrue(A.contains(2));
        assertTrue(A.contains(3));
        assertFalse(A.contains(4));
        assertEquals(2, A.length());
        
        // Reset A
        A.clear();
        A.add(1);
        A.add(2);
        A.add(3);
        
        // Test difference
        A.diff(B);
        assertTrue(A.contains(1));
        assertFalse(A.contains(2));
        assertFalse(A.contains(3));
        assertFalse(A.contains(4));
        assertEquals(1, A.length());
    }
}