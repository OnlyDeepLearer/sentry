package uz.unicorn.deeplearning.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:36 PM
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageKey {
    public static final String USER_NOT_FOUND = "user.not.found";
    public static final String SUCCESS = "success";
    public static final String INVALID_PHONE = "invalid.phone";
    public static final String INVALID_NAME = "invalid.name";
    public static final String SOMETHING_WENT_WRONG = "something.went.wrong";
    public static final String USER_ALREADY_EXISTS = "user.already.exists";
}
