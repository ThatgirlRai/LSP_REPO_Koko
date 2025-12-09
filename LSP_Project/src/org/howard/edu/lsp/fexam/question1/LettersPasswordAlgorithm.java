package org.howard.edu.lsp.fexam.question1;



import java.util.Random;

/**
 * Generates passwords using letters only (A-Z, a-z).
 * Uses java.util.Random for random number generation.
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Random random = new Random();
    
    /**
     * Generates a password containing only letters.
     * @param length the desired password length
     * @return a password string containing only letters (A-Z, a-z)
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return password.toString();
    }
}

