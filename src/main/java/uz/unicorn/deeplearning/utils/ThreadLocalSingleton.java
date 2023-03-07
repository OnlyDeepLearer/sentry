package uz.unicorn.deeplearning.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.unicorn.deeplearning.user.User;

/**
 * @author Doston Bokhodirov on 04 March 2023 at 10:57 PM
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalSingleton {

    private static final ThreadLocal<User> USER = ThreadLocal.withInitial(User::new);

    public static void removeUser() {
        remove(USER);
//        USER.remove();
    }

    public static <T> void remove(ThreadLocal<T> threadLocal) {
        threadLocal.remove();
    }

    public static User getUser() {
        return USER.get();
    }

    public static void setUser(User user) {
        ThreadLocalSingleton.USER.set(user);
    }

}
