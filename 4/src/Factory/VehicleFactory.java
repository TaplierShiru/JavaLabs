package Factory;

import MainClasses.*;
import BaseVehicle.*;
import CustomReader.*;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.IOException;


public class VehicleFactory {

    public static Vehicle createVehicle(CreatedVehicle type) throws VehicleCreationException {
        switch (type) {
            case CAR: {
                return new Car();
            }
            case MOTORBIKE: {
                return new Motorbike();
            }
        }

        throw new VehicleCreationException();
    }

    public static Vehicle VehicleFromChars(String filename) throws DuplicateModelNameException, IOException, VehicleCreationException {

        VehicleReader in = new VehicleReader(filename);

        System.out.println("Enter type of the vehicle");
        System.out.println(CreatedVehicle.toInteger(Car.NUM_TYPE) + " - Car");
        System.out.println(CreatedVehicle.toInteger(Motorbike.NUM_TYPE) + " - Motorbike");

        Vehicle newVehicle = createVehicle(CreatedVehicle.fromInteger(in.readInt()));

        newVehicle.createFromChars(in);

        in.close();

        return newVehicle;
    }

}
