package service;

import java.util.HashMap;
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
import com.augmentun.exam.model.Exam;
import com.augmentun.exam.model.ExamPagination;
import com.augmentun.exam.service.ExamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class ExamServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    private ExamService examService;

    public void setExamService(ExamService examService) {
        this.examService = examService;
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
        exam.setExamName("english test");
        exam.setDescription("english test");
        exam.setEffcientTime("2016-8-13T20:00");
        exam.setExamDuration(60);
        exam.setQuantity(10);
        exam.setSingleQuestionScore(10);
        exam.setTotalScore(100);
        exam.setPassCriteria(60);
    }

    @Test
    public void listExam() {
        ExamPagination pagination = new ExamPagination();
        pagination.setOffset(0);
        pagination.setPageSize(5);
        examService.listExam(pagination);
    }
    @Test
    public void TestGetPageCount() {
        Map<String, String> conditions = new HashMap<String, String>();
        conditions.put("keyword", "e");
        conditions.put("startDate", "");
        conditions.put("endDate", "");
        System.out.println(examService.getPageCount(conditions, 3));
    }
}
