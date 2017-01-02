/**
 * Created by jk on 2016/12/10.
 */
/*全局变量*/
var flag = [true,true,true,true,true,true,true,true,true];
/*加载*/
$(function () {
    tog();
    changeIco();
})
/*事件函数*/
function tog() {
    $(".headList").click(function () {
        $(this).parent().next().toggle();
    })
}
function changeIco() {
    $(".list1").click(function () {
        if(flag[0] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[0] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[0] = true;
        }
    })
    $(".list2").click(function () {
        if(flag[1] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[1] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[1] = true;
        }
    })
    $(".list3").click(function () {
        if(flag[2] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[2] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[2] = true;
        }
    })
    $(".list4").click(function () {
        if(flag[3] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[3] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[3] = true;
        }
    })
    $(".list5").click(function () {
        if(flag[4] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[4] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[4] = true;
        }
    })
    $(".list6").click(function () {
        if(flag[5] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[5] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[5] = true;
        }
    })
    $(".list7").click(function () {
        if(flag[6] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[6] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[6] = true;
        }
    })
    $(".list8").click(function () {
        if(flag[7] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[7] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[7] = true;
        }
    })
    $(".list9").click(function () {
        if(flag[8] == true) {
            $(this).children(".rightIco").html("&#xe612;");
            flag[8] = false;
        }
        else {
            $(this).children(".rightIco").html("&#xe613;");
            flag[8] = true;
        }
    })
}