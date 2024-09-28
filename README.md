# IRCTC Assignment

## Overview
This project is a Train Ticket Booking System built with Sprinboot, MySQL, and JWT for authentication. It allows users to book seats on trains while ensuring that concurrent bookings are handled correctly. The application follows a modular architecture, separating concerns for better maintainability and scalability.

## Getting Started


### Installation Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/Shravan-Parikh/IRCTC_Booking.git

### Database Setup 
2. **Update Application.properties**
    ```bash
      #db specific properties
    spring.datasource.url=jdbc:mysql://localhost:3306/irctc
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=######
    
    
    #ORM s/w specific properties
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

4. **Run the Project**
    ```bash
    spring :run


### Sample Paramaeters for Testing In Postman
1. **Users: Register**
   Description: Registration Of The User
    ```bash
   curl -X POST http://localhost:8080/api/users/register \
   -H "Content-Type: application/json" \
   -d '{
       "username": "Shravan",
       "password": "Shraan9999999",
       "email": "parikhshravan@gmail.com",
       "role": "Admin"
   }'

2. **Users: Login**
    Description: Login User, Getting the Bearer Token In Reponse
    ```bash
    curl --location 'http://localhost:8080/api/users/login' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=8A380117C36A5F8D7047218E04C2B3B6' \
    --data '{"username":"ShravanDon", "password":"password123"}'

3. **Train: Add Trains**
   Description: Adding Train,Assuming the api key is sent in req body, Admins Only
    ```bash
   curl --location 'http://localhost:8080/api/trains' \
    --header 'API-Key: your_secret_api_key' \
    --header 'Authorization: Bearer <token> ' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=8A380117C36A5F8D7047218E04C2B3B6' \
    --data '{"name":"Express 123", "source":"CityA", "destination":"CityB", "totalSeats":100}'

4. **Train: Get Available Trains**
   Description: Get Available Trains From One Point To Another
   ```bash
   curl --location 'http://localhost:8080/api/trains/availability?source=CityA&destination=CityB' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJhdmFuIiwiaWF0IjoxNzI3NTM0Mjk2LCJleHAiOjE3Mjc1NzAyOTZ9.MpGJFO3VmUPxx6LaPJQ9YXmsMBUbeZ8pVU_nolFYUVg' \
    --header 'Cookie: JSESSIONID=8A380117C36A5F8D7047218E04C2B3B6'

5. **Train: Book Ticket**
   Description: Booking Tickets, Assuming there the user to book multiple tickets.
    ```bash
     curl --location 'http://localhost:8080/api/bookings' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaHJhdmFuRG9uIiwiaWF0IjoxNzI3NTM0NDc1LCJleHAiOjE3Mjc1NzA0NzV9.S_r2mxTrm8E08Zqf7umpm4R_FhnXuhkNkFEp49iAH28' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: JSESSIONID=8A380117C36A5F8D7047218E04C2B3B6' \
    --data '{
        "trainId": 1
    }'

6. **Train: Get Booking Details**
    Description: Get Booking Details On the basis of the Booking Id.
   ```bash
   curl --location 'http://localhost:8080/api/bookings/1' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaHJhdmFuRG9uIiwiaWF0IjoxNzI3NTM0NDc1LCJleHAiOjE3Mjc1NzA0NzV9.S_r2mxTrm8E08Zqf7umpm4R_FhnXuhkNkFEp49iAH28' \
    --header 'Cookie: JSESSIONID=8A380117C36A5F8D7047218E04C2B3B6'
