/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.dee.tool.models;

/**
 * <h1>IExplanationService<\h1>
 * The IExplanationService interface has methods that should be implemented for a full entailment explanation.
 * 
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 * @version 1.0.1
 * @since 2023-07-03
 */
public class ValidationResult<T> {
    private final boolean _isValid;
    private final String  _message;
    private final T _data;
    
    public ValidationResult(boolean isValid, T data)
    {
        _isValid = isValid;
        _message = null;
        _data = data;
    }
    
    public ValidationResult(String message)
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
