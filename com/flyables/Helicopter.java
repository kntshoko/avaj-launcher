package flyables;

import weather.Coordinates;
import weather.Simulator;
import weather.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);

        if (weather.equals("SUN")){
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 10,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 2
            );
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + " This is hot.");
        }
        else if (weather.equals("RAIN"))
           { this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 5,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            );
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + "  It's raining. Better watch out for lightings.");
        }
        else if (weather.equals("FOG"))
            {this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 1,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0
            ); 
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + "  Mr forggy I can't sea");
        }
        else if (weather.equals("SNOW"))
           { this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 12
            );
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + "  My rotor is going to freeze!");
        }
       
        if (this.coordinates.getHeight() == 0)
        {
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}