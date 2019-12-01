package diary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import diary.beans.UserInfoBeans;
import diary.util.Password;

public class StudentDao extends Dao {
    private final String loginSQL = "SELECT `students`.`student_id`, `students`.`first_name`, `students`.`last_name`, `students`.`password`, `students`.`class_code`, `courses`.`course_name`, `classes`.`grade`, `classes`.`class_name` FROM `students` INNER JOIN `classes` ON `students`.`class_code` = `classes`.`class_code` INNER JOIN `courses` ON `classes`.`course_code` = `courses`.`course_code` WHERE `students`.`student_id` = ?;";

    public UserInfoBeans login(String userId, String password) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return null;
        }

        // SQL Execute
        PreparedStatement stmt = null;
        UserInfoBeans userInfo = null;
        try {
            stmt = con.prepareStatement(loginSQL);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                // If matched password
                if(Password.match(password, rs.getString("password"))) {
                    userInfo = new UserInfoBeans();
                    userInfo.setUserId(rs.getString("student_id"));
                    userInfo.setFirstName(rs.getString("first_name"));
                    userInfo.setLastName(rs.getString("last_name"));
                    userInfo.setClassId(rs.getInt("class_code"));
                    userInfo.setClassName(rs.getString("course_name") + rs.getString("grade") + rs.getString("class_name"));
                    userInfo.setType("student");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
