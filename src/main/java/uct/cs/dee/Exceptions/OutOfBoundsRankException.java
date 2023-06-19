/**
 * 
 */
package uct.cs.dee.Exceptions;

/**
 * Exception for for out of bounds rank in ranking
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class OutOfBoundsRankException extends RuntimeException {
    
    /**
     * Default constructor
     */
    public OutOfBoundsRankException() {
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param message The error message
     */
    public OutOfBoundsRankException(String message)
    {
        super(message);
    }
}
