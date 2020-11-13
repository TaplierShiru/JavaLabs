package Factory;

import VehicleExceptions.VehicleCreationException;

public enum CreatedVehicle {

    CAR, MOTORBIKE;

    public static CreatedVehicle fromInteger(int inInteger) throws VehicleCreationException {
        switch(inInteger) {
            case 0:
                return CAR;
            case 1:
                return MOTORBIKE;
        }

        throw new VehicleCreationException();
    }

    public static int toInteger(CreatedVehicle type) throws VehicleCreationException {
        switch(type) {
            case CAR:
                return 0;
            case MOTORBIKE:
                return 1;
        }

        throw new VehicleCreationException();
    }
}
