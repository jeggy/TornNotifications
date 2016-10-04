package net.jebster.TornNotifications.Exceptions;

/**
 * Created by jeggy on 10/4/16.
 */

public class NegativeNumberException extends RuntimeException{
    public NegativeNumberException(String txt){
        super(txt);
    }
}
