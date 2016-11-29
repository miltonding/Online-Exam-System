package dao;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
import com.augmentun.exam.dao.PaperDao;
import com.augmentun.exam.model.Paper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class PaperDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    private PaperDao paperDao;

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
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
    public void testCreatePaper() {
        Paper paper = new Paper();
        paper.setExamId(100);
        Random random = new Random();
        Set<Integer> questionSet = new HashSet<Integer>();
        while(true) {
            int randomInt = random.nextInt(100);
            questionSet.add(randomInt);
                if (questionSet.size() >= 10) {
                    break;
                }
        }
        String temp = questionSet.toString();
        String questionIdArray = temp.substring(1, temp.length() - 1);
        System.out.println(questionIdArray);
        paper.setQuestionIdArray(questionIdArray);
        int result = paperDao.createPaper(paper);
        System.out.println(result + "........................");
    }

    @Test
    public void testupdatePaper() {
        Paper paper = new Paper();
        paper.setExamId(1);
        paper.setQuestionIdArray("1, 2, 3");
        int result = paperDao.updateQuestionIdArr(paper);
        System.out.println("result" + result +"...................");
    }

    @Test
    public void testGetPaperByExamId() {
        Paper paper = paperDao.getPaperByExamId(56);
        System.out.println(paper.getQuestionIdArray());
    }
}
