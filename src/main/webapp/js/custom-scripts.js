/**
 * 
 */

function addRecord() {
    $("<tr></tr>").appendTo("tbody");
    
    var id = $(this).find("applicantId").text();
    $("<td></td>").html(id).appendTo("tr:last");
    
    var firstName = $(this).find("firstName").text();
    var lastName = $(this).find("lastName").text();
    $("<td></td>").html("<a href='#'>" + firstName + " " + lastName + "</a>").appendTo("tr:last");
    
    var position = $(this).find("title");
    $("<td></td>").html(position).appendTo("tr:last");
    
    var dateApplied = $(this).find("dateApplied").text();
    $("<td></td>").html($.format.date(dateApplied, "dd.MM.yyyy")).appendTo("tr:last");
    
    var status = $(this).find("status");
    $("<td></td>").html(status).appendTo("tr:last");
}

function onFormSubmit(e){
        e.preventDefault();
        $("#searchresults tbody").remove();
        $.ajax({
	       url: "rest/resources/applicants",
	       type: "GET",
	       dataType: "xml",
        })
        .done(function( xml ) {
            $("<tbody></tbody>").appendTo("#searchresults");
            $(xml).find("applicant").each(addRecord);
        })
        .fail(function( xhr, status, errorThrown ) {
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
        })
        .always(function( xhr, status ) {
	    //alert( "The request is complete!" );
        });
    };

$( document ).ready(function() {
    /*GOVUK.modules.start();*/
    GOVUK.stickAtTopWhenScrolling.init();
            
    var $buttons = $("label input[type='radio'], label input[type='checkbox']");
    var selectionButtons = new GOVUK.SelectionButtons($buttons);
    
    $("form").submit(onFormSubmit);

});
