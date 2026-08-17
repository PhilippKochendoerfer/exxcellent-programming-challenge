package de.exxcellent.challenge.exception;

/**
 * Thrown when a data file was read successfully but contained no row
 * usable for the requested computation (e.g. missing columns).
 * 
 */
public class NoDataFoundException extends Exception {

    public NoDataFoundException(String message) {
        super(message);
    }
}
