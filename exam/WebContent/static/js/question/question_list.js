function keywordSearch() {
    var keyWord = $("#search_box").val();
    if (keyWord.length != 0){
        $("#paging_keyWord").val(keyWord);
    } else {
        $("#paging_keyWord").val("");
    }
    $("#paging_currentPage").val("1");
    $("#paging_form").submit();
}

$(function() {
    //change currentPage
    $("#previous_page").click(function() {
        var currentPage = parseInt($("#paging_currentPage").val()) - 1;
        $("#paging_currentPage").val(currentPage);
        $("#paging_form").submit();
    });
    $("#next_page").click(function() {
        var currentPage = parseInt($("#paging_currentPage").val()) + 1;
        $("#paging_currentPage").val(currentPage);
        $("#paging_form").submit();
    });

    $("#goal_page").blur(function() {
        var goalPage = $(this).val();
        if (numberValidate(goalPage) === false && goalPage.length > 0) {
            $(".disablie_screen").show();
            $(".warning_input").show();
        }
    });
    $(".page_count").click(function() {
        var goalPage = $(this).html();
        $("#paging_currentPage").val(goalPage);
        $("#paging_form").submit();  
    });

    $("#go_btn").click(function() {
        var goalPage = $("#goal_page").val();
        if (numberValidate(goalPage) === true && goalPage.length > 0) {
            $("#paging_currentPage").val(goalPage);
            $("#paging_form").submit();  
        } else {
            $(".disablie_screen").show();
            $(".warning_input").show();
        }
    });

    //display warning
    $("#reinput").click(function() {
        $(".warning_input").hide();
        $(".disablie_screen").hide();
    });

    $("#rechoose").click(function() {
        $(".warning_delete").hide();
        $(".disablie_screen").hide();
    });

    //change pageSize
    $("#select_size").change(function() {
        var pageSize = $(this).val();
        $("#paging_pageSize").val(pageSize);
        $("#paging_currentPage").val("1");
        $("#paging_form").submit();  
    });

    //change order
    $("#up_down").click(function() {
        var clazz = $(this).attr("class");
        if (clazz === "up") {
            $(this).attr("class", "down");
            $("#paging_order").val("DESC");
            $("#paging_form").submit();  
        } else {
            $(this).attr("class", "up");
            $("#paging_order").val("ASC");
            $("#paging_form").submit();  
        }
    });

    //keyword
    $("#serch_icon").on("click", keywordSearch);
    $("#search_box").on("keydown", function(e) {
        if (e.keyCode === 13) {
            keywordSearch();
        }
    });

    //delete question
    $("#del_btn").click(function() {
        var submitFlag = false;
        $.each($("#delete_form :checkbox"), function(i, e) {
            if ($(this).prop("checked") == true) {
                submitFlag = true;
            }
        });
        //submit deleteForm
        if (submitFlag) {
        $(".delete_confirm").show();
            $(".disablie_screen").show();
        } else {
            $(".warning_delete").show();
            $(".disablie_screen").show();
        }
    });

    $("#close_img").click(function() {
        $(".disablie_screen").hide();
        $(".delete_confirm").hide();
    });

    $(".cancel_btn").click(function() {
        $(".disablie_screen").hide();
        $(".delete_confirm").hide();
    });

    $(".confirm_btn").click(function() {
        //submit deleteForm
        $("#delete_form").submit();
    });

    //select all
    $("#check_all").click(function() {
        $(this).toggleClass("list_seleted list_unSeleted");
        if ($(this).hasClass("list_seleted")) {
            $("#delete_form :checkbox").prop("checked", true);
          $(".custom_checkbox").attr("class", "delete_seleted custom_checkbox");
        } else {
            $("#delete_form :checkbox").prop("checked", false);
            $(".custom_checkbox").attr("class", "delete_unSeleted custom_checkbox");
        }
    });

    $(".custom_checkbox").click(function() {
        $(this).toggleClass("delete_seleted delete_unSeleted");
        var isAllChecked = true;
        if ($(this).hasClass("delete_seleted")) {
            $(this).next().prop("checked", true);
            $.each($("#delete_form :checkbox"), function(i, e) {
                if ($(this).prop("checked") === false) {
                    isAllChecked = false;
                    return;
                }
           });
        } else {
            $(this).next().prop("checked", false);
            isAllChecked = false;
        }
        if (isAllChecked) {
            $("#check_all").attr("class", "list_seleted");
        } else {
            $("#check_all").attr("class", "list_unSeleted");
        }
    });

    //href
    $("#question_list").click(function() {
        window.location.href = "/exam/page/question/questionList";
    });
    $("#question_create").click(function() {
        window.location.href = "/exam/page/question/showCreateQuestion";
    });
    $("#question_management").click(function() {
        window.location.href = "/exam/page/question/questionList";
    });
    $("#exam_management").click(function() {
        window.location.href = "/exam/page/exam/listExam";
    });
 });