/**
 * This script performs GET requests to retrieve the data in XML format from the server.
 * The data is being parsed and used to build dynamic content.
 */

// Base URLs
var urlList = "rest/resources/applications/1"; // All applications for position with ID = 1
var urlDetails = "rest/resources/applicants";  // All applicants

// Toggle form search fields
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

// Build URL according to the search criteria
function buildUrl() {
    var url = urlList;
    
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

// Add a single field in the details section
function addField(sel, name, value) {
    $(sel).append("<div class='grid-row'></div>");
    sel = sel + " .grid-row:last";
    $(sel).append("<div class='column-third field-name'>" + name + "</div>");
    $(sel).append("<div class='column-two-thirds field-value'>" + value + "</div>");
}

// Populate Personal Data section
function addPersonalData() {
    var sel = "#personaldata .section-content";
    $(sel).append("<div class='sub-section'></div>");
    sel = sel + " .sub-section:last";
    
    var firstName = $(this).find("firstName").text();
    var lastName = $(this).find("lastName").text();
    addField(sel, "Full name:", firstName + " " + lastName);
    
    var address = $(this).find("address").text();
    addField(sel, "Address:", address);
    
    var mobile = $(this).find("mobile").text();
    addField(sel, "Mobile:", mobile);
    
    var landline = $(this).find("landline").text();
    addField(sel, "Landline:", landline);
    
    var email = $(this).find("email").text();
    addField(sel, "E-Mail:", email);
    
    var dob = $.format.date($(this).find("dateOfBirth").text(), "dd.MM.yyyy");
    addField(sel, "Date of birth", dob);
}

// Return "Now" when Date to is empty
function getDateTo(text) {
    if (text === '') return "Now"
    else return text;
}

// Populate Experience section
function addExperience() {
    var sel = "#experience .section-content";
    $(sel).append("<div class='sub-section'></div>");
    sel = sel + " .sub-section:last";
    
    var dateFrom = $.format.date($(this).find("dateFrom").text(), "dd.MM.yyyy");
    var dateTo = getDateTo($.format.date($(this).find("dateTo").text(), "dd.MM.yyyy"));
    addField(sel, "Dates:", dateFrom + " - " + dateTo);
        
    var company = $(this).find("companyName").text();
    addField(sel, "Company name:", company);
    
    var position = $(this).find("position").text();
    addField(sel, "Position:", position);
        
    var details = $(this).find("details").text();
    addField(sel, "Details:", details);
}

// Populate Education section
function addEducation() {
    var sel = "#education .section-content";
    $(sel).append("<div class='sub-section'></div>");
    sel = sel + " .sub-section:last";
    
    var dateFrom = $.format.date($(this).find("dateFrom").text(), "dd.MM.yyyy");
    var dateTo = getDateTo($.format.date($(this).find("dateTo").text(), "dd.MM.yyyy"));
    addField(sel, "Dates:", dateFrom + " - " + dateTo);
        
    var school = $(this).find("educationalInstitution").text();
    addField(sel, "School:", school);
    
    var details = $(this).find("details").text();
    addField(sel, "Details:", details);
}

// Add a single cell to the search results table
function addCell(sel, value) {
    $(sel).append("<td>" + value + "</td>");
}

// Add a record to the search results table
function addRecord() {
    var sel = "#searchresults .section-content";
    $(sel).append("<tr></tr>");
    sel = sel + " tr:last";
        
    var id = $(this).find("applicantId").text();
    addCell(sel, id);
    
    var fullName = $(this).find("firstName").text() + " " + $(this).find("lastName").text()  
    addCell(sel, "<a id=" + id + " href='#details'>" + fullName + "</a>");
        
    var position = $(this).find("title").text();
    addCell(sel, position);
    
    var dateApplied = $.format.date($(this).find("dateApplied").text(), "dd.MM.yyyy");
    addCell(sel, dateApplied);
    
    var status = $(this).find("status").text();
    addCell(sel, status);
}

// Get list of applications
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
        $(xml).find("application").each(addRecord);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
    $("#searchresults").show();
};

// Get selected applicant's details
function onGetDetails(e) {
    e.preventDefault();
        
    $("#details").hide();
    $("#details .section-content").empty();
       
    // Get current applicant ID
    var id = $(this).attr("id");
    
    // Build URL
    var url = urlDetails + "/" + id;
    
    // Do GET request
    $.ajax({
	   url: url,
	   type: "GET",
	   dataType: "xml",
    })
    .done( function( xml ) {
        $(xml).find("applicant").each(addPersonalData);
        $(xml).find("experience").each(addExperience);
        $(xml).find("education").each(addEducation);
    })
    .fail(function( xhr, status, errorThrown ) {
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
    });
        
    $("#details").show();
}

// Reset search fields
function onFormReset(e) {
    e.preventDefault();
    $("form input[type=search]").val('');    
}

$( document ).ready(function() {
    /*GOVUK.modules.start();*/
    GOVUK.stickAtTopWhenScrolling.init();
            
    var $buttons = $("label input[type='radio'], label input[type='checkbox']");
    var selectionButtons = new GOVUK.SelectionButtons($buttons);
    
    $(".hidden").hide();
    
    // Event handlers
    $("form").on("submit", onFormSubmit);
    $("form").on("reset", onFormReset);
    $("form input[name=searchmethod]").on("click", onSearchMethod);
    $("#searchresults").on("click", "a", onGetDetails);
    
    // Trigger click event for ID radio button
    $("form input[value=id]").trigger("click");
});
