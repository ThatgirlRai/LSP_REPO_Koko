package org.howard.edu.lsp.fexam.question1;
import java.security.SecureRandom;


	/**
	 * Generates passwords using letters (A-Z, a-z) and digits (0-9).
	 * Uses java.security.SecureRandom for cryptographically strong random generation.
	 */
	public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {
	    private static final String ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    private SecureRandom random = new SecureRandom();
	    
	    /**
	     * Generates a password containing letters and digits.
	     * @param length the desired password length
	     * @return a password string containing A-Z, a-z, and 0-9
	     */
	    @Override
	    public String generate(int length) {
	        StringBuilder password = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            password.append(ALLOWED.charAt(random.nextInt(ALLOWED.length())));
	        }
	        return password.toString();
	    }
	}


