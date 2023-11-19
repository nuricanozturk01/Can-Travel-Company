# Can-Travel-Company

### Midterm Project For SE-4458 lesson.

- Database:
    - Development: PostgreSQL
    - Test: H2

- General:
    - Spring boot 3.1.5
    - Java 17
    - Mapstruct
    - Swagger
    - JUnit
    - JWT

### Database Design:

![db_design.png](image%2Fdb_design.png)

### Unit Tests:

##### Read Service:

![unit_tests.png](image%2Funit_tests.png)

##### Authentication Service:

![auth_test.png](image%2Fauth_test.png)

##### Booking Service:

![booking_test.png](image%2Fbooking_test.png)


# Booking Service Documentation

## Assumptions

- The Query Service allows anyone to make queries without authentication.
- The Booking Service functionality includes:
  - Users can only make reservations through a POST request.
  - Admin users can add new houses and perform specific queries.

## Invalid Reservation Scenarios

When making a reservation, the following invalid scenarios are considered:

1. **Exceeding House Capacity:**
   - Users cannot make a reservation with a number of people exceeding the capacity of the house.
   - The system responds with a DataServiceException.

2. **Invalid Username:**
   - Users cannot make a reservation using another user's username or a username that does not exist in the system.
   - The system responds with a DataServiceException.

3. **Invalid house_id:**
   - Users cannot make a reservation with an invalid house_id.
   - The system responds with a DataServiceException.

4. **Invalid Date Intervals:**
   - Reservations are subject to the following date interval conditions:
      - Past time interval: All reservations can be made from the current date onwards.
      - The finish_date cannot be before the start_date.
      - If a reservation has been made for the same house on the same dates before, that date is considered invalid.

## Date Format

The accepted date format for requests is: dd/MM/yyyy (e.g., 25/01/1999).

## Swagger Documentation

Before making a request, please refer to the bottom section of the Swagger documentation, which displays the Data Transfer Objects (DTOs) for accurate parameter information.


## Document:
- Youtube Playlist:
  - https://www.youtube.com/playlist?list=PLB80H1Af3NVp_RBjHQMuUhT2hsV1G-Ak0

- Swagger Documentation:
  - Query Service: http://ec2-3-72-76-120.eu-central-1.compute.amazonaws.com:5151/swagger-ui/index.html
  - Booking Service: http://ec2-3-72-76-120.eu-central-1.compute.amazonaws.com:8080/swagger-ui/index.html
