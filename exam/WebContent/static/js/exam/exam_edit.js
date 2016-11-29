//totalCount
function countTotalScore() {
    var quantity = $.trim($("#question_quantity").val());
    var points = $.trim($("#question_points").val());
    if (isPositiveNumber(quantity) && isPositiveNumeric(points)) {
        var totalScore = parseFloat(quantity) * parseFloat(points);
        $("#total_score").val(totalScore);
    } else {
        $("#total_score").val("");
    }
}
function hideWarning() {
    $("#exam_disablie_screen").hide();
    $("#exam_edit_confirm").hide();
}
$(function() {
    //count totalScore
    $("#question_quantity").on("blur", countTotalScore);
    $("#question_points").on("blur", countTotalScore);

    //warning
    $("#close_img").on("click", hideWarning);
    $("#ok_btn").on("click", hideWarning);

    //validate
    $("#save").on("click", function() {
        var nameFlag = checkExamName();
        var descriptionFlag = checkExamDescription();
        var timeFlag = checkUpdateExamTime();
        var durationFlag = checkDuration();
        var quantityFlag = checkQuestionQuantity();
        var pointsFlag = checkQuestionPoints();
        var scoreFlag = checkTotalScore();
        var criteriaFlag = checkPassCriteria();
        var submitFlag = nameFlag && descriptionFlag && timeFlag && durationFlag && quantityFlag && pointsFlag && scoreFlag && criteriaFlag;
        if (submitFlag === true) {
            //Ajax
            var url = "/exam/page/question/queryQuestionCount";
            $.post(url, null, function(data) {
                var totalCount = parseInt(data);
                var quantity =  parseInt($.trim($("#question_quantity").val()));
                if (quantity <= totalCount) {
                    $("#edit_form").submit();
                } else {
                    $("#exam_disablie_screen").show();
                    $("#exam_edit_confirm").show();
                }
            }, "json");
        }
    });

    $("#cancel").on("click", function() {
        window.location.href = "/exam/page/exam/listExam";
    });
    

    
});