var examinationPager = {
	url : "./exam/examinationPagerList",
	pageSuccess : function(page) {
		common.pagination(page, this.pageClick);
		$("#examinationPager_template").show();
		$.each(page.result, function(i, n) {
			var row = $("#examinationPager_template").clone();
			row.find("#cbox").html("<input type='checkbox' />");
			row.find("#exId").text(n.exId);
			row.find("#title").text(n.title);
			row.find("#startTime").text(n.startTime);
			row.find("#startTime").text(n.endTime);
			row.find("#invigilateName").text(n.invigilateName);
			row.find("#createTime").text(n.createTime);
			if (i % 2 == 0) {
				row.attr("class", "alt-row ready");
			} else {
				row.attr("class", "ready");
			}
			row.appendTo("#examinationPager_table");
		});
		$("#examinationPager_template").hide();
	},
	page : function(pageNo) {
		common.page(this.url, pageNo, this.pageSuccess);
	},
	userValidate : function() {
		$("#examinationPagerform").validate({
			rules : {
				title : {
					required : true
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
				title : {
					required : "请输入试卷名称"
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
	},
	pageClick : function(pageclickednumber) {
		this.page(pageclickednumber);
	}
};

$(document).ready(
		function() {
			examinationPager.page(1);
			$('.check-all').click(mainContent.checkAllHandler);
			$('.content-box ul.content-box-tabs li a').click(
					mainContent.tabClickHandler);
			examinationPager.userValidate();
		});