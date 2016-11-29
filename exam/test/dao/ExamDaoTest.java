package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.augmentun.exam.Constants;
import com.augmentun.exam.common.AppContext;
import com.augmentun.exam.dao.ExamDao;
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class ExamDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    private ExamDao examDao;

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Before
    public void setUp() throws Exception{
        System.out.println("before.............................");
        AppContext appContext = AppContext.getAppContext();
        appContext.addObject(Constants.APP_HTTP_SESSION, new MockHttpSession());
    }

    @After
    public void tearDowm() throws Exception{
        System.out.println("after...........................");
        AppContext appContext = AppContext.getAppContext();
        appContext.clear();
    }

    @Test
    public void testCreateExam() {
        Exam exam = new Exam();
        exam.setCreateUserId(1);
        exam.setExamName("2");
        exam.setDescription("22");
        exam.setSingleQuestionScore(2);
        exam.setQuantity(10);
        exam.setEffcientTime("2016-8-1 10:00:00");
        exam.setExamDuration(10);
        exam.setTotalScore(20);
        exam.setPassCriteria(10);
        examDao.createExam(exam);
    }

    @Test
    public void testListExam() {
        ExamPagination pagination = new ExamPagination();
        pagination.setOffset(0);
        pagination.setPageSize(10);
        pagination.setKeyword("");
        pagination.setStartDate("");
        pagination.setEndDate("");
        pagination.setOrder("idASC");
        List<Exam> listExam = examDao.listExam(pagination);
        System.out.println("size = " + listExam.size());
        for (Exam exam: listExam) {
            System.out.println(exam.getId());
        }
    }

    @Test
    public void testGetExamQuantity() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("keyword", "e");
        conditions.put("startDate", "");
        conditions.put("endDate", "");
        System.out.println(examDao.getExamQuantity(conditions));
    }

    @Test
    public void testUpdateExam() {
        Exam exam = new Exam();
        exam.setExamName("rtqetea");
    }

    @Test
    public void updateAvailable() {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(51);
        arr.add(52);
        arr.add(53);
        arr.add(54);
        int result = examDao.updateAvailable(arr);
        System.out.println(result);
    }

}
