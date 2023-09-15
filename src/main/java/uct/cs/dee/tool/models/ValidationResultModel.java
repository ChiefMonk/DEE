package uct.cs.dee.tool.models;

/**
 * <h1>ValidationResultModel<\h1>
 * The ValidationResult Model.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @param <T>
 * @since 2023-07-03
 */
public class ValidationResultModel<T> {
    private final boolean _isValid;
    private final String  _message;
    private final T _data;
    
    public ValidationResultModel(boolean isValid, T data)
    {
        _isValid = isValid;
        _message = null;
        _data = data;
    }
    
    public ValidationResultModel(String message)
    {
        _isValid = false;
        _data = null;
        _message = message;
    }
    
    public boolean isValid() {
        return _isValid;
    }
    
    public String getMessage() {
        return _message;
    }
    
    public T getData() {
        return _data;
    }
}
