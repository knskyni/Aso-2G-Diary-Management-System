package diary.util;

public class HTML {
    public static String sanitize(String str) {

        if(CheckString.isEmpty(str)) {
            return "";
        }

        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\n", "<br>");
        str = str.replaceAll("\r\n", "<br>");
        str = str.replaceAll("\t", "    ");

        return str;
    }
}
