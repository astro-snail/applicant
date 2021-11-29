# Test RESTful Web Application

Displays a fictitious list of job applicants together with their details such as address, phone numbers, education and work experience.

Written in Java + JavaScript.

Database: PostgreSQL.

Uses:
	EclipseLink 2.5.2,
	PostgreSQL JDBC Driver,
	Jersey.
	
Design: GOV.UK Frontend Toolkit (https://github.com/alphagov/govuk_frontend_toolkit)
	
Hosted by Heroku (https://frozen-thicket-70643.herokuapp.com)

## Build

Application is built using Maven and packaged as a *.war archive.

## Database

Application uses PostgreSQL database. A sample database is included in the form of a backup file at:
- ./src/main/resources/db\_backup.backup - custom format (used by Heroku PostgreSQL)
- ./src/main/resources/db\_backup\_plain.backup - plain text format

Sample database contains 4 job applications from 4 applicants and 1 position they apply for.

## Database connection

Application expects the connection details to be stored in the environment variable **DATABASE_URL**. The format is as follows:

**DATABASE_URL** = postgres://&lt;username&gt;:&lt;password&gt;@&lt;host&gt;:&lt;port&gt;/&lt;dbname&gt;

## Resources

Application makes use of the following resources:

- ./rest/resources/applicants - get all applicants

- ./rest/resources/applicants/&lt;id&gt; - get the applicant with ID = &lt;id&gt;

- ./rest/resources/applications/1 - get all applications for position with ID = 1

- ./rest/resources/applications/1/&lt;id&gt; - get an application for position with ID = 1 from an applicant with ID = &lt;id&gt;

- ./rest/resources/applications/1/&lt;firstname&gt;/&lt;lastname&gt; - get an application for position with ID = 1 from an applicant whose first name = &lt;firstname&gt; and whose last name = &lt;lastname&gt;
