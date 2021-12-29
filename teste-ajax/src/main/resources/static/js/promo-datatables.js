$(document).ready(function(){
	moment.locale('pt-br');
	var table = $('#table-server').DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu:[ 1, 2,3],
	    pageLength: 2,
		ajax: {
			url: "/promocao/datatables/server",
			data: "data"
		},
		columns: [
			{data:'id'},
			{data:'titulo'},
			{data:'site'},
			{data:'linkPromocao'},
			{data:'descricao'},
			{data:'linkImagem'},
			{data:'preco', render : $.fn.DataTable.render.number('.' ,',', 2,' R$')}, 
			{data:'likes'},
			{data:'dtCadastro', render : 
				function(dtCadastro){
				   return moment(dtCadastro).format('LLL');
				}
			
			},
			{data:'categoria.titulo'}
		],
		dom: 'Bfrtip',
		buttons: [
			{
				text:"Editar",
				attr:{
					id:"btn-editar",
					type:"button"				
				},
				enabled: false
			},
			{
				text:"Excluir",
				attr:{
					id:"btn-excluir",
					type:"button"
				},
				enabled: false
			}
		]		
	});

	//acao oara marcar/desmarcar buttons ao clicar na ordenacao
	$("#table-server thead").on('click', 'tr', function(){
		table.buttons().disable();
	});
	
		//acao oara marcar/desmarcar buttons ao selecionar registros
	$("#table-server tbody").on('click', 'tr', function(){
		if($(this).hasClass('selected')){
			$(this).removeClass('selected'); 
			table.buttons().disable();
			
		}else{
		   $('tr.selected').removeClass('selected'); 
		   $(this).addClass('selected');
		   table.buttons().enable();
		}	
	});

    // acao do botao de editar (abrir modal)
	$("#btn-editar").on("click",function(){	
		if(  isSelectedRow()){
			  $("#modal-form").modal('show');
		 }
	});


	// acao do botao de excluir (abrir modal)
	$("#btn-excluir").on('click', function() {
		if ( isSelectedRow() ) {
			$("#modal-delete").modal('show');
		}
	});
	

	
	//exclusao de uma promocao
	$("#btn-del-modal").on("click", function(){	
	    var id =getPromoId();
		$.ajax({
			method:"GET",
			url:"/promocao/delete/"+id,
			success: function(){
				$("#modal-delete").modal('hide');
				table.ajax.reload();
			},
			error:  function(){
				alerts("ops.... ocorreu um erro");
			}
		});
	});

	function getPromoId(){
		return 	table.row(table.$('tr.selected')).data().id;
	}
	
	function isSelectedRow() {
		var trow = table.row(table.$('tr.selected'));
		return trow.data() !== undefined;
	}

});


