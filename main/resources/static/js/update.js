$(function() {

	$("#updform").on("submit", function(e) {
		// HTMLでの送信をキャンセル
		e.preventDefault();

		var id = $('input[name=id]').val();
		var name = $('input[name=name]').val();
		var sex = $('input[name=sex]:checked').val();
		//var sex = $('[name=sex] option:selected').val();
		var classx = $('[name=classx] option:selected').val();
		var sansuwu = $('input[name=sansuwu]').val();
		var kokugo = $('input[name=kokugo]').val();
		var adress = $('input[name=adress]').val();
		var birthday = $('input[name=birthday]').val();

		var model = {
			'id': id,
			'name': name,
			'sex': sex,
			'classx': classx,
			'sansuwu': sansuwu,
			'kokugo': kokugo,
			'adress': adress,
			'birthday': birthday
		};

		// 操作対象のフォーム要素を取得
		var $form = $(this);
		// 送信
		$.ajax({
			url: '/update2',
			type: $form.attr('method'),
			data: model,
			//datatype: 'json',
			//contentType: 'application/x-www-form-urlencoded',
		})
			.done(function() {
				window.open("/", "_self");
			})
			.fail(function() {
				alert("updform error");
			})
			.always(function() {
			});
	})
});