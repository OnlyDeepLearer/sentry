package uz.unicorn.deeplearning.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 11:51 PM
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    public static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String likeFormat(String value) {
        return MessageFormat.format("%{0}%", value);
    }

    public static <T> Set<T> convertListToSet(List<T> roles) {
        return new HashSet<>(roles);
    }
}
