package diary.model;

import diary.beans.UserInfoBeans;
import diary.dao.StudentDao;
import diary.dao.TeacherDao;

public class LoginModel {
    public UserInfoBeans login(String userId, String password) {
        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        UserInfoBeans userInfo = null;

        // Search from Student
        studentDao.connect();
        userInfo = studentDao.login(userId, password);

        // Search from Teacher
        if(userInfo == null) {
            teacherDao.connect();
            userInfo = teacherDao.login(userId, password);
            teacherDao.close();
        }

        studentDao.close();
        return userInfo;
    }
}
