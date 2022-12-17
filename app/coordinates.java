import java.lang.Math;


class Coordinate{
    int latitude;
    int longitude;

    Coordinate( int latitude, int longitude ){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    double getLatitude(){
        return this.latitude;
    }

    double getLongitude(){
        return this.longitute;
    }

    double distance( Coordinate other ){
        return Math.sqrt( (other.latitude-this.latitude)*(other.longitude-this.longitude) );
    }

}




