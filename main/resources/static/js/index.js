$(function() {
	var curPage = 1; //当前页码
	//var total, pageSize, totalPage; //总记录数，每页显示数，总页数
	getData(1);

	function getData(page) {
		$.ajax({
			url: "/begin",
			type: "POST",
			data: { 'pageNum': page - 1 },
		})
			.done(function(data) {
				// 該当するデータが無かった場合
				if (!data) {
					alert('該当するデータはありませんでした');
					return;
				}

				// 画面のtableタグに表示
				const jsonmoto = JSON.parse(data);
				// json文字列をjsonオブジェクトに切り替え

				// console.log(json);
				total = jsonmoto.total; //总记录数
				pageSize = jsonmoto.pageSize; //每页显示条数
				curPage = page; //当前页
				totalPage = jsonmoto.totalPage; //总页数
				json = jsonmoto.list;

                // ヘッダ以外の全行を削除
				$('#listtbl').find("tr:gt(0)").remove();
				
				var tr = [];
				for (var i = 0; i < json.length; i++) {
					if (typeof(json[i].id) == 'undefined') {
						continue;
					}
					tr.push('<tr>');
					tr.push('<td>' + json[i].id + '</td>');
					tr.push('<td>' + json[i].name + '</td>');
					tr.push('<td>' + json[i].sex + '</td>');
					tr.push('<td>' + json[i].classx + '</td>');
					tr.push('<td>' + json[i].sansuwu + '</td>');
					tr.push('<td>' + json[i].kokugo + '</td>');
					tr.push('<td>' + json[i].adress + '</td>');
					tr.push('<td>' + json[i].birthday + '</td>');
					tr.push('<td>' + json[i].TOUROKUBI + '</td>');
					tr.push('<td>' + json[i].KOUSINNBI + '</td>');
					tr.push('<td><button class=\'btnUpdate\' id=' + json[i].id + '>更新</button>&nbsp;<button class=\'btnDelete\' id=' + json[i].id + '>削除</button></td>');
				}
				$('#listtbl').append($(tr.join('')));
			})
			.fail(function() {
				alert("begin error");
			})
			.always(function() {
				getPageBar();
			});
	}
	$(document).on('click', '.btnDelete', function() {
		var parent = $(this).parent().parent();
		var name = parent.children('td:nth-child(1)');
		if (confirm('以下の学生情報を削除しますか？\r\n学生名：' + name.html())) {
			var id = $(this).attr('id');
			$.ajax({
				type: 'DELETE',
				url: '/del/' + id,
			})
				.done(function() {
					$('#' + id).parent().parent().remove();
				})
				.fail(function() {
					alert("del error");
				})
				.always(function() {
				});
		}
	});

	$(document).on('click', '.btnUpdate', function() {
		var id = $(this).attr('id');
		window.open("/update?id=" + id, "_self");
	});

	$("#pagecount").on('click', 'span a', function() {
		var rel = $(this).attr("rel");
		if (rel) {
			getData(rel);
		}
	});

	//获取分页条
	function getPageBar() {
		$("#pagecount").find('*').remove();
		//页码大于最大页数
		if (curPage > totalPage) { curPage = totalPage; }
		//页码小于1
		if (curPage < 1) curPage = 1;
		pageStr = "<span>共" + total + "条</span><span>" + curPage + "/" + totalPage + "</span>";
		//如果是第一页
		if (curPage == 1) {
			pageStr += "<span>首页</span><span>上一页</span>";
		} else {
			pageStr += "<span><a href='javascript:void(0)' rel='1'>首页</a></span><span><a href='javascript:void(0)' rel='" + (curPage - 1) + "'>上一页</a></span>";
		}
		//如果是最后页
		if (curPage >= totalPage) {
			pageStr += "<span>下一页</span><span>尾页</span>";
		} else {
			pageStr += "<span><a href='javascript:void(0)' rel='" + (parseInt(curPage) + 1) + "'>下一页</a></span><span><a href='javascript:void(0)' rel='" + totalPage + "'>尾页</a></span>";
		}
		$("#pagecount").append(pageStr);
	}

});