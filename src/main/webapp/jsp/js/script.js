function checkForm() {

    var el,
            elName,
            value,
            type;

    var errorList = [];

    var errorText = {
        1: resource.checkform_error_title,
        2: resource.checkform_error_date,
        3: resource.checkform_error_brief,
        4: resource.checkform_error_content,
        5: resource.checkform_error_date_format,
        6: resource.checkform_error_brief_overflow,
        7: resource.checkform_error_content_overflow,
        8: resource.checkform_error_date_day,
        9: resource.checkform_error_date_month,
        10: resource.checkform_error_date_year
    };

    for (var i = 0; i < document.newsForm.elements.length; i++) {
        el = document.newsForm.elements[i];
        elName = el.nodeName.toLowerCase();
        value = el.value;
        if (elName == "input") {

            type = el.type.toLowerCase();

            switch (type) {
                case "text" :
                    if (el.name == "newsMessage.title" && value == "")
                        errorList.push(1);
                    if (el.name == "date" && value == "")
                        errorList.push(2);
                    if (el.name == "date" && !validDate(value))
                        errorList.push(5);
                    else {
                        if (el.name == "date" && !checkDateYear(value))
                            errorList.push(10);
                        if (el.name == "date" && !checkDateMonth(value))
                            errorList.push(9);
                        if (el.name == "date" && !checkDateDay(value))
                            errorList.push(8);

                    }
                    if (el.name == "date" && checkDateMonth(value) && checkDateDay(value) && checkDateYear(value))
                        prcessDate(value);
                    if (el.name == "newsMessage.title" && !titleCheckLength(el))
                        errorList.push(5);
                    break;
                default :
                    break;
            }
        } else if (elName == "textarea") {
            if (el.name == "newsMessage.brief" && value == "")
                errorList.push(3);
            if (el.name == "newsMessage.content" && value == "")
                errorList.push(4);
            if (el.name == "newsMessage.brief" && !briefCheckLength(el))
                errorList.push(6);
            if (el.name == "newsMessage.content" && !contentCheckLength(el))
                errorList.push(7);

        } else {

        }
    }

    if (!errorList.length)
        return true;

    var errorMsg = resource.checkform_error + "\n\n";
    for (i = 0; i < errorList.length; i++) {
        errorMsg += errorText[errorList[i]] + "\n";
    }
    alert(errorMsg);
    return false;
}
function prcessDate(date) {
    if (resource.lang == 'en') {
        var arrD = date.split("/");
        arrD[0] -= 1;
        var d = new Date(arrD[2], arrD[0], arrD[1]);

        if ((d.getFullYear() == arrD[2]) && (d.getMonth() == arrD[0]) && (d.getDate() == arrD[1])) {

            var massDate = date.split("/");
            month = massDate[0];
            day = massDate[1];
            year = massDate[2];

            el = document.getElementsByName("newsMessage.date")[0];
            el.value = month + '/' + day + '/' + year;
        }
    }
    if (resource.lang == 'ru') {
        var arrD = date.split("/");
        arrD[1] -= 1;
        var d = new Date(arrD[2], arrD[1], arrD[0]);

        if ((d.getFullYear() == arrD[2]) && (d.getMonth() == arrD[1]) && (d.getDate() == arrD[0])) {

            var massDate = date.split("/");
            day = massDate[0];
            month = massDate[1];
            year = massDate[2];

            el = document.getElementsByName("newsMessage.date")[0];
            el.value = month + '/' + day + '/' + year;
        }
    }
}
function validDate(date)
{
    if (!/^\d\d\/\d\d\/\d{4}$/.test(date)) {
        return false;
    }
    return true;
}
function checkDateDay(date) {
    var d;
    var monthes = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var massDate = date.split("/");

    if (resource.lang == 'en') {
        var arrD = date.split("/");
        arrD[0] -= 1;

        month = massDate[0];
        day = massDate[1];
        year = massDate[2];
    }
    if (resource.lang == 'ru') {
        var arrD = date.split("/");
        arrD[1] -= 1;
        day = massDate[0];
        month = massDate[1];
        year = massDate[2];
    }

    if (month < 1 || month > 12) {
        if (day < 1 || day > 30) {
            return false;
        }
        return true;
    }

    var length = monthes[month - 1];
    if (month == 2 && (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))) {
        length = 29;
    }
    if (!(day > 0 && day <= length)) {
        return false;
    }
    return true;
}
function checkDateMonth(date) {
    var d;
    var month;
    var monthes = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var massDate = date.split("/");

    if (resource.lang == 'en') {
        var arrD = date.split("/");
        arrD[0] -= 1;
        d = new Date(arrD[2], arrD[0], arrD[1]);
        month = massDate[0];
    }
    if (resource.lang == 'ru') {
        var arrD = date.split("/");
        arrD[1] -= 1;
        var d = new Date(arrD[2], arrD[1], arrD[0]);
        month = massDate[1];
    }

    if (month < 1 || month > 12) {
        return false;
    }
    var length = monthes[month - 1];

    return true;
}

function checkDateYear(date) {
    var d;
    var year;
    var monthes = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var massDate = date.split("/");

    if (resource.lang == 'en') {
        var arrD = date.split("/");
        arrD[0] -= 1;
        year = massDate[2];
    }
    if (resource.lang == 'ru') {
        var arrD = date.split("/");
        arrD[1] -= 1;
        year = massDate[2];
    }

    if (year < 1900 || year > 2050) {
        return false;
    }

    return true;
}

function askDelete()
{
    return confirm(resource.askDelete);
}
function askAllDelete()
{
    var elLength = document.newsForm.elements.length;
    var number = 0;
    for (i = 0; i < elLength; i++)
    {
        var type = newsForm.elements[i].type;
        if (type == "checkbox" && newsForm.elements[i].checked) {
            number++;
        }
    }
    if (number == 1) {
        return confirm(resource.askDeleteAllOneNews);
    }
    if (number > 1) {
        return confirm(resource.askDeleteAllSomeNews);
    }
    alert(resource.askDeleteAll_error);
    return false;
}
function titleCheckLength(text) {
    var MaxLenght = 100;
    if (text.value.length > MaxLenght) {
        return false;
    }
    return true;
}
function briefCheckLength(textArea) {
    var MaxLenght = 500;
    if (textArea.value.length > MaxLenght) {
        return false;
    }
    return true;
}
function contentCheckLength(textArea) {
    var MaxLenght = 2000;
    if (textArea.value.length > MaxLenght) {
        return false;
    }
    return true;
}
window.onload = function checkDeleteAllSubmit() {
    var current_page = document.getElementsByName("page")[0];
    if (current_page.value == "page_edit") {
        date_el = document.getElementsByName("date")[0];
        format_date_el = document.getElementsByName("newsMessage.date")[0];

        date_el.value = format_date_el.value;
    }

    if (current_page.value == "page_list") {
        if (document.getElementsByClassName('news_head')[0]) {
        }
        else {
            var obj = document.getElementsByName('method')[0];
            obj.style.display = "none";
        }
    }
}
