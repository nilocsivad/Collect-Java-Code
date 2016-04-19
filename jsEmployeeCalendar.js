/**
 * @author Colin Davis
 * @email nilocsivad@hotmail.com
 * @date 2016-04-06
 */
(function($) {
	$.fn.jsCalendar = function(options, param) {

		if (typeof options == 'string') {
			var method = $.fn.jsCalendar.methods[options];
			if (method) {
				return method(this, param);
			}
		}

		var defaults = {
			calendarCount : 2,
			year : 0,
			month : null,
			splitDay : false,
			width : 210,
			maxDay : 0,
			startDay : "",
			selectWeek : true,
			bgColor : "#f0f0f0",
			dayBgColor : "#e6e6e6",
			selBgColor : "#81B0F7",
			dataBgColor : "#F58796",
			dayClick : function(e) {
				var _sty = {
					"background-color" : options.selBgColor
				};
				var _sty2 = {
					"background-color" : options.dayBgColor
				}
				var daydom = $(this);
				if (daydom.hasClass("week") && options.selectWeek == false) {
					//
				} else { // 可以选择周六日
					if (daydom.hasClass("sel-day")) {
						daydom.removeClass("sel-day").css(_sty2);
					} else {
						if (options.startDay == "" || daydom.attr("date") >= options.startDay) {
							if (!daydom.hasClass("got-day")) {
								if (options.maxDay > 0) {
									var len = $.fn.jsCalendar.methods["getSelectedData"]($(this).parent().parent()).length;
									if (len >= options.maxDay)
										return;
								}
								daydom.addClass("sel-day").css(_sty);
							}
						}
					}
				}
			}
		};

		var generate = function(cl, year, month) {
			//
			var m2 = month + 1;
			var ym = year + "-" + (m2 < 10 ? "0" + m2 : m2);
			//
			var _day_w = window.parseInt(options.width / 7);
			//
			var _show_style = {
				"display" : "block",
				"float" : "left",
				"width" : options.width + "px",
				"height" : _day_w + "px",
				"margin" : 0,
				"padding" : 0,
				"line-height" : _day_w + "px",
				"text-align" : "center",
				"background-color" : options.dayBgColor,
				"background-position" : "center"
			};
			//
			// 顶部年月
			var _fmt_show = "<div class='year-month'>{0}</div>";
			var _st = $(_fmt_show.format(ym)).css(_show_style).css("font-weight", "bold");
			$(cl).append(_st);
			//
			var _day_style = $.extend({}, _show_style);
			_day_style["width"] = _day_w + "px";
			_day_style["background-color"] = options.bgColor;
			//
			// 周几
			var _wk = [ "日", "一", "二", "三", "四", "五", "六" ];
			var _fmt_wk = "<div>{0}</div>";
			for (var i = 0; i < 7; ++i) {
				var _t = $(_fmt_wk.format(_wk[i])).css(_day_style);
				$(cl).append(_t);
			}
			//
			var _day1 = new Date();
			_day1.setFullYear(year, month, 1);
			_day1.setHours(12, 0, 0, 0);
			//
			// 上个月最后一周的几天
			var _len_nd = _day1.getDay();
			_len_nd = _len_nd == 0 ? 7 : _len_nd;
			_day1.setDate(0);
			var _bef_s = $.extend({}, _day_style);
			_bef_s["color"] = "#aaaaaa";
			for (var l = _day1.getDate(), i = l - _len_nd + 1; i <= l; ++i) {
				var _nd = $(_fmt_wk.format(i)).css(_bef_s);
				$(cl).append(_nd);
			}
			_day1.setFullYear(year, month, 1);
			_day1.setHours(12, 0, 0, 0);
			//
			_day1.setMonth(month + 1);
			_day1.setDate(0);
			var _md = _day1.getDate();
			_day_style["background-color"] = options.dayBgColor;
			_day_style["cursor"] = "pointer";
			for (var i = 1; i <= _md; ++i) {
				var _full_d = ym + "-" + (i < 10 ? "0" + i : i);
				if (options.startDay == "" || _full_d >= options.startDay) {
					var _nd = $("<div>{0}</div>".format(i)).addClass("day").addClass("day-" + _full_d).css(_day_style).attr("date", _full_d).on("click", options.dayClick);
					_day1.setDate(i);
					if (_day1.getDay() % 6 == 0) {
						_nd.addClass("week");
					}
					$(cl).append(_nd);
				} else {
					var _nd = $(_fmt_wk.format(i)).css(_bef_s);
					$(cl).append(_nd);
				}
			}
			//
			// 尾行显示下个月
			_len_nd = _day1.getDay();
			if (month == 2 || _len_nd == 6) {
				_len_nd = _len_nd == 6 ? -1 : _len_nd;
			}
			_len_nd = 6 - _len_nd;
			for (var i = 1; i <= _len_nd; ++i) {
				var _nd = $(_fmt_wk.format(i)).css(_bef_s);
				$(cl).append(_nd);
			}
		};

		var options = $.extend(defaults, options);
		return this.each(function() {
			//
			$.data(this, "jsCalendar", options);
			$(this).css({
				"width" : (options.calendarCount == 2 ? (options.width * 2 + 5) : (options.width + 5)) + "px"
			});
			//
			var _day_w = window.parseInt(options.width / 7);
			var _s1 = {
				"display" : "block",
				"float" : "left",
				"width" : options.width + "px",
				"height" : _day_w * 8,
				"background-color" : options.bgColor,
				"margin" : "0 5px 0 0",
				"padding" : 0,
				"font-size" : "12px"
			}
			var c1 = $("<div></div>").css(_s1);
			$(this).append(c1);
			if (options.calendarCount == 2) {
				_s1["margin"] = 0;
				var c2 = $("<div></div>").css(_s1);
				$(this).append(c2);
			} else {
				$(c1).css("margin", "0px");
			}
			var c3 = $("<div style='clear: both; width: 0px; height: 0px'></div>");
			$(this).append(c3);
			//
			var _day = new Date();
			if (options.year != 0)
				_day.setYear(options.year);
			if (options.month != null)
				_day.setMonth(options.month);
			_day.setHours(12, 0, 0, 0);
			//
			generate(c1, _day.getFullYear(), _day.getMonth());
			if (options.calendarCount == 2) {
				generate(c2, _day.getFullYear(), _day.getMonth() + 1);
			}
		});
	};

	$.fn.jsCalendar.methods = {
		options : function(c) {
			return $.data(c[0], "jsCalendar");
		},
		setData : function(c, arr) {
			if (arr instanceof Array) {
				var options = $.data(c[0], "jsCalendar");

				if (options.splitDay) {

					// window.alert(document.currentScript.src);
					var src = $("script[src$='jsEmployeeCalendar.js']").attr("src");
					var _i5 = src.lastIndexOf("jsEmployeeCalendar.js");
					var prefix = src.substr(0, _i5);
					// window.alert(prefix + "\r\n" + _i5);

					$.each(arr, function(i, data) {

						var day = data["date"];
						if (options.startDay == "" || day >= options.startDay) {

							var _m5 = data["morning"], _n5 = data["afternoon"];
							var _sty = {
								// "background-color" : options.dataBgColor,
								"background-image" : "url('" + prefix + _m5 + "-" + _n5 + ".png')",
								"background-position" : "center"
							};

							var who = $(c).find("div.year-month");
							if (!who.hasClass("set-ym")) {
								// window.alert(data["who"] + " - " +
								// who.html());
								who.html(data["who"] + " - " + who.html());
								who.addClass("set-ym");
							}

							var msg = data["message"] || "";
							var item = $(c).find("div.day-" + day);
							item.addClass("got-day").attr("title", msg).css(_sty);

							var json = {};
							for ( var k in data) {
								json[k] = data[k];
							}

							item.attr("extra-data", JSON.stringify(json));
						}
					});
				} else { // whole day
					$.each(arr, function(i, day) {
						if (options.startDay == "" || day >= options.startDay) {
							var item = $(c).find("div.day-" + day);
							item.addClass("got-day").css(_sty);
						}
					});
				}
			}
		},
		get : function(c, sel) {
			var days = $(c).find(sel);
			var data = new Array();
			days.each(function(i, day) {
				data.push($(day).attr("date"));
			});
			return data;
		},
		getData : function(c) {
			return $(c).jsCalendar("get", "div.day.got-day");
		},
		getSelectedData : function(c) {
			return $(c).jsCalendar("get", "div.day.sel-day");
		}
	};
})(jQuery, window.document);