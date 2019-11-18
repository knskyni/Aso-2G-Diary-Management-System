package diary.model;

import java.util.List;

import diary.beans.DiaryInfoBeans;
import diary.dao.DiaryDao;

public class DiaryModel {
    public List<DiaryInfoBeans> getList(int classId) {
        DiaryDao diaryDao = new DiaryDao();
        List<DiaryInfoBeans> diaryList = null;

        try {
            diaryDao.connect();
            diaryList = diaryDao.getList(classId);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            diaryDao.close();
        }

        return diaryList;
    }
}
