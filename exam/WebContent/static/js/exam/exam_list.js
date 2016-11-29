//check date
function checkSearchDate(startDate, endDate) {
    var beforeDate = new Date(startDate + " 00:00:00");
    var afterDate = new Date(endDate + " 00:00:00");
    if (afterDate.getTime() > beforeDate.getTime()) {
        return true;
    } else {
        return false;
    }
}
$(function() {
    //Order
    $("#id_order").click(function() {
        var clazz = $(this).attr("class");
        if (clazz === "id_up") {
            $(this).attr("class", "id_down");
            $("#page_order").val("idDESC");
        } else {
            $(this).attr("class", "id_up");
            $("#page_order").val("idASC");
        }
        $("#currentPage").val("1");
        $("#paging_form").submit();  
    });

    $("#name_order").click(function() {
        var clazz = $(this).attr("class");
        if (clazz === "name_up") {
            $(this).attr("class", "name_down");
            $("#page_order").val("nameDESC");
        } else {
            $(this).attr("class", "name_up");
            $("#page_order").val("nameASC");
        }
        $("#currentPage").val("1");
        $("#paging_form").submit();
    });

    $("#time_order").click(function() {
        var clazz = $(this).attr("class");
        if (clazz === "time_up") {
            $(this).attr("class", "time_down");
            $("#page_order").val("timeDESC");
        } else {
            $(this).attr("class", "time_up");
            $("#page_order").val("timeASC");
        }
        $("#currentPage").val("1");
        $("#paging_form").submit();  
    });

    //Date
    $(".date_search").click(function() {
        var submitFlag = true;
        var startDate = $(".start_date").val();
        var endDate = $(".end_date").val();
            $("#startDate").val(startDate);
            $("#endDate").val(endDate);
        //submit form
        if (startDate.length != 0 && endDate.length != 0) {
            submitFlag = checkSearchDate(startDate, endDate);
        }
        if (submitFlag) {
            $("#currentPage").val("1");
            $("#paging_form").submit();
        } else {
            $(".warning_message").html("please choose correct date");
            $(".disablie_screen").show();
            $(".warning_input").show();
        }
    });

    //change currentPage
    $("#previous_page").click(function() {
        var currentPage = parseInt($("#currentPage").val()) - 1;
        $("#currentPage").val(currentPage);
        $("#paging_form").submit();
    });

    $("#next_page").click(function() {
        var currentPage = parseInt($("#currentPage").val()) + 1;
        $("#currentPage").val(currentPage);
            $("#paging_form").submit();
        });

    $("#goal_page").on("blur", function() {
        var goalPage = $("#goal_page").val();
        if (goalPage.length != 0 && !isPositiveNumber(goalPage)) {
            $(".warning_message").html("please input correct number");
            $(".disablie_screen").show();
            $(".warning_input").show();
        }
    });

    $(".page_count").click(function() {
        var goalPage = $(this).html();
        $("#currentPage").val(goalPage);
        $("#paging_form").submit(); 
    });

    $("#go_btn").click(function() {
        var goalPage = $("#goal_page").val();
        if (!isPositiveNumber(goalPage)) {
            $(".warning_message").html("please input correct number");
            $(".disablie_screen").show();
            $(".warning_input").show();
        } else {
            $("#currentPage").val(goalPage);
            $("#paging_form").submit();
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
        $("#pageSize").val(pageSize);
        $("#currentPage").val("1");
        $("#paging_form").submit(); 
        });

    //keyword
    $(".serch_icon").click(function() {
        var keyword = $("#search_box").val();
        $("#keyword").val(keyword);
        $("#currentPage").val("1");
        $("#paging_form").submit();  
    });
    
    $("#search_box").on("keydown", function(e) {
         if (e.keyCode === 13) {
             var keyword = $("#search_box").val();
             $("#keyword").val(keyword);
             $("#currentPage").val("1");
             $("#paging_form").submit();  
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
            $(".warning_message").html("please choose one to delete");
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

    $("#check_all").click(function() {
        $(this).toggleClass("list_unSeleted list_seleted");
        if ($(this).hasClass("list_seleted")) {
            $(".delete_checkbox").attr("class", "delete_seleted delete_checkbox");
            $(".hide_checkbox").prop("checked", true);
        } else {
            $(".delete_checkbox").attr("class", "delete_unSeleted delete_checkbox");
            $(".hide_checkbox").prop("checked", false);
        }
    });

    $(".delete_checkbox").click(function() {
        $(this).toggleClass("delete_seleted delete_unSeleted");
        var isAllChecked = true;
        if ($(this).hasClass("delete_seleted")) {
            $(this).next().prop("checked", true);
            $.each($(".hide_checkbox"), function() {
                if ($(this).prop("checked") === false) {
                    isAllChecked = false;
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
    
    //delete
    $(".confirm_btn").on("click", function() {
        $("#delete_form").submit();
    });
    
    //href
    $("#menu_question").click(function() {
        window.location.href = "/exam/page/question/questionList";
    });

    $("#menu_exam").click(function() {
        window.location.href = "/exam/page/exam/listExam";
    });
    $("#exam_list").click(function() {
        window.location.href = "/exam/page/exam/listExam";
    });
    $("#exam_create").click(function() {
        window.location.href = "/exam/page/exam/showCreateExam";
    });
 });