package diary.util;

public class UserUtil {
    public static String getUserName(String last, String first) {

        if(StringUtil.isEmpty(last) || StringUtil.isEmpty(first)) {
            return "";
        }

        return last + " " + first;
    }

    public static String getClassName(String courseName, int grade, String className) {

        if(className == null) {
            className = "";
        }

        return courseName + grade + className;
    }
}
