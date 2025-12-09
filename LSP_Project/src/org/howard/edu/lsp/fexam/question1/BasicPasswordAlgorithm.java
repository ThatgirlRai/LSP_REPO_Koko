package org.howard.edu.lsp.fexam.question1;
import java.util.Random;

	
	/**
	 * Generates passwords using digits only (0-9).
	 * Uses java.util.Random for random number generation.
	 */
	public class BasicPasswordAlgorithm implements PasswordAlgorithm {
	    private static final String DIGITS = "0123456789";
	    private Random random = new Random();
	    
	    /**
	     * Generates a password containing only digits.
	     * @param length the desired password length
	     * @return a password string containing only digits (0-9)
	     */
	    @Override
	    public String generate(int length) {
	        StringBuilder password = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	        }
	        return password.toString();
	    }
	}


