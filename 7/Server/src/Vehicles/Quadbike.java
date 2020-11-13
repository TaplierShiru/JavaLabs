package Vehicles;

import BaseVehicle.*;
import Factory.CreatedVehicle;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.NoSuchModelNameException;

import java.util.ArrayList;
import java.util.Arrays;


public class Quadbike extends BaseVehicle {

    public final static CreatedVehicle NUM_TYPE = CreatedVehicle.QUADBIKE;

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

    private ArrayList<Model> arrayListModel;
    private String mark;

    public Quadbike(){
        this.mark = NON_SET_NAME;
        this.arrayListModel = new ArrayList<>();
    }

    public Quadbike(String mark, int sizeArrayOfModels){
        this.mark = mark;

        if (sizeArrayOfModels > 0) {
            arrayListModel = new ArrayList<>();
            for (int i = 0; i < sizeArrayOfModels; i++) {
                arrayListModel.add(new Model());
            }
        }else{
            arrayListModel = null;
        }
    }


    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public void setMark(String newMark) {
        this.mark = newMark;
    }

    @Override
    public void setModelName(String newModelName, String oldModelName) throws DuplicateModelNameException, NoSuchModelNameException {
        checkModelNameOnDuplicate(newModelName);
        checkModelNameOnExisted(oldModelName);

        arrayListModel.get(foundIndexOfElement(oldModelName)).setModelName(newModelName);
    }

    @Override
    public String[] getModelNames() {
        if (arrayListModel != null) {
            String[] modelNames = new String[arrayListModel.size()];

            for (int i = 0; i < arrayListModel.size(); i++) {
                modelNames[i] = arrayListModel.get(i).getModelName();
            }

            return modelNames;
        }else{
            return null;
        }
    }

    @Override
    public float[] getModelPrices() {
        if (arrayListModel != null) {
            float[] modelPrices = new float[arrayListModel.size()];

            for (int i = 0; i < arrayListModel.size(); i++) {
                modelPrices[i] = arrayListModel.get(i).getPrice();
            }

            return modelPrices;
        }else {
            return null;
        }
    }

    @Override
    public int getModelSize() {
        return arrayListModel.size();
    }

    @Override
    public CreatedVehicle getType() {
        return NUM_TYPE;
    }

    @Override
    public void addNewModel(String modelName, float price) throws DuplicateModelNameException {
        checkPrice(price);

        checkModelNameOnDuplicate(modelName);

        if (arrayListModel != null) {
            arrayListModel.add(new Model(modelName, price));

        }else{
            arrayListModel = new ArrayList<>();
            arrayListModel.add(new Model(modelName, price));

        }

    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        // Found index of which element needs to delete
        int index_delete = foundIndexOfElement(modelName);

        // Delete element if it was found
        if (index_delete != NOT_FOUND_NUMBER){

            arrayListModel.remove(index_delete);
        }else{
            // If `modelName` was not found
            throw new NoSuchModelNameException(modelName);
        }
    }

    @Override
    public float getPriceByModelName(String modelName) throws NoSuchModelNameException {
        float foundPrice = -1.0f;

        int i = 0;
        boolean wasFound = false;

        while (i != arrayListModel.size() && !wasFound){
            if (arrayListModel.get(i).getModelName().equals(modelName)){

                foundPrice = arrayListModel.get(i).getPrice();
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

    @Override
    public void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException {
        checkPrice(newPrice);

        boolean wasFound = false;

        for (int i = 0;i < arrayListModel.size() && !wasFound; i++){
            if (arrayListModel.get(i).getModelName().equals(modelName)){
                arrayListModel.get(i).setPrice(newPrice);
                wasFound = true;
            }
        }

        if (!wasFound){
            // If `modelName` was not found
            throw new NoSuchModelNameException(modelName);
        }
    }

    @Override
    public boolean isExist(String modelName) {
        boolean wasFound = false;
        if (arrayListModel != null) {
            for (int i = 0; i < arrayListModel.size() && !wasFound; i++) {
                if (modelName.equals(arrayListModel.get(i).getModelName())
                ) {
                    wasFound = true;
                }
            }
        }
        return wasFound;
    }

    @Override
    public void clearModels() {
        this.arrayListModel = null;
    }

    private int foundIndexOfElement(String modelName){
        int indexFound = NOT_FOUND_NUMBER;

        if (arrayListModel != null) {
            for (int i = 0; i < arrayListModel.size() && NOT_FOUND_NUMBER == indexFound; i++) {
                if (modelName.equals(arrayListModel.get(i).getModelName())
                ) {
                    indexFound = i;
                }

            }
        }
        return indexFound;
    }
}
