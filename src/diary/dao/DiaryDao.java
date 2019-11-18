package diary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diary.beans.DiaryInfoBeans;

public class DiaryDao extends Dao {
    public final String getListSQL = "SELECT `diaries`.`id`, `students`.`last_name` AS `student_last_name`, `students`.`first_name` AS `student_first_name`, `courses`.`course_name`, `classes`.`grade`, `classes`.`class_name`, `diaries`.`date`, `diaries`.`regist_time`, `diaries`.`update_time`, `diaries`.`good_comment`, `diaries`.`bad_comment`, `diaries`.`about_comment`, `teachers`.`last_name` AS `teacher_last_name`, `teachers`.`first_name` AS `teacher_first_name`, `diaries`.`teacher_comment` FROM `diaries` INNER JOIN `students` ON `diaries`.`student_id` = `students`.`student_id` INNER JOIN `classes` ON `diaries`.`class_code` = `classes`.`class_code` INNER JOIN `courses` ON `classes`.`course_code` = `courses`.`course_code` LEFT OUTER JOIN `teachers` ON `diaries`.`teacher_id` = `teachers`.`teacher_id` WHERE `diaries`.`class_code` = ?;";

    public List<DiaryInfoBeans> getList(int classId) {
     // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return null;
        }

        // SQL Execute
        PreparedStatement stmt = null;
        DiaryInfoBeans diaryInfo = null;
        List<DiaryInfoBeans> diaryList = new ArrayList<DiaryInfoBeans>();
        try {
            stmt = con.prepareStatement(getListSQL);
            stmt.setInt(1, classId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                diaryInfo = new DiaryInfoBeans();
                diaryInfo.setDiaryId(rs.getInt("id"));
                diaryInfo.setUserName(rs.getString("student_last_name") + " " + rs.getString("student_first_name"));
                diaryInfo.setClassName(rs.getString("course_name") + rs.getString("grade") + rs.getString("class_name"));
                diaryInfo.setDate(rs.getDate("date"));
                diaryInfo.setRegistTime(rs.getTimestamp("regist_time"));
                diaryInfo.setUpdateTime(rs.getTimestamp("update_time"));
                diaryInfo.setGoodComment(rs.getString("good_comment"));
                diaryInfo.setBadComment(rs.getString("bad_comment"));
                diaryInfo.setAboutComment(rs.getString("about_comment"));

                if(rs.getString("teacher_last_name") == null || rs.getString("teacher_first_name") == null) {
                    diaryInfo.setTeacherName(null);
                } else {
                    diaryInfo.setTeacherName(rs.getString("teacher_last_name") + " " + rs.getString("teacher_first_name"));
                }

                diaryInfo.setTeacherComment(rs.getString("teacher_comment"));

                diaryList.add(diaryInfo);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        // If there is no result, return null
        if(diaryList.size() <= 0) {
            return null;
        }

        return diaryList;
    }
}
