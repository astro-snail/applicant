package com.herokuapp.applicant;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
     * Test to see that the Applicant entity with applicantId = 1 is retrieved from the database and is sent in the response.
     */
    @Test
    public void testApplicantId() {
        Response response = target().path("resources/applicants/1").request().get();
        Applicant applicant = response.readEntity(Applicant.class);
        int id = applicant.getApplicantId();

        assertEquals(1, id);
        
    }
}
