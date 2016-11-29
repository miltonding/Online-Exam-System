//update correct choice
$(function() {
    $(".choice li div").click(function() {
        $(".choice li div").attr("class", "unseleted_radio");
        $.each($(".choice li div"),function(i,e) {
            $(this).nextAll().eq(1).attr("class", "unseleted_input");
        });
        $(this).attr("class", "seleted_radio");
        var correctChoice = $(this).next().html();
        $("#correctChoice").val(correctChoice); 
        $(this).nextAll().eq(1).attr("class", "seleted_input"); 
    });

    $("#edit").click(function() {
        var questionNumberFlag = checkQuestionNumber();
        var questionNameFlag = checkQuestionName();
        var choiceAFlag = checkChoiceA();
        var choiceBFlag = checkChoiceB();
        var choiceCFlag = checkChoiceC();
        var choiceDFlag = checkChoiceD();
        var correctChoiceFlag = checkCorrectChoice();
        var canSubmit = questionNumberFlag && questionNameFlag && choiceAFlag && choiceBFlag && choiceCFlag && choiceDFlag && correctChoiceFlag;
        //alert(canSubmit);
        if ( canSubmit === true) {
            $("#editForm").submit();
        }
    });

    $("#cancel").click(function() {
        location.href = "/exam/page/question/questionList";
    });
    $("#question_management").click(function() {
        window.location.href = "/exam/page/question/questionList";
    });
    $("#exam_management").click(function() {
        window.location.href = "/exam/page/exam/listExam";
    });
});