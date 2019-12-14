package diary.util;

public class UserUtil {
    public static String getUserName(String last, String first) {

        if(StringUtil.isEmpty(last) || StringUtil.isEmpty(first)) {
            return "";
        }

        return last + " " + first;
    }

    public static String getClassName(String courseName, Integer grade, String className) {

        if(grade == 0) {
            grade = null;
        }

        return StringUtil.nullToEmpty(courseName) + (grade == null ? "" : grade) + StringUtil.nullToEmpty(className);
    }
}
