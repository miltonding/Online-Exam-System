package dao;

import java.util.ArrayList;
import java.util.List;

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
import com.augmentun.exam.dao.QuestionDao;
import com.augmentun.exam.model.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class QuestionDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
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
    public void testFindQuestionIdArr() {
        List<Integer> findQuestionIdArr = questionDao.findQuestionIdArr(200);
        System.out.println(findQuestionIdArr);
    }

    @Test
    public void findSeletedQuestion() {
        List<Integer> questionIdList = new ArrayList<Integer>();
        questionIdList.add(101);
        questionIdList.add(102);
        questionIdList.add(103);
        List<Question> findSeletedQuestion = questionDao.findSeletedQuestion(questionIdList);
        for (Question question: findSeletedQuestion) {
            System.out.println(question.getQuestionName());
        }
    }
}
