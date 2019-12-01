package diary.util;

public class CheckString {
    public static boolean isEmpty(String str) {
        if(str == null) {
            return true;
        }

        str = str.replaceAll("\n", "");
        str = str.replaceAll("\r\n", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll(" ", "");
        str = str.replaceAll("　", "");

        if(str.equals("")) {
            return true;
        }

        return false;
    }
}
