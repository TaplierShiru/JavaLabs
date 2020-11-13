package VehicleExceptions;

public class VehicleCreationException extends Exception {
    public VehicleCreationException() {
        super("Считанный тип Vehicle не был найден!");
    }
}

