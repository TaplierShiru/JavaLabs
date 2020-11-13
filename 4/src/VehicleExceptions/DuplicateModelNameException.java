package VehicleExceptions;

public class DuplicateModelNameException extends Exception  {
    public String dublicateName;

    public DuplicateModelNameException(String name) {
        super("Атрибут с именем \"" + name + "\" уже существует");
        dublicateName = name;
    }
}
