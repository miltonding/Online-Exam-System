 //validate
function checkQuestionNumber() {
    var questionNumberFlag = isNotEmpty($("#questionNumber").val());
    if (questionNumberFlag === true && $("#questionNumber").val().length <= 10) {
        $("#tip_question_number").html("");
            return true;
    } else if ($("#questionNumber").val().length > 10) {
        $("#tip_question_number").html("ID length is over 10");
        return false;
    } else {
        $("#tip_question_number").html("Question ID is required");
        return false;
    }
}

function checkQuestionName() {
    var questionNameFlag = isNotEmpty($("#questionName").val());
    if (questionNameFlag === true && $("#questionName").val().length <= 200) {
        $("#tip_question_name").html("");
        return true;
    } else if ($("#questionName").val().length > 200) {
        $("#tip_question_name").html("Question length is over 200");
        return false;     
    }else {
        $("#tip_question_name").html("Question is required");
         return false;        
    }
}

function checkChoiceA() {
    var choiceAFlag = isNotEmpty($("#choiceA").val());
    if (choiceAFlag === true && $("#choiceA").val().length <= 200) {
        $("#tip_choice_a").html("");
        return true;
    } else if ($("#choiceA").val().length > 200) {
        $("#tip_choice_a").html("choiceA length is over 200");
        return false; 
    }else {
        $("#tip_choice_a").html("choiceA is required");
        return false; 
    }
}

function checkChoiceB() {
    var choiceBFlag = isNotEmpty($("#choiceB").val());
    if (choiceBFlag === true && $("#choiceB").val().length <= 200) {
        $("#tip_choice_b").html("");
        return true;
    } else if ($("#choiceB").val().length > 200) {
        $("#tip_choice_b").html("choiceB length is over 200");
        return false; 
    } else {
       $("#tip_choice_b").html("choiceB is required");
       return false;
    }
}

function checkChoiceC() {
     var choiceCFlag = isNotEmpty($("#choiceC").val());
     if (choiceCFlag === true && $("#choiceC").val().length <= 200) {
         $("#tip_choice_c").html("");
         return true;
     } else if ($("#choiceC").val().length > 200) {
         $("#tip_choice_c").html("choiceC length is over 200");
         return false; 
     } else {
         $("#tip_choice_c").html("choiceC is required");
         return false;
     }
}

function checkChoiceD() {
    var choiceDFlag = isNotEmpty($("#choiceD").val());
    if (choiceDFlag === true && $("#choiceD").val().length <= 200) {
        $("#tip_choice_d").html("");
        return true;
    } else if ($("#choiceD").val().length > 200) {
        $("#tip_choice_d").html("choiceD length is over 200");
        return false; 
    } else {
        $("#tip_choice_d").html("choiceD is required");
        return false;
    }
}

function checkCorrectChoice() {
    var correctChoiceFlag = isNotEmpty($("#correctChoice").val());
    if (correctChoiceFlag === true) {
        $("#tip_correct_choice").html("");
        return true;
    } else {
        $("#tip_correct_choice").html("Answer is required");
        return false;
    }
}