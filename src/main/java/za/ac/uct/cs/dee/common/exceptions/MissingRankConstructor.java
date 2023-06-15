package za.ac.uct.cs.dee.common.exceptions;

/**
 * Exception for missing rank constructor for reasoner
 */
public class MissingRankConstructor extends RuntimeException{

    /**
     * Default constructor
     */
    public MissingRankConstructor(){
        super();
    }

    /**
     * Parameterized constructor
     * 
     * @param message The error message
     */
    public MissingRankConstructor(String message){
        super(message);
    }
}
