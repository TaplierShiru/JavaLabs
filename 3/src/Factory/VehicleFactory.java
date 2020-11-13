package Factory;

import MainClasses.*;
import BaseVehicle.*;
import CustomReader.*;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.IOException;


public class VehicleFactory {

    public static Vehicle createVehicle(CreatedVehicle type){
        Vehicle newVehicle = null;

        switch (type) {
            case CAR: {
                newVehicle = new Car();
                break;
            }
            case MOTORBIKE: {
                newVehicle = new Motorbike();
                break;
            }
        }

        return newVehicle;
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
