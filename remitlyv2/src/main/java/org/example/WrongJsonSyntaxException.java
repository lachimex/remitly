package org.example;

public class WrongJsonSyntaxException extends Exception{
    public WrongJsonSyntaxException(String message){
        super(message);
    }
}
