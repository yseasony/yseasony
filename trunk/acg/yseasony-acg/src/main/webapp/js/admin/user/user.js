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
						row.attr("class","ready");
					}
					row.appendTo("#user_table");
				});
				$("#user_template").hide();
			},
			error : function() {
				console.log("aaaaaaa");
			}
		});
	}
};

var pageClick = function(pageclickednumber) {
	user.page(pageclickednumber);
};

$(document).ready(function() {
	user.page(1);
    $('.check-all').click(mainContent.checkAllHandler);
    $('.content-box ul.content-box-tabs li a').click(mainContent.tabClickHandler);
    
    $("#userform").validate({
		rules: {
			loginName: {
				required: true,
				minlength: 6,
			},
			password: {
				required: true,
			},
			password_confirm: {
				required: true,
				equalTo: "#password"
			}
		},
		messages: {
			loginName: {
				required: "Enter a username",
				minlength: jQuery.format("Enter at least {0} characters")
			},
			password_confirm: {
				required: "Repeat your password",
				minlength: jQuery.format("Enter at least {0} characters"),
				equalTo: "Enter the same password as above"
			}
		},
		errorElement: "span",
//		errorClass: "input-notification error png_bg" ,
		// the errorPlacement has to take the table layout into account
//		errorPlacement: function(error, element) {
//			error.prependTo(element.next("span"));
//		},
		// specifying a submitHandler prevents the default submit, good for the demo
		submitHandler: function() {
			alert("submitted!");
		},
		// set this class to error-labels to indicate valid fields
		success: function(span) {
			span.addClass("checked");
		}
		
	});
	
});