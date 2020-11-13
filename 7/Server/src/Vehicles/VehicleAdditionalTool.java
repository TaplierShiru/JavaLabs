package Vehicles;

import BaseVehicle.Vehicle;
import Factory.*;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class VehicleAdditionalTool {

    public static float calculateAvgOfPrices(Vehicle certainVehicle){
        float[] prices = certainVehicle.getModelPrices();

        float totalPrice = 0.0f;

        for (float elem : prices){
            totalPrice += elem;
        }

        return totalPrice / prices.length;
    }

    public static void showModelNames(Vehicle certainVehicle){
        String[] names = certainVehicle.getModelNames();

        for (String elem : names){
            System.out.println(elem);
        }
    }

    public static void showModelPrices(Vehicle certainVehicle){
        float[] prices = certainVehicle.getModelPrices();

        for (float elem : prices){
            System.out.println(elem);
        }
    }

    public static void outputVehicle(Vehicle vehicle, OutputStream out) throws IOException, VehicleCreationException {
        vehicle.writeInBytes(out);
    }

    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException, VehicleCreationException {

        DataInputStream dataStream = new DataInputStream(in);

        int type = dataStream.readInt();

        Vehicle newVehicle = VehicleFactory.createVehicle(CreatedVehicle.fromInteger(type));

        newVehicle.restoreDataFromBytes(dataStream);

        dataStream.close();

        return newVehicle;
    }

    public static void writeVehicle(Vehicle vehicle, Writer out) throws VehicleCreationException {
        vehicle.writeInChars(out);
    }

    public static Vehicle readVehicle(String filename) throws DuplicateModelNameException,
            IOException, VehicleCreationException {

        return VehicleFactory.VehicleFromChars(filename);
    }

    public static Vehicle readVehicleWithScanner(String filename) throws DuplicateModelNameException,
            VehicleCreationException {
        return VehicleFactory.VehicleFromCharsWithScanner(filename);
    }

    public static Vehicle createVehicleByName(String markName, int modelSize, Vehicle classExample) {
        try{

            Class<?> newClass = classExample.getClass();
            Constructor<?> classConstructor = newClass.getConstructor(String.class, Integer.TYPE);
            return (Vehicle)classConstructor.newInstance(markName, modelSize);

        } catch (Exception e){

            return null;
        }

    }

    public static float averagePrice(Vehicle... inputVehicles) {
        float avg = 0.0f;

        for (Vehicle inputVehicle : inputVehicles) {
            int size = 0;
            float sum = 0.0f;

            float[] prices = inputVehicle.getModelPrices();

            for (float price : prices) {
                if (!Float.isNaN(price)) {
                    sum += price;
                    size++;
                }
            }
            avg += (sum / size);
        }
        return avg / inputVehicles.length;
    }


}
