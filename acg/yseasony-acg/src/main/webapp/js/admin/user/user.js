var user = {
	page : function(pageNo) {
		$.ajax({
			url : "./user/userList",
			type : 'POST',
			data : {
				pageNo : pageNo
			},
			success : function(page) {
				$("#pagination").pager({
					pagenumber : page.pageNo,
					pagecount : page.totalPages,
					buttonClickCallback : pageClick
				});
				$(".ready").remove();
				$("#user_template").show();
				$.each(page.result, function(i, n) {
					var row = $("#user_template").clone();
					// var view = "<a href='editRole.do?roleId="+n.id+"'>查看</a>
					// ";
					// var del = "<a
					// href='deleteRole.do?roleId="+n.id+"'onclick='javascript:return
					// del();'>删除</a>";
					row.find("#cbox").html("<input type='checkbox' />");
					row.find("#uid").text(n.id);
					row.find("#loginName").text(n.loginName);
					row.find("#name").text(n.name);
					row.find("#sex").text(n.sex == true ? '男' : '女');
					row.find("#enabled").text(n.enabled == true ? '启用' : '禁用');
					row.find("#email").text(n.email);
					if (i % 2 == 0) {
						row.attr("class", "alt-row ready");
					} else {
						row.attr("class", "ready");
					}
					row.appendTo("#user_table");
				});
				$("#user_template").hide();
			},
			error : function() {
				console.log("aaaaaaa");
			}
		});
	},
	userValidate : function() {
		$("#userform").validate({
			rules : {
				loginName : {
					required : true,
					minlength : 6,
				},
				password : {
					required : true,
					minlength : 6
				},
				password_confirm : {
					required : true,
					minlength : 6,
					equalTo : "#password"
				}
			},
			messages : {
				loginName : {
					required : "请输入用户名",
					minlength : jQuery.format("用户名长度至少 {0} 位")
				},
				password : {
					required : "请输入密码",
					minlength : jQuery.format("密码长度至少 {0} 位")
				},
				password_confirm : {
					required : "请输入确认密码",
					minlength : jQuery.format("密码长度至少 {0} 位"),
					equalTo : "两次密码输入不同"
				}
			},
			errorElement : "span",
			submitHandler : function() {
				alert("submitted!");
			},
			success : function(span) {
				span.addClass("checked");
			}

		});
	}
};

var pageClick = function(pageclickednumber) {
	user.page(pageclickednumber);
};

$(document).ready(
		function() {
			user.page(1);
			$('.check-all').click(mainContent.checkAllHandler);
			$('.content-box ul.content-box-tabs li a').click(mainContent.tabClickHandler);
			user.userValidate();
		});