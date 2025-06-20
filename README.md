# Nearby Restaurants Selector API

A Spring Boot REST API that finds nearby restaurants based on geolocation coordinates using the Haversine formula for accurate distance calculations.

## 🚀 Features

- **Geolocation-based restaurant search** using latitude and longitude coordinates
- **Haversine formula** for precise distance calculations
- **Configurable search radius** in meters
- **Limit results** by number of restaurants
- **Docker containerization** with multi-stage builds
- **MySQL database** with pre-loaded restaurant data
- **RESTful API** with proper error handling and validation

## 🏗️ Architecture

### Technology Stack
- **Backend**: Spring Boot 2.7.9 with Java 11
- **Database**: MySQL 5.7 with spatial indexing
- **Build Tool**: Gradle 7.6
- **Containerization**: Docker & Docker Compose
- **API Documentation**: REST endpoints with JSON responses

### Project Structure
```
nearby-restaurants-selector-api/
├── app/                          # Spring Boot application
│   ├── src/main/java/com/api/app/
│   │   ├── controller/           # REST controllers
│   │   ├── service/             # Business logic
│   │   ├── repository/          # Data access layer
│   │   ├── model/               # Entity classes
│   │   └── dto/                 # Data transfer objects
│   ├── src/main/resources/      # Configuration files
│   ├── Dockerfile               # Multi-stage Docker build
│   └── build.gradle             # Gradle dependencies
├── .db/                         # Database initialization
│   ├── Dockerfile               # MySQL custom image
│   └── data.sql                 # Restaurant data
├── docker-compose.yml           # Container orchestration
└── README.md                    # Project documentation
```

## 📊 Database Schema

The application uses a MySQL database with the following restaurant table structure:

```sql
CREATE TABLE `restaurants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `lat` decimal(8,6) NOT NULL,
  `lon` decimal(8,6) NOT NULL,
  `street_name` varchar(70) NOT NULL,
  `neighborhood` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurants_lat_index` (`lat`),
  KEY `restaurants_lon_index` (`lon`),
  KEY `restaurants_lat_lon_index` (`lat`,`lon`)
);
```

## 🛠️ Setup & Installation

### Prerequisites
- Docker and Docker Compose
- Java 11+ (for local development)
- Gradle (for local development)

### Quick Start with Docker

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd nearby-restaurants-selector-api
   ```

2. **Build and start containers**
   ```bash
   docker-compose build
   docker-compose up -d
   ```

3. **Verify services are running**
   ```bash
   # Check API status
   curl http://localhost:8080/api/v1/
   
   # Expected response:
   {"environment":"localhost","status":"up"}
   ```

### Local Development Setup

1. **Build the application**
   ```bash
   cd app
   ./gradlew build
   ```

2. **Start the application**
   ```bash
   ./gradlew bootRun
   ```

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Endpoints

#### 1. Health Check
```http
GET /api/v1/
```

**Response:**
```json
{
  "environment": "localhost",
  "status": "up"
}
```

#### 2. Find Nearby Restaurants
```http
GET /api/v1/restaurants/nearby
```

**Parameters:**
| Parameter    | Required | Type    | Description                                    | Example |
|--------------|----------|---------|------------------------------------------------|---------|
| `lat`        | Yes      | Double  | Latitude of reference point                    | -23.5505 |
| `lon`        | Yes      | Double  | Longitude of reference point                   | -46.6333 |
| `maxDistance`| No       | Double  | Maximum distance in meters                     | 1000     |
| `limit`      | No       | Integer | Maximum number of restaurants (default: 10)   | 5        |

**Example Requests:**

1. **Find 10 nearest restaurants (no distance limit)**
   ```bash
   curl "http://localhost:8080/api/v1/restaurants/nearby?lat=-23.5505&lon=-46.6333"
   ```

2. **Find 5 restaurants within 1000 meters**
   ```bash
   curl "http://localhost:8080/api/v1/restaurants/nearby?lat=-23.5505&lon=-46.6333&maxDistance=1000&limit=5"
   ```

3. **Find 20 nearest restaurants (no distance limit)**
   ```bash
   curl "http://localhost:8080/api/v1/restaurants/nearby?lat=-23.5505&lon=-46.6333&limit=20"
   ```

**Response Format:**
```json
[
  {
    "id": 1,
    "name": "Restaurant Name",
    "lat": -23.5505,
    "lon": -46.6333,
    "streetName": "Street Name",
    "neighborhood": "Neighborhood",
    "distance": 150.5
  }
]
```

**Error Responses:**
- `400 Bad Request`: Invalid coordinates or parameters
- `500 Internal Server Error`: Server-side error

## 🔧 Configuration

### Environment Variables
The application uses the following configuration in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://database:3306/restaurants_geolocation_api
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Environment
ENV=localhost
```

### Docker Configuration
- **Application Port**: 8080
- **Database Port**: 3306
- **Database Name**: restaurants_geolocation_api
- **Database Root Password**: root

## 🧪 Testing

### Manual Testing
```bash
# Test health endpoint
curl http://localhost:8080/api/v1/

# Test nearby restaurants endpoint
curl "http://localhost:8080/api/v1/restaurants/nearby?lat=-23.5505&lon=-46.6333&limit=5"
```

### Database Verification
```bash
# Connect to MySQL container
docker exec -it <container_id> mysql -p

# Check restaurant data
mysql> USE restaurants_geolocation_api;
mysql> SELECT COUNT(*) FROM restaurants;
mysql> SELECT * FROM restaurants LIMIT 5;
```

## 📈 Performance Considerations

- **Spatial Indexing**: Database indexes on latitude and longitude columns
- **Haversine Formula**: Accurate distance calculations using spherical trigonometry
- **Query Optimization**: Native SQL queries with proper indexing
- **Result Limiting**: Configurable limits to prevent excessive data transfer

## 🔒 Security

- **Input Validation**: Coordinate range validation (-90 to 90 for lat, -180 to 180 for lon)
- **Parameter Limits**: Maximum limit of 100 restaurants per request
- **Error Handling**: Proper HTTP status codes and error responses
- **Sensitive Data**: Database credentials excluded from version control

## 🚀 Deployment

### Production Considerations
1. **Environment Variables**: Use environment variables for database credentials
2. **SSL/TLS**: Enable HTTPS for production deployments
3. **Load Balancing**: Consider using a load balancer for high availability
4. **Monitoring**: Implement application monitoring and logging
5. **Backup Strategy**: Regular database backups

### Docker Production Build
```bash
# Build production image
docker build -t nearby-restaurants-api:latest ./app

# Run with environment variables
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/restaurants_geolocation_api \
  -e SPRING_DATASOURCE_USERNAME=your-username \
  -e SPRING_DATASOURCE_PASSWORD=your-password \
  nearby-restaurants-api:latest
```

## 📝 TODO List

- [x] Geolocation API spike
- [x] Database design
- [x] Data pipeline
- [x] Container design
- [x] Endpoint creation
- [ ] CRUD operations
- [x] Request and response structure
- [ ] Authentication token
- [x] Exception handling
- [ ] Comprehensive test suite
- [ ] API documentation with Swagger
- [ ] Rate limiting
- [ ] Caching layer
- [ ] Performance monitoring

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For questions or issues, please open an issue in the repository or contact the development team.

---

**Coverage Area in São Paulo**

<img src="data/area.png" width="800" alt="São Paulo Coverage Area">
