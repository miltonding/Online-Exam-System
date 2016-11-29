function goBack() {
    location.href = "/exam/page/question/questionList";
}
$(function() {
    $("#question_management").click(function() {
        window.location.href = "/exam/page/question/questionList";
    });
    $("#exam_management").click(function() {
        window.location.href = "/exam/page/exam/listExam";
    });
});