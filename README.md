#Test RESTful Web Application

Displays a fictitious list of job applicants together with their details such as address, phone numbers, education and work experience.

Written in Java + JavaScript.

Database: PostgreSQL.

Uses:
	EclipseLink 2.5.2,
	PostgreSQL JDBC Driver,
	Jersey.
	
Design: GOV.UK Frontend Toolkit (https://github.com/alphagov/govuk_frontend_toolkit)
	
Hosted by Heroku (https://frozen-thicket-70643.herokuapp.com)

##Build

Application is built using Maven and packaged as a *.war archive.

##Database

Application uses PostgreSQL database. A sample database is included at in the form of a backup file ./src/main/resources/db_backup.backup.

Sample database contains 2 applications from 2 applicants:

**1	Jane Doe**

**2	John Smith**

Sample database contains 1 position:

**1	Java Developer**

##Database connection

Application expects the connection details to be stored in the environment variable **DATABASE_URL**. The format is as follows:

DATABASE_URL = postgres://*username*:*password*@*host*:*port*/*dbname*

##Resources

Application makes use of the following resources:

*./rest/resources/applicants* - get all applicants

*./rest/resources/applicants/***id** - get applicant with ID = **id**

*./rest/resources/applications/1* - get all applications for position with ID = 1

*./rest/resources/applications/1/***id** - get application for position 1 from candidate **id**

*./rest/resources/applications/1/***firstname**/**lastname** - get application for position 1 from candidate **firstname** **lastname**









