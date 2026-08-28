package de.exxcellent.challenge.exception;

/**
 * Thrown when a data file was read successfully but contained no row
 * usable for the requested computation (e.g. missing columns).
 */
public class NoDataFoundException extends Exception {
    /**
     * An exception indicating that no valid data was found in the file for the requested computation.
     * @param message detail message describing which computation found no usable data
     */
    public NoDataFoundException(String message) {
        super(message);
    }
}
