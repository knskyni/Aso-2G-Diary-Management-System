package diary.util;

import diary.beans.UserInfoBeans;

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

    public static boolean isTeacher(UserInfoBeans userInfo) {

        if(userInfo.getType().equals("teacher")) {
            return true;
        }

        return false;
    }

    public static boolean isStudent(UserInfoBeans userInfo) {

        if(userInfo.getType().equals("student")) {
            return true;
        }

        return false;
    }
}
