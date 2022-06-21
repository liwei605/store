
/*全选全不选*/
function checkall(ckbtn) {
    $(".ckitem").prop("checked", $(ckbtn).prop("checked"));
    calcTotal();
}

//批量删除按钮
function selDelCart() {
    //遍历所有按钮
	var i =$(".ckitem").length - 1;
	var flag=0;
    for (i ; i >= 0; i--) {
        //如果选中
        if ($(".ckitem")[i].checked) {
            //删除
			var cid=$($(".ckitem")[i]).val();
			// delCartItem(value);
			$.ajax({
				url: "/carts/" + cid + "/num/delete",
				type: "POST",
				dataType: "JSON",
				success: function (json) {
					if (json.state == 200) {
						//alert("删除成功！");

					} else {
						flag=-1;
					}
				},
				error: function (xhr) {
					alert("您的登录信息已经过期，请重新登录！HTTP响应码：" + xhr.status);
					location.href = "login.html";
				}
			});
			if(flag==0)
			{
				$($(".ckitem")[i]).parents("tr").remove();
			}else
			{
				alert("所选中的商品删除失败！");
			}
        }
    }
    calcTotal();
}


//计算总价格的方法

function calcTotal() {
	//选中商品的数量
	var vselectCount = 0;
	//选中商品的总价
    var vselectTotal = 0;

	//循环遍历所有tr
	for (var i = 0; i < $(".cart-body tr").length; i++) {
		//计算每个商品的价格小计开始
		//取出1行
		var $tr = $($(".cart-body tr")[i]);
		//取单价
		var vprice = parseFloat($tr.children(":eq(3)").children("span").html());
		//取数量
		var vnum = parseFloat($tr.children(":eq(4)").children(".num-text").val());
		//小计金额
		var vtotal = vprice * vnum;
		//赋值
		$tr.children(":eq(5)").children("span").html(vtotal);
		//计算每个商品的价格小计结束

		//检查是否选中
		if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
			//计数
			vselectCount++;
			//计总价
			vselectTotal += vtotal;
		}
		//将选中的数量和价格赋值
		$("#selectTotal").html(vselectTotal);
		$("#selectCount").html(vselectCount);
	}
}