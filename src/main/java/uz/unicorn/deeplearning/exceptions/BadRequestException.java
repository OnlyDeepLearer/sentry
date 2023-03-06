package uz.unicorn.deeplearning.exceptions;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:42 PM
 */

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
