///infinity scroll
var pageNumber = 0;


$(document).ready(function() {

	$("#loader-img").hide();
	$("#fim-btn").hide();

});

$(window).scroll(function() {

	var scrollTop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();
	//console.log('scrollTop : ',scrollTop,' conteudo: ',conteudo);

	//onde entra o ajax
	if (scrollTop >= conteudo) {
		///console.log('ajax entra aqui');

		pageNumber++;

		setTimeout(function() {

			finByScollBar(pageNumber);

		}, 200);
	}
});



function finByScollBar(pageNumber) {

	$.ajax({
		method: "GET",
		url: "/promocao/list/ajax",
		data: {
			page: pageNumber
		},
		beforeSend: function() {
			$("#loader-img").show();
		},
		success: function(response) {
			if (response.length > 150) {
				$(".row").fadeIn(250, function() {
					$(this).append(response);
				});
			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
		},
		error: function(xhr) {
			alert("Ops, ocorreu um erro", xhr.status, xhr.statusText);
		},
		complete: function() {
			$("#loader-img").hide();
		},
	});
}
///likes na promocoes
$("button[id*='likes-btn-']").on("click", function() {
	var id = $(this).attr("id").split("-")[2];
	console.log("id:", id);
	$.ajax({
		method: "POST",
		url: "/promocao/like/" + id,
		success: function(response) {
			$("#likes-count-" + id).text(response);
		},
		error: function(xhr) {
			alert("ops,ocorreu um erro:" + xhr.status + ", " + xhr.statusText);
		}
	});
});

//autocomplete

$("#autocomplete-input").autocomplete({
	source : function(request,response){
		$.ajax({
			method: "GET",
			url: "/promocao/site",
			data: {
				termo: request.term
			},
			success: function(result){
				response(result);
			}
		});
	}
});










