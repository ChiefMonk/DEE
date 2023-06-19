package uct.cs.dee.exceptions;

/**
 * Exception for missing ranking for reasoner
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
@SuppressWarnings("serial")
public class MissingRankingException extends RuntimeException
{
	/**
    * Default constructor
    */
   public MissingRankingException()
   {
       super();
   }

   /**
    * Parameterized constructor
    * 
    * @param message The error message
    */
   public MissingRankingException(String message)
   {
       super(message);
   }
}