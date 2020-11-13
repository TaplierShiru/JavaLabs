package Factory;

import VehicleExceptions.VehicleCreationException;

public enum CreatedVehicle {

    CAR, MOTORBIKE, SKUTER, QUADBIKE, MOPED;

    public static CreatedVehicle fromInteger(int inInteger) throws VehicleCreationException {
        switch(inInteger) {
            case 0:
                return CAR;
            case 1:
                return MOTORBIKE;
            case 2:
                return SKUTER;
            case 3:
                return QUADBIKE;
            case 4:
                return MOPED;
        }

        throw new VehicleCreationException();
    }

    public static int toInteger(CreatedVehicle type) throws VehicleCreationException {
        switch(type) {
            case CAR:
                return 0;
            case MOTORBIKE:
                return 1;
            case SKUTER:
                return 2;
            case QUADBIKE:
                return 3;
            case MOPED:
                return 4;
        }

        throw new VehicleCreationException();
    }
}
