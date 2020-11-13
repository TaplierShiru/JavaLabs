package VehicleExceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public String typePrice;

    public ModelPriceOutOfBoundsException(String name) {
        super("Цена вида \"" + name + "\" является некорректной");
        typePrice = name;
    }
}
