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
                    sp[2] = sp[2].trim();
                    for (char m : sp[2].toCharArray()) {
                        if(!Character.isDigit(m)){
                            System.out.println("input at line " + c + " : value at comlun "+3+" is not a number");
                            return;
                        }                        
                    }
                    sp[3] = sp[3].trim();
                    for (char m : sp[3].toCharArray()) {
                        if(!Character.isDigit(m)){
                            System.out.println("input at line " + c + " : value at comlun "+4+" is not a number");
                            return;
                        }                        
                    }
                    sp[4] = sp[4].trim();
                    for (char m : sp[4].toCharArray()) {
                        if(!Character.isDigit(m)){
                            System.out.println("input at line " + c + " : value at comlun "+5+" is not a number");
                            return;
                        }                        
                    }

                    aircraftFactory.newAircraft(
                                sp[0],
                                sp[1],
                                Integer.parseInt(sp[2]),
                                Integer.parseInt(sp[3]),
                                Integer.parseInt(sp[4])
                        ).registerTower(weatherTower);
                }else
                if (c == 1 && sp.length == 1){
                    j = j.trim();
                    for (char m : j.toCharArray()) {
                        if(!Character.isDigit(m)){
                            System.out.println("input at line " + c + " is not a number");
                            return;
                        }                        
                    }
                    l =  Integer.parseInt(j);
                }
                else  {
                    System.out.println("input at line " + c + " does not have 5 columes, it has "+ sp.length);
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
