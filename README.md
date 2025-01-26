# **Movie Search App**

## **Overview**
Run `docker-compose up` to start the db.

This project has necessary Flyway migration scripts to generate tables and set up data in the database.

---

## **Endpoints**

### **1. Get Popular Movies**
- **URL**: `http://localhost:8080/movies/popular`
- **Method**: `GET`
- **Description**: Returns the top 50 movies with the highest ratings.
- **Headers**:
    - `api_key`: The API key is read from the local system and configured in `application.properties`.

---

### **2. Search Movies**
- **URL**: `http://localhost:8080/movies/search`
- **Method**: `GET`
- **Description**: Search for movies using filters and a query string.
- **Headers**:
    - `api_key`: The API key is read from the local system.
- **Query Parameters**:
    - **Example**:
      ```
      http://localhost:8080/movies/search?filter=rating:6&query=h&filter=releaseDateAfter:1994-09-22
      ```
        - Filters movies with:
            - Rating greater than 6.
            - Titles containing the letter "h".
            - Release date after `1994-09-22`.

---

### **3. Get Movie by ID**
- **URL**: `http://localhost:8080/movies/{id}`
- **Method**: `GET`
- **Description**: Retrieve details of a movie by its ID.
- **Headers**:
    - `api_key`: The API key is read from the local system.
- **Path Parameter**:
    - `{id}`: The unique ID of the movie.
- **Example**:
