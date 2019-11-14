package diary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import diary.beans.UserInfoBeans;
import diary.util.Password;

public class TeacherDao extends Dao {
    private final String loginSQL = "SELECT `teacher_id`, `first_name`, `last_name`, `password` FROM `teachers` WHERE `teacher_id` = ?;";

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
                    userInfo.setUserId(rs.getString("teacher_id"));
                    userInfo.setFirstName(rs.getString("first_name"));
                    userInfo.setLastName(rs.getString("last_name"));
                    userInfo.setType("teacher");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
