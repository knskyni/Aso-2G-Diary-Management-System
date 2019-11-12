package diary.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static String encode(String password) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe.encode(password);
    }

    public static boolean match(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe.matches(rawPassword, encodedPassword);
    }
}
