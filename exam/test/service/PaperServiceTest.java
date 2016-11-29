package service;

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
import com.augmentun.exam.service.PaperService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class PaperServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    private PaperService paperService;

    public void setPaperService(PaperService paperService) {
        this.paperService = paperService;
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
        paperService.createPaper(10, 1);
    }
}
