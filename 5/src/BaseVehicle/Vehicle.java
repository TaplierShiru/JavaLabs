package BaseVehicle;

import Factory.CreatedVehicle;
import VehicleExceptions.*;
import CustomReader.VehicleReader;

import java.io.*;
import java.util.Scanner;

public interface Vehicle extends Serializable, Cloneable{

    int NOT_FOUND_NUMBER = -1;
    String NON_SET_NAME = "None";

    String getMark();
    void setMark(String newMark);

    void setModelName(String newModelName, String oldModelName) throws DuplicateModelNameException,
            NoSuchModelNameException;

    String[] getModelNames();
    float[] getModelPrices();

    int getModelSize();

    CreatedVehicle getType();

    void addNewModel(String modelName, float price) throws DuplicateModelNameException;
    void removeModel(String modelName) throws NoSuchModelNameException;

    float getPriceByModelName(String modelName) throws NoSuchModelNameException;
    void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException;

    boolean isExist(String modelName);

    void writeInBytes(OutputStream out) throws IOException, VehicleCreationException;
    void restoreDataFromBytes(DataInputStream in) throws IOException, DuplicateModelNameException;

    void writeInChars(Writer out) throws VehicleCreationException;
    void createFromChars(VehicleReader in) throws DuplicateModelNameException;
    void createFromCharsWithScanner(Scanner in) throws DuplicateModelNameException;

    void clearModels();
    void setModels(String[] models, float[] prices) throws DuplicateModelNameException ;

    Object clone();
}
