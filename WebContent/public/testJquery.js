/*$(function() {
  $('#texteJQ').html('Hello world. Ce texte est affiché par jQuery.');
});*/

/*$(
	$('#bouttonTest').on( "onclick", function(event){
		alert('test');
		return false;
	}))
	;*/
	function validationForm(){
		$.ajax({
			url: "/Test/testToto",
		})
		.done(function( data ) {
			if ( console && console.log ) {
				//console.log( "Sample of data:", data.slice( 0, 100 ) );
			}
			alert(data);
			$('#texteJQ').html(data.node1);
		});
	}