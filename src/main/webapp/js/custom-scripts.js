/**
 * 
 */


$( document ).ready(function() {
    /*GOVUK.modules.start();*/
    GOVUK.stickAtTopWhenScrolling.init();
            
    var $buttons = $("label input[type='radio'], label input[type='checkbox']");
    var selectionButtons = new GOVUK.SelectionButtons($buttons);
});

$( 'input' ).click(function(e) {
    alert("Click!");
    if ($(this).getAttribute("value") === "id") {
        alert("ID selected");
    }
    else {
        alert("Name selected");        
    }
    
});


$( 'form' ).submit(function(e) {
    e.preventDefault();
	$.ajax({
	    url: "rest/service/applicants",
	    type: "GET",
	    dataType: "xml",
	})
	.done(function( xml ) {
		$("#dvContent").append("<ul></ul>");
		$(xml).find('applicant').each(function(){
		    var id = $(this).children('applicantId').text();
		    var firstName = $(this).find('firstName').text();
		    var lastName = $(this).find('lastName').text();
		    $("<li></li>").html(id + ": " + firstName + " " + lastName).appendTo("#dvContent ul");
		});
	})
	.fail(function( xhr, status, errorThrown ) {
		alert( "Sorry, there was a problem!" );
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	})
	.always(function( xhr, status ) {
	    alert( "The request is complete!" );
	});
});