package com.herokuapp.applicant;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.herokuapp.applicant.db.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("resources")
public class Resources {
	
	private static EntityManagerFactory entityManagerFactory;
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    
	private Map<String, String> getProperties() throws URISyntaxException {
		/** 
		 * Reads environment variable DATABASE_URL and populates persistence properties.
		 * This is required for compatibility with Heroku platform. 
		 * 
		 * DATABASE_URL = postgres://<username>:<password>@<host>:<port>/<dbname>
		 * 
		 * When DATABASE_URL is not found, persistence properties come from "persistence.xml".	   	
	   	 */
		
		Map<String, String> dbProps = new HashMap<String, String>();
						
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String dbUser = dbUri.getUserInfo().split(":")[0];
		String dbPassword = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				
		dbProps.put("javax.persistence.jdbc.user", dbUser);
		dbProps.put("javax.persistence.jdbc.password", dbPassword);
		dbProps.put("javax.persistence.jdbc.url", dbUrl);
		
		return dbProps; 
	}
		
	private EntityManager getEntityManager() {
				
		if (entityManagerFactory == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
			}
			try {
				entityManagerFactory = Persistence.createEntityManagerFactory("applicant", getProperties());
			} catch (URISyntaxException e) {
				entityManagerFactory = Persistence.createEntityManagerFactory("applicant");
			}	
		}
		return entityManagerFactory.createEntityManager();
	}
	
	@GET
    @Produces(MediaType.APPLICATION_XML)
	@Path("/applicants")
	public List<Applicant> getAll() {
		EntityManager entityManager = getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNamedQuery("Applicant.findAll");
	    List<Applicant> resultList = query.getResultList();
	    transaction.commit();
		entityManager.close(); 
		return resultList;
	}
	    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/applicants/{id}")
    public Applicant getById(@PathParam("id") int id) {
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Applicant result = entityManager.find(Applicant.class, id);
		transaction.commit();
		entityManager.close(); 
		return result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/applicants/{firstName}/{lastName}")
    public List<Applicant> findByName(@PathParam("firstName") String firstName,
    						          @PathParam("lastName") String lastName) {
    	
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNamedQuery("Applicant.findByName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		List<Applicant> resultList = query.getResultList();
	    transaction.commit();
		entityManager.close();
		return resultList;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/applicants/{id}/details")
    public ApplicantDetails getDetailsById(@PathParam("id") int id) {
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		ApplicantDetails result = entityManager.find(ApplicantDetails.class, id);
		transaction.commit();
		entityManager.close(); 
		return result;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/applicants/{id}/experience")
    public List<Experience> findExperience(@PathParam("id") int id) {
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNamedQuery("Experience.findAllForApplicant");
		query.setParameter("id", id);
		List<Experience> resultList = query.getResultList();
		transaction.commit();
		entityManager.close(); 
		return resultList;
    }
	    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/applicants/{id}/education")
    public List<Education> findEducation(@PathParam("id") int id) {
    	EntityManager entityManager = getEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNamedQuery("Education.findAllForApplicant");
		query.setParameter("id", id);
		List<Education> resultList = query.getResultList();
		transaction.commit();
		entityManager.close(); 
		return resultList;
    }
}
