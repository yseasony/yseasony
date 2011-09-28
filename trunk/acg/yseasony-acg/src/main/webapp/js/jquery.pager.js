(function($) {

	$.fn.pager = function(options) {

		return this.each(function() {

			$(this).empty().append(
					renderpager(parseInt(options.pagenumber),
							parseInt(options.pagecount),
							options.buttonClickCallback));

		});
	};

	function renderpager(pagenumber, pagecount, buttonClickCallback) {

		var $pager = $('<div></div>');

		$pager.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback))
			  .append(renderButton('上一页', pagenumber, pagecount, buttonClickCallback));

		var startPoint = 1;
		var endPoint = 5;

		if (pagenumber > 4) {
			startPoint = pagenumber - 2;
			endPoint = pagenumber + 2;
		}

		if (endPoint > pagecount) {
			startPoint = pagecount - 4;
			endPoint = pagecount;
		}

		if (startPoint < 1) {
			startPoint = 1;
		}

		// loop thru visible pages and render buttons
		for ( var page = startPoint; page <= endPoint; page++) {

			var currentButton = $('<a class="number">' + (page) + '</a>');

			page == pagenumber ? currentButton.addClass('current')
					: currentButton.click(function() {
						buttonClickCallback(this.firstChild.data);
					});
			currentButton.appendTo($pager);
		}

		// render in the next and last buttons before returning the whole
		// rendered control back.
		$pager.append(renderButton('下一页', pagenumber, pagecount, buttonClickCallback))
		      .append(renderButton('尾页', pagenumber, pagecount, buttonClickCallback));

		return $pager;
	}

	// renders and returns a 'specialized' button, ie 'next', 'previous' etc.
	// rather than a page number button
	function renderButton(buttonLabel, pagenumber, pagecount,
			buttonClickCallback) {

		var $Button = $('<a class="pgNext">' + buttonLabel + '</a>');

		var destPage = 1;

		// work out destination page for required button type
		switch (buttonLabel) {
		case "首页":
			destPage = 1;
			break;
		case "上一页":
			destPage = pagenumber - 1;
			break;
		case "下一页":
			destPage = pagenumber + 1;
			break;
		case "尾页":
			destPage = pagecount;
			break;
		}

		// disable and 'grey' out buttons if not needed.
		if (buttonLabel == "首页" || buttonLabel == "上一页") {
			pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button
					.click(function() {
						buttonClickCallback(destPage);
					});
		} else {
			pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button
					.click(function() {
						buttonClickCallback(destPage);
					});
		}

		return $Button;
	}

	// pager defaults. hardly worth bothering with in this case but used as
	// placeholder for expansion in the next version
	$.fn.pager.defaults = {
		pagenumber : 1,
		pagecount : 1
	};

})(jQuery);

