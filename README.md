# CSI 2999 Project
This is a five member group project done for Oakland University's Sophomore Project class. Our team chose to do a campsite reservation website. A working demo can be seen [here](https://csi-2999-project.onrender.com/). Please wait a few minutes after clicking the link for the application to start up. Note that our database pauses access after 7 days of inactivity. If the reservations page produces an error, contact one of our group members so we can unpause it.


## Why Campsite Reservation Software? 
In response to having used ineffective campsite reservation software in the past, our team decided we wanted to form a solution. We decided to make reservation software that was both versatile and modern. To best reach our users, we wanted our UI to be adaptable to both desktop and mobile screens, along with the ability to work on any device with an internet connection. We set out to accomplish this through a full-stack web application that made making campsite reservations quick and headache-free.


## How it Was Made
Our web application utilizes a Java/Spring Boot back-end and an HTML, CSS, and JavaScript front-end. Campsite and reservation data is stored in a remote PostgreSQL database which is accessed through PostgREST, a RESTful API. This allowed us to maintain a secure storage and transfer of customer data. Our template engine of choice was ThymeLeaf, which allowed us to quickly form user-friendly visuals of all campsite information. 
Since our application had to be made in a short seven week time frame, our team used Jira to manage the assignment of tasks to each team member. Scrum methodologies were used to break our project into sprints which aided in keeping us all on track. We were able to successfully complete the project by our projected end date, which was June 24th.


## How to Run
- Supabase
  - Create a Supabase database, and run these queries:<br>
  `create table
    public."Site" (
      site_id bigint generated always as identity,
      name text null,
      description text null,
      picture_name text null,
      full_hookup boolean null,
      water_and_electric boolean null,
      rustic boolean null,
      cost_per_day double precision null,
      constraint Site_pkey primary key (site_id)
    ) tablespace pg_default;`
  `create table
    public."Customer" (
      customer_id bigint generated always as identity,
      created_at timestamp with time zone not null default now(),
      first_name text null,
      last_name text null,
      email text null,
      phone_number bigint null,
      selected_site_id smallint null,
      start_date text null,
      end_date text null,
      agreed_to_terms boolean null,
      start_time time without time zone null,
      end_time time without time zone null,
      constraint Customer_pkey primary key (customer_id)
    ) tablespace pg_default;`
  - Add campsite information to the Site table
- Application
  - In `/src/main/java/resources` create an `application.properties` file
  - Add `supabase.url`, `supabase.apikey`, and `supabase.service_role` to the file, and set their values
  - Install Maven
  - Run `mvn spring-boot:run`
