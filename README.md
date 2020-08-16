# Get Together: An Event Management System Project

### *Final Project for Promineo Tech Back End Software Development Bootcamp*

Get Together is a RESTful API that allows family and friends to plan and manage events. 
Users can create events, add attendees to events and add tasks for events to manage their to-do lists. 

### Technologies
* Java Version 1.8.0_251
* Apache Maven Version 3.6.3
* Spring Boot 1.2.6
* MySQL Version 8.0.20
* Eclipse Version 4.15.0

### REST API

This app uses HTTP Methods:

* POST
* GET
* PUT
* DELETE

### The application has the following functionality: 

#### Authentication 
1.	User can register 
*     POST: /users/register
2.  User's password is encrypted using hashing and salting
3.	User can login
*     POST: /users/login

#### Users
1. User can get their account information
*     GET: /users/{userId}
2. User can update their account information
*     PUT: /users/{userId}
3. User can close their account
*     DELETE: /users/{userId}
4. User can change their password
*     PUT: /users/password/{userId}

#### Events
1.  Create an event
*     POST: /users/{userId}/events
2.  Update an event
*     PUT: /users/{userId}/events/{id}
3.  Display a list of events
*     GET: /users/{userId}/events
4. Display a list of events by event Id
*     GET: /users/{userId}/events/{id}
5.	Delete an event
*     DELETE: /users/{userId}/events/{id}

#### Tasks
1.  Create a task for an event
*     POST: /events/{id}/tasks
2.  Update a task associated with an event
*     PUT: /events/{id}/tasks/{taskId}
3.  Display a list of tasks associated with an event
*     GET: /events/{id}/tasks
4.	Delete a task associated with an event
*     DELETE: /events/{id}/tasks/{taskId}
5.  Complete a task and receive the date/time the task was completed
*     PUT: /events/{id}/tasks/{taskId}
