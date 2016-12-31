/**
 * Created by wei on 16-12-31.
 */

function amountChange(cartId, amount, goodsAmount) {
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
                    document.getElementById(cartId).setAttribute("disabled", "disabled");
                    document.getElementById(cartId).style.cursor="not-allowed";
                    document.getElementById(cartId).removeAttribute("checked");
                } else {
                    document.getElementById(cartId).removeAttribute("disabled");
                    document.getElementById(cartId).style.cursor="auto";
                }
            }
        }
    })
}

// $(document).ready(function () {
//     $('#cartSubmitForm').bind('submit',
function submitCart() {
    var data = "";
    $(':checkbox').each(function () {
            if ($(this).is(':checked')) {
                // alert("enter");
                // alert(this.value);
                data += this.value + "," ;
                // data.push($(this).val());
            }
        }
    )
    if(""==data){
        alert("Please select at least one item");
        return;
    }
    $.ajax({
        url: "/order/cartSubmit",
        data: {
            ch:data
        },
        type: 'POST',
        success: function (msg) {
            if ('error' != msg && msg.indexOf("/") != -1) {
                window.location.href = msg;
            }
            else {
                alert("Generator order fail,please try again");
            }
        }
    })
}
// return false;
//     })
// })