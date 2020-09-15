package flyables;

import weather.Coordinates;
import weather.Simulator;
import weather.WeatherTower;

public class Baloon  extends Aircraft implements Flyable  {

    private WeatherTower weatherTower;
    Baloon(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(this.coordinates);

        if (weather.equals("SUN")){
             coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 4
            );
            
        Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + "hotty");
        }
           
        else if (weather.equals("RAIN")){
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 5
            );
            
        Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + "rainny");
        }
            
        else if (weather.equals("FOG")){
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 3
            );
            
        Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + "foggy");
        }
            
        else if (weather.equals("SNOW")){
             this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 0,
                    coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 15
            );
            Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + "sonwy");
        }
           
        if (this.coordinates.getHeight() == 0)
        {
            Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
}

    public void registerTower(WeatherTower weatherTower)
    {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }

}
