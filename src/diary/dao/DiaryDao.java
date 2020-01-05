package diary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diary.beans.DiaryInfoBeans;
import diary.util.UserUtil;

public class DiaryDao extends Dao {
    public final String getAllListSQL = "SELECT `diaries`.`id`, `students`.`last_name` AS `student_last_name`, `students`.`first_name` AS `student_first_name`, `courses`.`course_name`, `classes`.`grade`, `classes`.`class_name`, `diaries`.`date`, `diaries`.`regist_time`, `diaries`.`update_time`, `diaries`.`good_comment`, `diaries`.`bad_comment`, `diaries`.`about_comment`, `teachers`.`last_name` AS `teacher_last_name`, `teachers`.`first_name` AS `teacher_first_name`, `diaries`.`teacher_comment` FROM `diaries` INNER JOIN `students` ON `diaries`.`student_id` = `students`.`student_id` INNER JOIN `classes` ON `diaries`.`class_code` = `classes`.`class_code` INNER JOIN `courses` ON `classes`.`course_code` = `courses`.`course_code` LEFT OUTER JOIN `teachers` ON `diaries`.`teacher_id` = `teachers`.`teacher_id` WHERE `status` = 'public';";
    public final String getListSQL = "SELECT `diaries`.`id`, `diaries`.`student_id`, `students`.`last_name` AS `student_last_name`, `students`.`first_name` AS `student_first_name`, `courses`.`course_name`, `classes`.`grade`, `classes`.`class_name`, `diaries`.`date`, `diaries`.`regist_time`, `diaries`.`update_time`, `diaries`.`good_comment`, `diaries`.`bad_comment`, `diaries`.`about_comment`, `teachers`.`last_name` AS `teacher_last_name`, `teachers`.`first_name` AS `teacher_first_name`, `diaries`.`teacher_comment` FROM `diaries` INNER JOIN `students` ON `diaries`.`student_id` = `students`.`student_id` INNER JOIN `classes` ON `diaries`.`class_code` = `classes`.`class_code` INNER JOIN `courses` ON `classes`.`course_code` = `courses`.`course_code` LEFT OUTER JOIN `teachers` ON `diaries`.`teacher_id` = `teachers`.`teacher_id` WHERE `diaries`.`class_code` = ? AND `status` = 'public';";
    public final String insertCheckSQL = "SELECT COUNT(*) AS `count` FROM `diaries` WHERE `class_code` = ? AND `date` = ? AND `status` = 'public';";
    public final String insertSQL = "INSERT INTO `diaries`(`class_code`, `date`, `student_id`, `regist_time`, `update_time`, `good_comment`, `bad_comment`, `about_comment`) VALUES (?, ?, ?, ?, NULL, ?, ?, ?);";
    public final String getDiarySQL = "SELECT `diaries`.`id`, `diaries`.`student_id`, `students`.`last_name` AS `student_last_name`, `students`.`first_name` AS `student_first_name`, `courses`.`course_name`, `classes`.`grade`, `classes`.`class_name`, `diaries`.`date`, `diaries`.`good_comment`, `diaries`.`bad_comment`, `diaries`.`about_comment`, `teachers`.`last_name` AS `teacher_last_name`, `teachers`.`first_name` AS `teacher_first_name`, `diaries`.`teacher_comment` FROM `diaries` INNER JOIN `students` ON `diaries`.`student_id` = `students`.`student_id` INNER JOIN `classes` ON `students`.`class_code` = `classes`.`class_code` INNER JOIN `courses` ON `classes`.`course_code` = `courses`.`course_code` LEFT OUTER JOIN `teachers` ON `diaries`.`teacher_id` = `teachers`.`teacher_id` WHERE `diaries`.`id` = ? AND `diaries`.`status` = 'public';";
    public final String updateSQL = "UPDATE `diaries` SET `date` = ?, `update_time` = ?, `good_comment` = ?, `bad_comment` = ?, `about_comment` = ? WHERE `id` = ? AND `student_id` = ?;";
    public final String deleteSQL = "UPDATE `diaries` SET `status` = 'deleted' WHERE `id` = ? AND `student_id` = ?;";
    public final String teacherUpdateSQL = "UPDATE `diaries` SET `teacher_id` = ?, `teacher_comment` = ? WHERE `id` = ?;";
    public final String teacherDeleteSQL = "UPDATE `diaries` SET `teacher_id` = NULL, `teacher_comment` = NULL WHERE `id` = ?;";

    public List<DiaryInfoBeans> getList() {
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
            stmt = con.prepareStatement(getAllListSQL);
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
                diaryInfo.setUserId(rs.getString("student_id"));
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

    public boolean insertCheck(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prerare
        boolean result = false;

        // SQL Execute
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(insertCheckSQL);
            stmt.setInt(1, diaryInfo.getClassId());
            stmt.setDate(2, new java.sql.Date(diaryInfo.getDate().getTime()));
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                if(rs.getInt("count") == 0) {
                    result = true;
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public boolean insert(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prepare
        int executeNum = 0;

        // SQL Execute
        PreparedStatement stmt = null;

        try {
            // SQL Prepare
            stmt = con.prepareStatement(insertSQL);

            // Parameter Prepare
            stmt.setInt(1, diaryInfo.getClassId());
            stmt.setDate(2, new java.sql.Date(diaryInfo.getDate().getTime()));
            stmt.setString(3, diaryInfo.getUserId());
            stmt.setTimestamp(4, new java.sql.Timestamp(diaryInfo.getRegistTime().getTime()));
            stmt.setString(5, diaryInfo.getGoodComment());
            stmt.setString(6, diaryInfo.getBadComment());
            stmt.setString(7, diaryInfo.getAboutComment());

            // Execute
            executeNum = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (executeNum == 1) ? true : false;
    }

    public DiaryInfoBeans getDiary(int diaryId) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return null;
        }

        // SQL Execute
        PreparedStatement stmt = null;
        DiaryInfoBeans diaryInfo = null;
        try {
            stmt = con.prepareStatement(getDiarySQL);
            stmt.setInt(1, diaryId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                diaryInfo = new DiaryInfoBeans();
                diaryInfo.setDiaryId(rs.getInt("id"));
                diaryInfo.setUserId(rs.getString("student_id"));
                diaryInfo.setUserName(UserUtil.getUserName(rs.getString("student_last_name"), rs.getString("student_first_name")));
                diaryInfo.setClassName(UserUtil.getClassName(rs.getString("course_name"), rs.getInt("grade"), rs.getString("class_name")));
                diaryInfo.setDate(rs.getDate("date"));
                diaryInfo.setGoodComment(rs.getString("good_comment"));
                diaryInfo.setBadComment(rs.getString("bad_comment"));
                diaryInfo.setAboutComment(rs.getString("about_comment"));
                diaryInfo.setTeacherName(UserUtil.getUserName(rs.getString("teacher_last_name"), rs.getString("teacher_first_name")));
                diaryInfo.setTeacherComment(rs.getString("teacher_comment"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return diaryInfo;
    }

    public boolean update(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prepare
        int executeNum = 0;

        // SQL Execute
        PreparedStatement stmt = null;

        try {
            // SQL Prepare
            stmt = con.prepareStatement(updateSQL);

            // Parameter Prepare
            stmt.setDate(1, new java.sql.Date(diaryInfo.getDate().getTime()));
            stmt.setTimestamp(2, new java.sql.Timestamp(diaryInfo.getUpdateTime().getTime()));
            stmt.setString(3, diaryInfo.getGoodComment());
            stmt.setString(4, diaryInfo.getBadComment());
            stmt.setString(5, diaryInfo.getAboutComment());
            stmt.setInt(6, diaryInfo.getDiaryId());
            stmt.setString(7, diaryInfo.getUserId());

            // Execute
            executeNum = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (executeNum == 1) ? true : false;
    }

    public boolean delete(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prepare
        int executeNum = 0;

        // SQL Execute
        PreparedStatement stmt = null;

        try {
            // SQL Prepare
            stmt = con.prepareStatement(deleteSQL);

            // Parameter Prepare
            stmt.setInt(1, diaryInfo.getDiaryId());
            stmt.setString(2, diaryInfo.getUserId());

            // Execute
            executeNum = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (executeNum == 1) ? true : false;
    }

    public boolean teacherUpdate(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prepare
        int executeNum = 0;

        // SQL Execute
        PreparedStatement stmt = null;

        try {
            // SQL Prepare
            stmt = con.prepareStatement(teacherUpdateSQL);

            // Parameter Prepare
            stmt.setString(1, diaryInfo.getTeacherId());
            stmt.setString(2, diaryInfo.getTeacherComment());
            stmt.setInt(3, diaryInfo.getDiaryId());

            // Execute
            executeNum = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (executeNum == 1) ? true : false;
    }

    public boolean teacherDelete(DiaryInfoBeans diaryInfo) {
        // If not connected to DB, return null
        if(con == null) {
            System.out.println("データベースに接続していません。");
            return false;
        }

        // Prepare
        int executeNum = 0;

        // SQL Execute
        PreparedStatement stmt = null;

        try {
            // SQL Prepare
            stmt = con.prepareStatement(teacherDeleteSQL);

            // Parameter Prepare
            stmt.setInt(1, diaryInfo.getDiaryId());

            // Execute
            executeNum = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (executeNum == 1) ? true : false;
    }
}
