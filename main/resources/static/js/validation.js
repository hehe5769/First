$(function () {
  //バリデーション
  $("input.require").on("blur", function () {
    let error;
    let value = $(this).val();
    if (value == "" || !value.match(/[^\s\t]/)) {
      error = true;
    }

    if (error) {
      //エラー時の処理
      alert('必須項目です');
    }
  });
});

$(function () {
  //バリデーション
  $("input.num-only").on("blur", function () {
    let error;
    let value = $(this).val();
    if (!Number.isFinite(Number(value))) {
      error = true;
    }

    if (error) {
      //エラー時の処理
      alert('数値を入力してください');
    }
  });
});

    $(function(){
        //<form>タグのidを指定
        $("#formCheck").validationEngine(
            'attach', {
                promptPosition: "topLeft"//エラーメッセージ位置の指定
            }
        );
    });