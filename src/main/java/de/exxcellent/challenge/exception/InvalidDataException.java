package de.exxcellent.challenge.exception;

/**
 * Thrown when a data file was read successfully but its content is not
 * usable for the requested computation (e.g. no rows, missing columns, or
 * a non-numeric value where a number was expected).
 */
public class InvalidDataException extends Exception {
    /**
     * An exception indicating that the data is not valid for the requested computation.
     * @param message detail message describing why the data could not be used
     */
    public InvalidDataException(String message) {
        super(message);
    }
}
