package weather;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import flyables.AircraftFactory;



public class Simulator {

    public static PrintWriter writer;
    public static int l;

    public static void main(String[] args)
    {
        
        if (args.length < 1){
            System.out.println("eee");
             return;
        }
           
       // String filename = args[0];
        
        File simulationFile = new File("simulation.txt");
        try {
            writer = new PrintWriter(simulationFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
       
        if (simulationFile.exists() && !simulationFile.isDirectory())
        writer.print("");

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
       
        try {
            int c = 1;
           Scanner r = new Scanner( new File(args[0]));
            while (r.hasNextLine()) {
                String j = r.nextLine();
                String[] sp= j.split(" ");
                if(c > 1 && sp.length == 5)
                {

			 aircraftFactory.newAircraft(
                                sp[0],
                                sp[1],
                                Integer.parseInt(sp[2]),
                                Integer.parseInt(sp[3]),
                                Integer.parseInt(sp[4])
                        ).registerTower(weatherTower);
                    System.out.println(j);
                }
                else if (c == 1 && sp.length == 1){
                    l =  Integer.parseInt(j);
                }
                else {
                    System.out.println("input error");
		return;

                }
                c++;
            }
            
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        
      
        WeatherProvider.getProvider(); 
        while (l > 0)
        { 
            weatherTower.changeWeather();
           l--;
        }

        writer.close();
    }
}
