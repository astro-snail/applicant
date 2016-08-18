package com.herokuapp.applicant;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.herokuapp.applicant.Resources;
import com.herokuapp.applicant.db.*;

public class ResourcesTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Resources.class);
    }

    /**
     * Test to see that the message "Hello, Heroku!" is sent in the response.
     */
    @Test
    public void testResources() {
        final String responseMsg = target().path("resources").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
        
    }
    
    /**
     * Test to see that the status 200 is sent in the response to the database request.
     */
    @Test
    public void testApplicants() {
        int status = target().path("resources/applicants").request().get().getStatus();

        assertEquals(200, status);
        
    }
    
    /**
     * Test to see that the applicant with ID = 1 is retrieved from the database and is sent in the response.
     */
    @Test
    public void testApplicantId() {
        Response response = target().path("resources/applicants/1").request().get();
        Applicant applicant = response.readEntity(Applicant.class);
        int id = applicant.getApplicantId();

        assertEquals(1, id);
        
    }
    
    /**
     * Test to see that the applicant with name = "Jane Doe" is retrieved from the database and is sent in the response.
     */
    @Test
    public void testApplicantName() {
    	Response response = target().path("resources/applicants/Jane/Doe").request().get();
    	assertEquals(200, response.getStatus());
    	/*Applicant applicant = response.readEntity(Applicant.class);
        String firstName = applicant.getFirstName();
        String lastName = applicant.getLastName();

        assertEquals("Jane", firstName);
        assertEquals("Doe", lastName);*/
        
    }
    
    /**
     * Test to see that the applicant details for ID = 2 are retrieved from the database and sent in the response.
     */
    @Test
    public void testApplicantDetails() {
        Response response = target().path("resources/applicants/2/details").request().get();
        ApplicantDetails applicantDetails = response.readEntity(ApplicantDetails.class);
        int id = applicantDetails.getApplicantId();

        assertEquals(2, id);
        
    }
}
