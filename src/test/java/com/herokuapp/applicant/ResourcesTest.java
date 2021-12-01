package com.herokuapp.applicant;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
     * Test to see that the status 200 is sent in the response.
     */
    @Test
    public void testApplications() {
        int status = target().path("resources/applications/1/Jane/Doe").request().get().getStatus();

        assertEquals(200, status);
    }
    
    /**
     * Test to see that the position ID = 1 is sent in the response.
     */
    @Test
    public void testPositionId() {
        Response response = target().path("resources/positions/1").request().get();
        Position position = response.readEntity(Position.class);
        int id = position.getPositionId();

        assertEquals(1, id);
    }
    
    /**
     * Test to see that the applicant ID = 1 is sent in the response.
     */
    @Test
    public void testApplicantId() {
        Response response = target().path("resources/applicants/1").request().get();
        Applicant applicant = response.readEntity(Applicant.class);
        int id = applicant.getApplicantId();

        assertEquals(1, id);
    }
    
    /**
     * Test to see that the application for position ID = 1 and applicant ID = 1 is sent in the response.
     */
    @Test
    public void testApplicationId() {
    	Response response = target().path("resources/applications/1/1").request().get();
    	com.herokuapp.applicant.db.Application application = response.readEntity(com.herokuapp.applicant.db.Application.class);
    	int positionId = application.getId().getPositionId();
    	int applicantId = application.getId().getApplicantId();
    	
    	assertEquals(1, positionId);
    	assertEquals(1, applicantId);
    }
}
