//selete correct choice
$(function() {
    var questionNumberFlag = false;
    $(".choice li div").click(function() {
    $(".choice li div").attr("class", "unseleted_radio");
    $(this).attr("class", "seleted_radio");
    var correctChoice = $(this).next().html();
    $("#correctChoice").val(correctChoice); 
});

    //Ajax
    $("#questionNumber").blur(function() {
       if (checkQuestionNumber() === true) {
           var url = "/exam/page/question/checkUserName";
           $.post(url, {"questionNumber": $(this).val()}, function callback(data){
               if (data === false) {
                   $("#tip_question_number").html("The number has existed");
                   questionNumberFlag = false;
               } else {
                   $("#tip_question_number").html("");
                   questionNumberFlag = true;
               }
           }, "json");
       }
    });

    $("#create").click(function() {
        checkQuestionNumber();
        var questionNameFlag = checkQuestionName();
        var choiceAFlag = checkChoiceA();
        var choiceBFlag = checkChoiceB();
        var choiceCFlag = checkChoiceC();
        var choiceDFlag = checkChoiceD();
        var correctChoiceFlag = checkCorrectChoice();
        var canSubmit = questionNumberFlag && questionNameFlag && choiceAFlag && choiceBFlag && choiceCFlag && choiceDFlag && correctChoiceFlag;
        if ( canSubmit === true) {
            $("#creatForm").submit();
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