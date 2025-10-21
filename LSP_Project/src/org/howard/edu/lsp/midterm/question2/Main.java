package org.howard.edu.lsp.midterm.question2;



public class Main {
    public static void main(String[] args) {
        // Test all overloaded methods
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Exception demonstration
        try {
            AreaCalculator.area(-5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /*
     * Overloading is the better design choice here because all methods
     * perform the same fundamental operation (calculating area) but for
     * different shapes. This provides a consistent, intuitive API where
     * users don't need to remember different method names for the same
     * conceptual operation. The compiler determines which method to call
     * based on parameters, making the code cleaner and more readable.
     */
}