 //validate
function checkExamName() {
    var exam_name_length = $.trim($(".exam_name").val()).length;
    if (exam_name_length == 0) {
        $("#tip_exam_name").html("exam name is required");
        return false;
    } else if (exam_name_length > 20) {
        $("#tip_exam_name").html("length is over 20");
        return false;
    } else {
        $("#tip_exam_name").html("");
        return true;
    }
}

function checkExamDescription() {
    var textarea_description_length = $.trim($(".textarea_description").val()).length;
    if (textarea_description_length == 0) {
        $("#tip_exam_description").html("description is required");
        return false;
    } else if (textarea_description_length > 100) {
        $("#tip_exam_description").html("length is over 100");
        return false;
    } else {
        $("#tip_exam_description").html("");
        return true;
    }
}

//validate create Exam
function checkCreateExamTime() {
    var text_date = $.trim($(".text_date").val());
    if (isNotEmpty(text_date)) {
        var times = text_date.split("T");
        text_date = times[0] + " " + times[1] + ":00";
        var now = new Date();
        var date = new Date(text_date);
        if (date.getTime() > now.getTime()) {
            $("#tip_exam_time").html("");
            return true;
        } else {
            $("#tip_exam_time").html("effective time is invalid");
            return false;
        }
    } else {
        $("#tip_exam_time").html("effective time is required");
        return false;
    }
}

//validate create Exam
function checkUpdateExamTime() {
    var text_date = $.trim($(".text_date").val());
    if (isNotEmpty(text_date)) {
        $("#tip_exam_time").html("");
        return true;
    } else {
        $("#tip_exam_time").html("effective time is required");
        return false;
    }
}

function checkDuration() {
    var duration = $.trim($(".select_duration").val());
    if (isNotEmpty(duration)) {
        $("#tip_exam_duration").html("");
        return true;
    } else {
        $("#tip_exam_duration").html("duration is required");
        return false;
    }
}

function checkQuestionQuantity() {
    var question_quantity_length = $.trim($("#question_quantity").val()).length;
    if (question_quantity_length > 0 && question_quantity_length <= 10 && isPositiveNumber($.trim($("#question_quantity").val()))) {
        $("#tip_exam_quantity").attr("class", "waring_img_hide");
        return true;
    } else {
        $("#tip_exam_quantity").attr("class", "waring_img_show");
        return false;
    }
}

function checkQuestionPoints() {
    var question_points_length = $.trim($("#question_points").val()).length;
    if (question_points_length > 0 && question_points_length <= 10 && isPositiveNumeric($("#question_points").val())) {
        $("#tip_exam_points").attr("class", "waring_img_hide");
        return true;
    } else {
        $("#tip_exam_points").attr("class", "waring_img_show");
        return false;
    }
}

function checkTotalScore() {
    var total_score_length = $.trim($("#total_score").val()).length;
    if (total_score_length > 0 && total_score_length <= 6 && isPositiveNumeric($("#total_score").val())) {
        $("#tip_total_score").attr("class", "waring_img_hide");
        return true;
    } else {
        $("#tip_total_score").attr("class", "waring_img_show");
        return false;
    }
}

function checkPassCriteria() {
    var pass_criteria_length = $.trim($("#pass_criteria").val()).length;
    if (pass_criteria_length > 0 && pass_criteria_length <= 6 && isPositiveNumeric($("#pass_criteria").val()) && checkTotalScore()) {
            var total_score = parseFloat($.trim($("#total_score").val()));
            var pass_criteria = parseFloat($.trim($("#pass_criteria").val()));
            if (pass_criteria < total_score) {
                $("#tip_exam_criteria").attr("class", "waring_img_hide");
                 return true;
            } else {
                 $("#tip_exam_criteria").attr("class", "waring_img_show");
                 return false;
            }
    } else {
        $("#tip_exam_criteria").attr("class", "waring_img_show");
         return false;
    }
}

