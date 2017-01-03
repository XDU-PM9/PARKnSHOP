/**
 * Created by wei on 16-12-31.
 */

/**
 * 全选
 */
function selectAll() {
    $(':checkbox').each(function () {
        //如果没有被禁用
            if (!$(this).is(':disabled')) {
                //选中
                $(this).attr("checked","checked");
            }
        }
    )
}

/**
 * 当数量改变时，发起ajax请求，同时判断是否超出库存，以决定是否禁用checkbox
 * @param cartId
 * @param amount
 * @param goodsAmount
 */
function amountChange(cartId, amount, goodsAmount) {
    //更改成功后改变总价
    $('#xiaoji'+cartId).html($('#danjia'+cartId).html()*amount);
    $.ajax({
        url: "/changeAmount",
        type: 'POST',
        data: {
            cartId: cartId,
            amount: amount
        },
        success: function (msg) {
            if ('success' == msg) {
                if (amount > goodsAmount) {
                    //取消选中
                    document.getElementById(cartId).checked=false;
                    document.getElementById(cartId).setAttribute("disabled", "disabled");
                    document.getElementById(cartId).style.cursor = "not-allowed";
                    // document.getElementById(cartId).removeAttribute("checked");
                } else {
                    document.getElementById(cartId).removeAttribute("disabled");
                    document.getElementById(cartId).style.cursor = "auto";
                    //更改成功后改变总价
                    // $('#xiaoji'+cartId).html($('#danjia'+cartId).html()*amount);
                }
            }
        }
    })
}

/**
 * 提交购物车
 */
function submitCart() {
    var data = "";
    $(':checkbox').each(function () {
            if ($(this).is(':checked')) {
                data += this.value + ",";
            }
        }
    )
    if ("" == data) {
        layer.alert("Please select at least one item");
        return;
    }
    $.ajax({
        url: "/order/cartSubmit",
        data: {
            ch: data
        },
        type: 'POST',
        success: function (msg) {
            if ('error' != msg && msg.indexOf("/") != -1) {
                window.location.href = msg;
            }
            else {
                layer.alert(msg);
                // alert("Generator order fail,please try again");
            }
        }
    })
}
