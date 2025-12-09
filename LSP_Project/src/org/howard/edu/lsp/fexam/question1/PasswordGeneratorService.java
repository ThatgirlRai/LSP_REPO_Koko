package org.howard.edu.lsp.fexam.question1;




	import java.util.HashMap;
	import java.util.Map;

	/**
	 * Singleton service for generating passwords using different algorithms.
	 * Provides a single shared access point for password generation across the application.
	 * 
	 * DESIGN PATTERNS USED:
	 * 
	 * 1. Singleton Pattern:
	 *    - Ensures only one instance of PasswordGeneratorService exists in the application.
	 *    - Provides global access through the getInstance() method.
	 *    - The private constructor prevents direct instantiation.
	 *    - This satisfies requirement #5: "Provide a single shared access point."
	 * 
	 * 2. Strategy Pattern:
	 *    - Encapsulates different password generation algorithms (basic, enhanced, letters)
	 *      in separate classes that implement the PasswordAlgorithm interface.
	 *    - Allows the algorithm to be selected and changed at runtime via setAlgorithm().
	 *    - New algorithms can be added by creating new classes implementing PasswordAlgorithm
	 *      without modifying existing code (Open/Closed Principle).
	 *    - This satisfies requirements #1-4: "Support multiple approaches", "Allow runtime selection",
	 *      "Support future expansion", and "Make behavior swappable."
	 * 
	 * Why These Patterns Are Appropriate:
	 * - Singleton ensures consistent state across the application and prevents multiple
	 *   instances from conflicting.
	 * - Strategy provides flexibility and extensibility, allowing new password generation
	 *   approaches to be added without changing the service class or client code.
	 * - Together, they provide a clean, maintainable architecture that meets all requirements.
	 */
	public class PasswordGeneratorService {
	    private static PasswordGeneratorService instance;
	    private Map<String, PasswordAlgorithm> algorithms;
	    private PasswordAlgorithm currentAlgorithm;
	    
	    /**
	     * Private constructor to prevent direct instantiation.
	     * Initializes the map of available algorithms.
	     */
	    private PasswordGeneratorService() {
	        algorithms = new HashMap<>();
	        algorithms.put("basic", new BasicPasswordAlgorithm());
	        algorithms.put("enhanced", new EnhancedPasswordAlgorithm());
	        algorithms.put("letters", new LettersPasswordAlgorithm());
	    }
	    
	    /**
	     * Returns the single instance of PasswordGeneratorService.
	     * Creates the instance on first call (lazy initialization).
	     * @return the singleton instance
	     */
	    public static PasswordGeneratorService getInstance() {
	        if (instance == null) {
	            instance = new PasswordGeneratorService();
	        }
	        return instance;
	    }
	    
	    /**
	     * Sets the password generation algorithm to use.
	     * @param name the algorithm name ("basic", "enhanced", or "letters")
	     */
	    public void setAlgorithm(String name) {
	        currentAlgorithm = algorithms.get(name);
	    }
	    
	    /**
	     * Generates a password using the currently selected algorithm.
	     * @param length the desired password length
	     * @return the generated password string
	     * @throws IllegalStateException if no algorithm has been set
	     */
	    public String generatePassword(int length) {
	        if (currentAlgorithm == null) {
	            throw new IllegalStateException("Algorithm not set");
	        }
	        return currentAlgorithm.generate(length);
	    }
	}



