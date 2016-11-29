package service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.augmentun.exam.exception.ParamterException;
import com.augmentun.exam.model.Pagination;
import com.augmentun.exam.model.Question;
import com.augmentun.exam.service.QuestionService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:exam-mvc.xml"})
public class QuestionServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    private QuestionService questionService;

    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Test
    public void testGetQuestionQtyByIdTure() {
        boolean result = questionService.getQuestionQtyById("Q78140");
        System.out.println(result + ".............");
    }

    @Test
    public void testGetQuestionQtyByIdFalse() {
        boolean result = questionService.getQuestionQtyById("Q142");
        System.out.println(result + ".............");
    }


    @Test
    public void testUpdateQuestionAvailableCorrect() {
        List<String> list = new ArrayList<String>();
        list.add("58");
        boolean result = questionService.updateQuestionAvailable(list);
        System.out.println(result + ".............");
    }

    @Test
    public void testUpdateQuestionAvailableWrong() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        boolean result = questionService.updateQuestionAvailable(list);
        System.out.println(result + ".............");
    }

    @Test
    public void testEditQuestionWithException() {
        Question question = new Question();
        question.setquestionNumber("Q1890tgi8hujeiwahtu89ewatyewa89ty");
        question.setQuestionName("tqetqe");
        question.setChoiceA("353q5rq35");
        question.setChoiceB("353q5rq35");
        question.setChoiceC("353q5rq35");
        question.setChoiceD("353q5rq35");
        question.setCorrectChoice("A");
        try {
            questionService.editQuestion(question);
        } catch (ParamterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testEditQuestionCorrect() {
        Question question = new Question();
        question.setquestionNumber("Q1890");
        question.setQuestionName("tqetqe");
        question.setChoiceA("353q5rq35");
        question.setChoiceB("353q5rq35");
        question.setChoiceC("353q5rq35");
        question.setChoiceD("353q5rq35");
        question.setCorrectChoice("A");
        try {
            int result = questionService.editQuestion(question);
            System.out.println(result + ".............");
        } catch (ParamterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void testqueryQuestionByIdWrong() {
        Question question = questionService.queryQuestionById(1);
        System.out.println(question);
    }

    @Test
    public void testqueryQuestionByIdCorrcet() {
        Question question = questionService.queryQuestionById(137);
        System.out.println(question);
    }

    @Test
    public void testCreateQuestionWithException() {
        Question question = new Question();
        question.setquestionNumber("Q1890tgi8hujeiwahtu89ewatyewa89ty");
        question.setQuestionName("tqetqe");
        question.setChoiceA("353q5rq35");
        question.setChoiceB("353q5rq35");
        question.setChoiceC("353q5rq35");
        question.setChoiceD("353q5rq35");
        question.setCorrectChoice("A");
        try {
            int result = questionService.createQuestion(question);
            System.out.println(result + ".............");
        } catch (ParamterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateQuestionCorrect() {
        Question question = new Question();
        question.setquestionNumber("Q1890");
        question.setQuestionName("tqetqe");
        question.setChoiceA("353q5rq35");
        question.setChoiceB("353q5rq35");
        question.setChoiceC("353q5rq35");
        question.setChoiceD("353q5rq35");
        question.setCorrectChoice("A");
        try {
            int result = questionService.createQuestion(question);
            System.out.println(result + ".............");
        } catch (ParamterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testGetQuestionQtyByKeyWord() {
        int result = questionService.getQuestionQtyByKeyword("e");
        System.out.println(result + ".............");
    }

    @Test
    public void testFindQuestionsPagingAll() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(10);
        pagination.setTotalCount(questionService.getQuestionQtyByKeyword("e"));
        pagination.setKeyword("e");
        pagination.setOrder("DESC");
        List<Question> list = questionService.findQuestionsPaging(pagination);
        System.out.println(list.size() + "..................");
    }

    @Test
    public void testFindQuestionsPagingTwo() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(1000);
        pagination.setTotalCount(questionService.getQuestionQtyByKeyword(""));
        pagination.setOrder("ASC");
        pagination.setKeyword("");
        List<Question> list = questionService.findQuestionsPaging(pagination);
        System.out.println(list.size() + "..................");
    }

    @Test
    public void testFindQuestionsPagingThree() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(10);
        pagination.setTotalCount(questionService.getQuestionQtyByKeyword(""));
        pagination.setOrder("DESC");
        pagination.setKeyword("");
        List<Question> list = questionService.findQuestionsPaging(pagination);
        System.out.println(list.size() + "..................");
    }

    @Test
    public void testFindQuestionsPagingPageSize() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(1000);
        pagination.setTotalCount(questionService.getQuestionQtyByKeyword("e"));
        pagination.setKeyword("e");
        pagination.setOrder("ASC");
        List<Question> list = questionService.findQuestionsPaging(pagination);
        System.out.println(list.size() + "..................");
    }
    @Test
    public void testFindQuestionsPagingWrong() {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(1);
        pagination.setPageSize(10);
        pagination.setKeyword("eji0eajtueiata8i");
        pagination.setOrder("ASC");
        List<Question> list = questionService.findQuestionsPaging(pagination);
        System.out.println(list + "..................");
    }

}
