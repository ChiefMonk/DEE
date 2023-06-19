package uct.cs.dee.Exceptions;

/**
 * Exception for invalid formula exception
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
@SuppressWarnings("serial")
public class InvalidFormulaException  extends RuntimeException 
{

	/**
     * Default constructor
     */
    public InvalidFormulaException()
    {
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param message The error message
     */
    public InvalidFormulaException(String message)
    {
        super(message);
    }

}
