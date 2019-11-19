package diary.model;

import java.util.Date;
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

    public boolean insert(DiaryInfoBeans diaryInfo) {
        DiaryDao diaryDao = new DiaryDao();
        boolean result = false;

        try {
            diaryDao.connect();
            diaryInfo.setDiaryId(diaryDao.getMaxId() + 1);
            diaryInfo.setRegistTime(new Date());
            diaryInfo.setUpdateTime(new Date());
            result = diaryDao.insert(diaryInfo);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            diaryDao.close();
        }

        return result;
    }
}
