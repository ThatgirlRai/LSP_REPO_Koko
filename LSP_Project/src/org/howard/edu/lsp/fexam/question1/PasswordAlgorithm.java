package org.howard.edu.lsp.fexam.question1;

public interface PasswordAlgorithm {
	

	    /**
	     * Generates a password of the specified length.
	     * @param length the desired password length
	     * @return the generated password string
	     */
	    String generate(int length);
	}

