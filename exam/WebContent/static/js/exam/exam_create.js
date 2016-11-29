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
$(function() {
    //totalCount
    $("#question_quantity").on("blur", countTotalScore);
    $("#question_points").on("blur", countTotalScore);

    $("#create").click(function() {
        var nameFlag = checkExamName();
        var descriptionFlag = checkExamDescription();
        var timeFlag = checkCreateExamTime();
        var durationFlag = checkDuration();
        var quantityFlag = checkQuestionQuantity();
        var pointsFlag = checkQuestionPoints();
        var scoreFlag = checkTotalScore();
        var criteriaFlag = checkPassCriteria();
        var submitFlag = nameFlag && descriptionFlag && timeFlag && durationFlag && quantityFlag && pointsFlag && scoreFlag && criteriaFlag;
        if (submitFlag) {
            $("#create_form").submit();
        }
    });

    $("#cancel").click(function() {
        location.href = '/exam/page/question/questionList';
    });

    $("#close_img").click(function() {
        $("#exam_delete_confirm").hide();
        $("#exam_disablie_screen").hide();
    });

    $("#cancel_btn").click(function() {
        $("#exam_delete_confirm").hide();
        $("#exam_disablie_screen").hide();
    });

    $("#save_btn").click(function() {
        $("#create_form").attr("action", "/exam/page/exam/draftExam");
        $("#create_form").submit();
    });
});