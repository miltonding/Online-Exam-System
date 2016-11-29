function numberValidate(para) {
    var reg = /^[-]{0,1}[0-9]{1,}$/;
    return reg.test(para);
}

function isNotEmpty(param) {
    if (param == null || param.length == 0) {
        return false;
    } else {
        return true;
    }
}

function isPositiveNumber(para) {
    var reg = /^[-]{0,1}[0-9]{1,}$/;
    if (reg.test($.trim(para)) && $.trim(para) > 0) {
        return true;
    }else {
        return false;
    }
}

function isPositiveNumeric(para) {
    if ($.isNumeric($.trim(para)) && $.trim(para) > 0) {
        return true;
    }else {
        return false;
    }
}