package diary.beans;

import java.io.Serializable;
import java.util.Date;

public class DiaryInfoBeans implements Serializable {
    private int diaryId;
    private String userId;
    private String userName;
    private String className;
    private Date date;
    private Date registTime;
    private Date updateTime;
    private String goodComment;
    private String badComment;
    private String aboutComment;
    private String teacherName;
    private String teacherComment;

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGoodComment() {
        return goodComment;
    }

    public void setGoodComment(String goodComment) {
        this.goodComment = goodComment;
    }

    public String getBadComment() {
        return badComment;
    }

    public void setBadComment(String badComment) {
        this.badComment = badComment;
    }

    public String getAboutComment() {
        return aboutComment;
    }

    public void setAboutComment(String aboutComment) {
        this.aboutComment = aboutComment;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }
}
