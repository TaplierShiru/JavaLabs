package MainClasses;

import BaseVehicle.Vehicle;
import Factory.*;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.*;


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

    public static void writeVehicle(Vehicle vehicle, Writer out){
        vehicle.writeInChars(out);
    }

    public static Vehicle readVehicle(String filename) throws DuplicateModelNameException, IOException, VehicleCreationException {

        return VehicleFactory.VehicleFromChars(filename);
    }
}
