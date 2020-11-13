package InterfaceCollect;

import model_expcetions.*;

public interface Vehicle {

    public final int NOT_FOUND_NUMBER = -1;
    public final String NON_SET_NAME = "Name is not setted!";

    String getMark();
    void setMark(String newMark);

    void setModelName(String newModelName, String oldModelName) throws DuplicateModelNameException,
            NoSuchModelNameException;

    String[] getModelNames();
    float[] getModelPrices();

    int getModelSize();

    void addNewModel(String modelName, float price) throws DuplicateModelNameException;
    void removeModel(String modelName) throws NoSuchModelNameException;

    float getPriceByModelName(String modelName) throws NoSuchModelNameException;
    void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException;

}
