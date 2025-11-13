package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a mathematical set of integers with standard set operations.
 * The set does not allow duplicate elements and provides methods for
 * union, intersection, difference, and complement operations.
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Clears the internal representation of the set, removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * 
     * @return the number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if the two sets are equal, false otherwise.
     * Two sets are equal if they contain all of the same values in ANY order.
     * This overrides the equals method from the Object class.
     * 
     * @param o the object to compare with
     * @return true if the sets contain the same elements, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        IntegerSet otherSet = (IntegerSet) o;
        if (this.length() != otherSet.length()) return false;
        
        for (Integer element : set) {
            if (!otherSet.contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the set contains the value, otherwise false.
     * 
     * @param value the value to check for membership
     * @return true if the set contains the value, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set (throws IllegalStateException if empty).
     * 
     * @return the largest element in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int max = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) > max) {
                max = set.get(i);
            }
        }
        return max;
    }

    /**
     * Returns the smallest item in the set (throws IllegalStateException if empty).
     * 
     * @return the smallest element in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        
        int min = set.get(0);
        for (int i = 1; i < set.size(); i++) {
            if (set.get(i) < min) {
                min = set.get(i);
            }
        }
        return min;
    }

    /**
     * Adds an item to the set or does nothing if already present.
     * 
     * @param item the integer to add to the set
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set or does nothing if not there.
     * 
     * @param item the integer to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Set union: modifies this to contain all unique elements in this or other.
     * 
     * @param other the other IntegerSet to union with
     */
    public void union(IntegerSet other) {
        for (int i = 0; i < other.length(); i++) {
            int element = other.set.get(i);
            if (!contains(element)) {
                add(element);
            }
        }
    }

    /**
     * Set intersection: modifies this to contain only elements in both sets.
     * 
     * @param other the other IntegerSet to intersect with
     */
    public void intersect(IntegerSet other) {
        List<Integer> intersection = new ArrayList<>();
        for (Integer element : set) {
            if (other.contains(element)) {
                intersection.add(element);
            }
        }
        set = intersection;
    }

    /**
     * Set difference (this \ other): modifies this to remove elements found in other.
     * 
     * @param other the other IntegerSet to subtract from this set
     */
    public void diff(IntegerSet other) {
        List<Integer> temp = new ArrayList<>(set);
        for (Integer element : temp) {
            if (other.contains(element)) {
                remove(element);
            }
        }
    }

    /**
     * Set complement: modifies this to become (other \ this).
     * 
     * @param other the other IntegerSet used for complement operation
     */
    public void complement(IntegerSet other) {
        List<Integer> complementSet = new ArrayList<>();
        for (int i = 0; i < other.length(); i++) {
            int element = other.set.get(i);
            if (!this.contains(element)) {
                complementSet.add(element);
            }
        }
        set = complementSet;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * 
     * @return true if the set contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a String representation; overrides Object.toString().
     * 
     * @return a string representation of the set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}