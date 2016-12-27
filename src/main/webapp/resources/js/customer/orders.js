/**
 * Created by Lenovo on 2016/12/24.
 */
$(document).ready(function (){
    $('#cartAddress').bind('submit',function () {
    var data=$('#cartAddress').serialize();
    $.ajax({
        url:'/order/saveAddress',
        type:'POST',
        data:data,
        success:function (msg) {
            if('true'==msg){
                window.location.reload();
            }else {
                //添加失败
            }
        }
    })
    return false;
})});

function deleteAddress(param) {
    $.ajax({
        url: '/order/deleteAddress',
        type: 'GET',
        data:{
          addressId:param
        },
        success: function (msg) {
            if ('true' == msg) {
                window.location.reload();
            } else {
                //添加失败
            }
        }
    })
    return false;

}
//
// var AddressOption={
//         name:'',
//         province:'',
//         country:'',
//         others:'',
//         zip:'',
//         phoneNum:''
// };