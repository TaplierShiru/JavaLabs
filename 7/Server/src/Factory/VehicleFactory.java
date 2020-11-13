package Factory;

import BaseVehicle.*;
import CustomReader.*;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.VehicleCreationException;
import Vehicles.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class VehicleFactory {

    public static Vehicle createVehicle(CreatedVehicle type) throws VehicleCreationException {
        switch (type) {
            case CAR: {
                return new Car();
            }
            case MOTORBIKE: {
                return new Motorbike();
            }
            case QUADBIKE:{
                return new Quadbike();
            }
            case SKUTER:{
                return new Skuter();
            }
            case MOPED:{
                return new Moped();
            }
        }

        throw new VehicleCreationException();
    }

    public static Vehicle VehicleFromChars(String filename) throws DuplicateModelNameException, IOException, VehicleCreationException {

        VehicleReader in = new VehicleReader(filename);

        System.out.println("Enter type of the vehicle");
        printTypes();
        Vehicle newVehicle = createVehicle(CreatedVehicle.fromInteger(in.readInt()));

        newVehicle.createFromChars(in);

        in.close();

        return newVehicle;
    }

    public static Vehicle VehicleFromCharsWithScanner(String filename) throws VehicleCreationException,
            DuplicateModelNameException {
        Scanner in;

        try {
            in = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e){
            in = new Scanner(System.in);
        }

        System.out.println("Enter type of the vehicle");
        printTypes();
        Vehicle newVehicle = createVehicle(CreatedVehicle.fromInteger(in.nextInt()));

        in.nextLine();

        newVehicle.createFromCharsWithScanner(in);
        in.close();

        return newVehicle;
    }

    private static void printTypes() throws VehicleCreationException {
        System.out.println(CreatedVehicle.toInteger(Car.NUM_TYPE) + " - Car");
        System.out.println(CreatedVehicle.toInteger(Motorbike.NUM_TYPE) + " - Motorbike");
        System.out.println(CreatedVehicle.toInteger(Skuter.NUM_TYPE) + " - Skuter");
        System.out.println(CreatedVehicle.toInteger(Quadbike.NUM_TYPE) + " - Quad bike");
        System.out.println(CreatedVehicle.toInteger(Moped.NUM_TYPE) + " - Moped");

    }
}
