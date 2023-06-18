package uct.cs.dee.Exceptions;

/**
 * Exception for missing rank constructor for reasoner
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
@SuppressWarnings("serial")
public class MissingRankConstructorException extends RuntimeException
{
	 /**
     * Default constructor
     */
    public MissingRankConstructorException()
    {
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param message The error message
     */
    public MissingRankConstructorException(String message)
    {
        super(message);
    }
}