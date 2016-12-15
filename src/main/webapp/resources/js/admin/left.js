/**
 * Created by jk on 2016/12/10.
 */
$(function () {
    
    /*hover事件*/
    $(".leftList>li").mouseenter(function () {
        $(this).children().css("color","white");
    }).mouseleave(function () {
        $(this).children().css("color","black")
    })
    
    /*click事件*/
   $(".leftList>li").click(function () {

       $(".clicked").removeClass("clicked");
       $(this).addClass("clicked");
   })
})