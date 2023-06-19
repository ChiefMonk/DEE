package uct.cs.dee.Exceptions;

/**
 * Exception for missing rank builder for reasoner
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
@SuppressWarnings("serial")
public class MissingRankBuilderException extends RuntimeException
{
	 /**
     * Default constructor
     */
    public MissingRankBuilderException()
    {
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param message The error message
     */
    public MissingRankBuilderException(String message)
    {
        super(message);
    }
}