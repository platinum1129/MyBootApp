$(function() {
	$('.header').on('click', function(event) {
		window.location.href = '/';
	});
	
	/**
	 * 行削除イベント
	 */
	$('#table1').on('click', '.deleteUser', function(event) {
		// 普通にonでイベント定義すると、書き換え後にイベントが
		// 発火しなくなるため、delegate式で定義
		
		var row = $(this).parents("tr");
		var id = row.find("[name='id']").val();
		
		$.ajax({
			type : "GET",
			url : "/repo/delete/" + id,
			dataType : "html",
			success : function(data, status, xhr) {
				// 明細だけ書き換え（そのうち、HTML全体でなく、明細のHTMLだけ返すようにしたい）
				var html = $($.parseHTML(data));
				$('#table1').empty().append(html.filter('#table1')[0].innerHTML);
		    },
			error : function(XMLHttpRequest, status, errorThrown) {
				alert('error');
			}
		});
	});
	
});

//
//var deleteUser = function(){
//	alert($(this).html());
//}
