package MainClasses;

import java.io.*;
import java.util.Arrays;

import Factory.CreatedVehicle;
import BaseVehicle.*;
import VehicleExceptions.*;


public class Car extends BaseVehicle {

    public final static CreatedVehicle NUM_TYPE = CreatedVehicle.CAR;

    private class Model implements ModelInterface{

        private String modelName;
        private float price;

        public Model(){
            modelName = NON_SET_NAME;
            price = Float.NaN;
        }
        public Model(String newModelName, float newPrice){
            modelName = newModelName;
            price = newPrice;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }

    private Model[] arrayOfModels;
    private String mark;

    public Car(){
        this.mark = null;
        this.arrayOfModels = null;
    }

    public Car(String mark, int sizeArrayOfModels){
        this.mark = mark;
        if (sizeArrayOfModels > 0) {
            arrayOfModels = new Model[sizeArrayOfModels];
            for (int i = 0; i < sizeArrayOfModels; i++) {
                arrayOfModels[i] = new Model();
            }
        }else{
            arrayOfModels = null;
        }
    }

    public String getMark(){
        return mark;
    }

    public CreatedVehicle getType(){
        return this.NUM_TYPE;
    }

    public void setMark(String newMark){
        mark = newMark;
    }

    public void setModelName(String newModelName, String oldModelName) throws DuplicateModelNameException,
            NoSuchModelNameException{

        checkModelNameOnDuplicate(newModelName);
        checkModelNameOnExisted(oldModelName);

        arrayOfModels[foundIndexOfElement(oldModelName)].setModelName(newModelName);

    }

    public String[] getModelNames(){
        if (arrayOfModels != null) {
            String[] modelNames = new String[arrayOfModels.length];

            for (int i = 0; i < arrayOfModels.length; i++) {
                modelNames[i] = arrayOfModels[i].getModelName();
            }

            return modelNames;
        }else{
            return null;
        }
    }

    public float[] getModelPrices(){
        if (arrayOfModels != null) {
            float[] modelPrices = new float[arrayOfModels.length];

            for (int i = 0; i < arrayOfModels.length; i++) {
                modelPrices[i] = arrayOfModels[i].getPrice();
            }

            return modelPrices;
        }else {
            return null;
        }
    }

    public int getModelSize(){
        return arrayOfModels.length;
    }

    public void addNewModel(String modelName, float price) throws DuplicateModelNameException {

        checkPrice(price);

        checkModelNameOnDuplicate(modelName);

        if (arrayOfModels != null) {
            arrayOfModels = Arrays.copyOf(arrayOfModels, arrayOfModels.length + 1);
            arrayOfModels[arrayOfModels.length - 1] = new Model(modelName, price);

        }else{
            arrayOfModels = new Model[1];
            arrayOfModels[0] = new Model(modelName, price);

        }
    }

    public void removeModel(String modelName) throws NoSuchModelNameException{
        // Found index of which element needs to delete
        int index_delete = foundIndexOfElement(modelName);

        // Delete element if it was found
        if (index_delete != NOT_FOUND_NUMBER){

            System.arraycopy(
                    arrayOfModels,
                    index_delete + 1,
                    arrayOfModels,
                    index_delete,
                    arrayOfModels.length - (index_delete + 1)
            );

            arrayOfModels = Arrays.copyOf(arrayOfModels, arrayOfModels.length - 1);

        }else{
            // If `modelName` was not found
            throw new NoSuchModelNameException(modelName);
        }
    }

    public float getPriceByModelName(String modelName) throws NoSuchModelNameException{
        float foundPrice = -1.0f;

        int i = 0;
        boolean wasFound = false;

        while (i != arrayOfModels.length && !wasFound){
            if (arrayOfModels[i].getModelName().equals(modelName)){

                foundPrice = arrayOfModels[i].getPrice();
                wasFound = true;
            }

            i++;
        }

        if (!wasFound){
            // If `modelName` was not found
            throw new NoSuchModelNameException(modelName);
        }

        return foundPrice;
    }

    public void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException {

        checkPrice(newPrice);

        boolean wasFound = false;

        for (int i = 0;i < arrayOfModels.length && !wasFound; i++){
            if (arrayOfModels[i].getModelName().equals(modelName)){
                arrayOfModels[i].setPrice(newPrice);
                wasFound = true;
            }
        }

        if (!wasFound){
            // If `modelName` was not found
            throw new NoSuchModelNameException(modelName);
        }

    }

    public void clearModels(){
        this.arrayOfModels = null;
    }

    public boolean isExist(String modelName){
        boolean wasFound = false;
        if (arrayOfModels != null) {
            for (int i = 0; i < arrayOfModels.length && !wasFound; i++) {
                if (modelName.equals(arrayOfModels[i].getModelName())
                ) {
                    wasFound = true;
                }
            }
        }
        return wasFound;
    }

    private int foundIndexOfElement(String modelName){
        int indexFound = NOT_FOUND_NUMBER;

        if (arrayOfModels != null) {
            for (int i = 0; i < arrayOfModels.length && NOT_FOUND_NUMBER == indexFound; i++) {
                if (modelName.equals(arrayOfModels[i].getModelName())
                ) {
                    indexFound = i;
                }

            }
        }
        return indexFound;
    }

}

