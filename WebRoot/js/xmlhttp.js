function send_request(callback, urladdress, isReturnData) {
	var xmlhttp = getXMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			try {
				if (xmlhttp.status == 200) {
					if (isReturnData && isReturnData == true) {
						callback(xmlhttp.responseText);
					}
				} else {
					callback("抱歉，没找到此页面:" + urladdress + "");
				}
			} catch (e) {
				callback("抱歉，发送请求失败，请重试 " + e);
			}
		}
	}
	xmlhttp.open("GET", urladdress, true);
	xmlhttp.send(null);
}

function getXMLHttpRequest() {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		try {
			xmlhttp = new XMLHttpRequest();
			xmlhttp.overrideMimeType("text/html;charset=UTF-8");// 设定以UTF-8编码识别数据
		} catch (e) {
		}
	} else if (window.ActiveXObject) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHttp");
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Msxml3.XMLHttp");
				} catch (e) {
				}
			}
		}
	}
	return xmlhttp;
}

/*
 * function confirmAgree() { if (confirm('确定通过该项目么?')) return true; return
 * false; }
 */

function confirmAgree(code) {
	$.layer({
		shade : false,
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '确定通过该项目么？',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				agreefun(code);
			},
			no : function() {
				var i = $.layer({});
				layer.close(i);
			}
		}
	});
}

function confrimDisAgree(code) {
	var html = '<div align="center" style="background-color: #d7e7f6"><p style="color:#4e4e4e">请填写不通过原因</p><div><textarea style="width:340px;height:120px;" class="advice"></textarea></div><div><a style="color: #0018ff" href="javascript:" class="yes">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: #0018ff" href="javascript:" class="no">取消</a></div></div>';
	var i = $.layer({
		type : 1,
		title : false,
		shade : false,
		closeBtn : [ 0, false ],
		border : [ 1, 0.9, '#ccc', true ],
		offset : [ '100px', '' ],
		area : [ '370px', 'auto' ],
		page : {
			html : html
		}
	});

	var yes = $('.yes'), no = $('.no');
	yes.on('click', function() {
		var advice = $('.advice').html();
		if (advice == '') {
			layer.msg('请填写项目不通过原因', 1, 2);
		} else {
			disagreefun(code, advice);
			layer.close(i);
		}
	});
	no.on('click', function() {
		layer.close(i);
	});
}

function agreefun(code) {
	var url = "/projectInformation/front/project_agreeProject";
	$.ajax({
		url : url,
		data : {
			"projectid" : code
		},
		async : true,
		type : 'post',
		success : function(result) {
			if (result == "ok") {
				layer.msg('审核通过', 1, 9);
				location.reload();
			} else {
				layer.msg('请分配小组', 1, 4);
				$.layer({
					type : 2,
					title : false,
					border : [ 12, 0.5, '#ccc', true ],
					iframe : {
						src : "/canvassGroup/front/canvassGroup_groupConfigUI?project="+code
					},
					area : [ '750px', '466px' ],
					offset : [ '100px', '' ],
					end : function() {
						location.reload();
					}
				});
			}
		}
	});
}

function disagreefun(code, reason) {
	var url = "/projectInformation/front/project_disagreeProject";
	$.ajax({
		url : url,
		data : {
			"projectid" : code,
			"reason" : reason
		},
		async : true,
		type : 'post',
		success : function(result) {
			if (result == "ok")
				layer.msg('该项目暂时不通过', 1, 4);
			location.reload();
		}
	});
}
