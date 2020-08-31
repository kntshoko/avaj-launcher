package com.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.flyable.AircraftFactory;


public class Simulator {

    public static PrintWriter text;
    public static int l;

    public static void main(String[] args)
    {
        if (args.length < 1)
            return;
        String filename = args[0];

        File simulationFile = new File("simulation.txt");
        try {
            text = new PrintWriter(simulationFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
       
        if (simulationFile.exists() && !simulationFile.isDirectory())
        text.print("");

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        
        try {
            FileInputStream fstream = new FileInputStream(filename);
            BufferedReader br  = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            int line = 1;
            String[] splitted;

            while ((strLine = br.readLine()) != null)
            {
                if (line == 1)
                   
                       l = Integer.parseInt(strLine);
                        if (l < 0)
                        {
                            System.out.println("first line must be a posetive number");
                            return;
                        }
                else
                {
                    splitted = strLine.split(" ");
                    if (splitted.length == 1 && splitted[0].isEmpty()) 
                        continue;
                    if (splitted.length != 5) 
                        throw new Exception( "there must be 5 parameters.");
                        try {
                            aircraftFactory.newAircraft(
                                splitted[0],
                                splitted[1],
                                Integer.parseInt(splitted[2]),
                                Integer.parseInt(splitted[3]),
                                Integer.parseInt(splitted[4])
                        ).registerTower(weatherTower);
                            
                        } catch (Exception e) {
                            System.out.println("parameter 3 to 5 must be integers.");
                            return;
                        }
                }
                line++;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (l > 0)
        {
            weatherTower.changeWeather();
           l--;
        }

        text.close();
    }
}
