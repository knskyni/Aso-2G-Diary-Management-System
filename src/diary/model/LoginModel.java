package diary.model;

import diary.beans.UserInfoBeans;
import diary.dao.StudentDao;
import diary.dao.TeacherDao;

public class LoginModel {
    public UserInfoBeans login(String userId, String password) {
        StudentDao studentDao = new StudentDao();
        TeacherDao teacherDao = new TeacherDao();
        UserInfoBeans userInfoBeans = null;

        // Search from Student
        studentDao.connect();
        userInfoBeans = studentDao.login(userId, password);

        // Search from Teacher
        if(userInfoBeans == null) {
            teacherDao.connect();
            userInfoBeans = teacherDao.login(userId, password);
            teacherDao.close();
        }

        studentDao.close();
        return userInfoBeans;
    }
}
