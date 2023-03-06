# nearby-restaurants-selector-api
Query nearby restaurants given a geolocation and distance.

<h2>To do list </h3>
<ol>
    <li>Geolocation API spike</li> 
    <li>Database design</li> 
    <li>Data pipeline</li>
    <li>Container design</li> 
    <li>Endpoint creation</li>
    <li>CRUD</li>
    <li>Request and response structure</li>
    <li>Auth token</li>
    <li>Exceptions handling</li>
    <li>Tests</li>
</ol>

<h2>Coverage area in São Paulo</h2>

<img src="data/area.png" width="800">

<h2>Running locally</h2>

- Build SpringBoot application with gradle

```console
cd app
```

```console
gradlew build
```

```console
cd ..
```


- Install Docker
```console
sudo apt-get install docker.io
sudo apt install docker-compose
```

needed this but dont know why
```console
 systemctl start docker
 systemctl enable docker
```

- Run the following command to create images and run the containers

```console
sudo docker-compose up -d
```

- Check if MySQL is ready for connections

```console
docker logs CONTAINER_ID
```

- Check if MySQL table is available

```console
docker exec -it CONTAINER_ID mysql -p
```

```console
mysql> SELECT * FROM restaurants_geolocation_api.restaurants LIMIT 10;
```

- Check if API status is up

```console
curl 127.0.0.1:8080/api/v1
```
<h3>Expected:</h3>
```json
{"environment":"localhost","status":"up"}
```


