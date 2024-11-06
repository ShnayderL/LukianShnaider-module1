package Exceptions;

public class IllegalCommandException extends RuntimeException{
    public IllegalCommandException(String message){
        super(message);
    }
    public IllegalCommandException(){}
}
