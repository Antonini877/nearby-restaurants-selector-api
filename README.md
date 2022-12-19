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

<h2>Running locally</h2>

- Install Docker

- Run the following command to create images and run the containers

```console
foo@bar:~$ docker compose up -d
```
- Check if MySQL is ready for connections

```console
foo@bar:~$ docker logs CONTAINER_ID
```

- Check if the MySQL table is available

```console
foo@bar:~$ docker exec -it db mysql 
```

```console
mysql> SELECT * FROM restaurants_geolocation_api.restaurants LIMIT 10;
```