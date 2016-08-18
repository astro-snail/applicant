/**
 * 
 */

var urlBase = "rest/resources/applicants";

function onSearchMethod(e) {
    var value = $("form input[name=searchmethod]:checked").val();
    if (value === "id") {
        $(".search-name").hide();
        $(".search-id").show();
    }
    else {
        $(".search-name").show();
        $(".search-id").hide();
    }
}

function buildUrl() {
    var url = urlBase;
    
    if ($("form input[name=searchmethod]:checked").val() === "id") {
        var id = $("form input[name=applicantId]").val();
        if (id.length) 
            url = url + "/" + id;
    }
    else {
        var firstName = $("form input[name=firstName]").val();
        var lastName = $("form input[name=lastName]").val();
        if (firstName.length || lastName.length) 
            url = url + "/" + firstName + "/" + lastName;            
    }
    return url;
}

function onGetDetails(e) {
    e.preventDefault();
        
    $("#details").hide();
    $("#details .section-content").empty();
    
    var id = $(this).attr("id");
    
    // Get personal data
    var urlD = urlBase + "/" + id + "/details";
    $.ajax({
	   url: urlD,
	   type: "GET",
	   dataType: "xml",
    })
    .done( function( xml ) {
        $("<ul></ul>").appendTo("#personaldata .section-content");
        $(xml).find("applicantDetails").each(addPersonalData);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
        
    // Get experience
    var urlEx = urlBase + "/" + id + "/experience";
    $.ajax({
	   url: urlEx,
	   type: "GET",
	   dataType: "xml",
    })
    .done(function( xml ) {
        $("<ul></ul>").appendTo("#experience .section-content");
        $(xml).find("experience").each(addExperience);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
    
    // Get education
    var urlEd = urlBase + "/" + id + "/education";
    $.ajax({
	   url: urlEd,
	   type: "GET",
	   dataType: "xml",
    })
    .done(function( xml ) {
        $("<ul></ul>").appendTo("#education .section-content");
        $(xml).find("education").each(addEducation);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
    
    $("#details").show();
}

function addPersonalData() {
    var firstName = $(this).find("firstName").text();
    $("<li></li>").html(firstName).appendTo("#personaldata ul");
    
    var lastName = $(this).find("lastName").text();
    $("<li></li>").html(lastName).appendTo("#personaldata ul");
    
    var mobile = $(this).find("mobile").text();
    $("<li></li>").html(mobile).appendTo("#personaldata ul");
    
    var landline = $(this).find("landline").text();
    $("<li></li>").html(landline).appendTo("#personaldata ul");
    
    var address = $(this).find("address").text();
    $("<li></li>").html(address).appendTo("#personaldata ul");
    
    var email = $(this).find("email").text();
    $("<li></li>").html(email).appendTo("#personaldata ul");
    
    var dob = $(this).find("dateOfBirth").text();
    $("<li></li>").html(dob).appendTo("#personaldata ul");
    
    var gender = $(this).find("gender").text();
    $("<li></li>").html(gender).appendTo("#personaldata ul");
        
}

function addExperience() {
    var company = $(this).find("companyName").text();
    $("<li></li>").html(company).appendTo("#experience ul");
    
    var position = $(this).find("position").text();
    $("<li></li>").html(position).appendTo("#experience ul");
    
    var details = $(this).find("details").text();
    $("<li></li>").html(details).appendTo("#experience ul");
}


function addEducation() {
    var institution = $(this).find("educationalInstitution").text();
    $("<li></li>").html(institution).appendTo("#education ul");
    
    var details = $(this).find("details").text();
    $("<li></li>").html(details).appendTo("#education ul");
}

function addRecord() {
    $("<tr></tr>").appendTo("#searchresults .section-content");
    
    var id = $(this).find("applicantId").text();
    $("<td></td>").html(id).appendTo("tr:last");
    
    var firstName = $(this).find("firstName").text();
    var lastName = $(this).find("lastName").text();
    $("<td></td>").html("<a id=" + id + " href='#details'>" + firstName + " " + lastName + "</a>").appendTo("tr:last");
        
    var position = $(this).find("title");
    $("<td></td>").html(position).appendTo("tr:last");
    
    var dateApplied = $(this).find("dateApplied").text();
    $("<td></td>").html($.format.date(dateApplied, "dd.MM.yyyy")).appendTo("tr:last");
    
    var status = $(this).find("status");
    $("<td></td>").html(status).appendTo("tr:last");
}

function onFormSubmit(e){
    e.preventDefault();
    $(".hidden").hide();
    $(".section-content").empty();
        
    // Build URL
    var url = buildUrl();
    
    // Do GET request
    $.ajax({
	    url: url,
	    type: "GET",
	    dataType: "xml",
    })
    .done(function( xml ) {
        $(xml).find("applicant").each(addRecord);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
    $("#searchresults").show();
};

$( document ).ready(function() {
    /*GOVUK.modules.start();*/
    GOVUK.stickAtTopWhenScrolling.init();
            
    var $buttons = $("label input[type='radio'], label input[type='checkbox']");
    var selectionButtons = new GOVUK.SelectionButtons($buttons);
    
    $(".hidden").hide();
    $("form").submit(onFormSubmit);
    $("#searchresults").on("click", "a", onGetDetails);
    
    $("form input[name=searchmethod]").click(onSearchMethod);
    $("form input[value=id]").trigger("click");

});
