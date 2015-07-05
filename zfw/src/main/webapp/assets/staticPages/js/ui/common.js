	function hideOrOpenNav() {
		var navObj = $(".newNav");
		var shadow = $(".popShadow");
		if (navObj.hasClass("none")) {
			navObj.removeClass("none");
			shadow.removeClass("none");
		} else {
			navObj.addClass("none");
			shadow.addClass("none");
		}
	}