package Vehicles;

import BaseVehicle.*;
import Factory.CreatedVehicle;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.NoSuchModelNameException;

import java.util.LinkedList;


public class Moped extends BaseVehicle{

    public final static CreatedVehicle NUM_TYPE = CreatedVehicle.MOPED;

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

    private LinkedList<Model> listOfModels;
    private String mark;

    public Moped(){
        listOfModels = new LinkedList<>();
        mark = NON_SET_NAME;
    }

    public Moped(String mark, int sizeArrayOfModel){
        this.mark = mark;
        this.listOfModels = new LinkedList<>();

        for (int i = 0;i < sizeArrayOfModel;i++){
            listOfModels.add(new Model());
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

        Model found = foundElementByName(oldModelName);
        if (found != null){
            found.setModelName(newModelName);
        }

    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[listOfModels.size()];

        int curIndex = 0;

        for (Model singleModel : listOfModels){
            modelNames[curIndex] = singleModel.getModelName();

            curIndex++;
        }

        return modelNames;
    }

    @Override
    public float[] getModelPrices() {
        float[] modelPrices = new float[listOfModels.size()];

        int curIndex = 0;

        for (Model singleModel : listOfModels){
            modelPrices[curIndex] = singleModel.getPrice();

            curIndex++;
        }

        return modelPrices;
    }

    @Override
    public int getModelSize() {
        return listOfModels.size();
    }

    @Override
    public CreatedVehicle getType() {
        return NUM_TYPE;
    }

    @Override
    public void addNewModel(String modelName, float price) throws DuplicateModelNameException {
        checkPrice(price);
        checkModelNameOnDuplicate(modelName);

        listOfModels.add(new Model(modelName, price));
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        checkModelNameOnExisted(modelName);

        listOfModels.remove(foundElementIndex(modelName));
    }

    @Override
    public float getPriceByModelName(String modelName) throws NoSuchModelNameException {
        checkModelNameOnExisted(modelName);

        return foundElementByName(modelName).getPrice();
    }

    @Override
    public void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException {
        checkPrice(newPrice);
        checkModelNameOnExisted(modelName);

        foundElementByName(modelName).setPrice(newPrice);

    }

    @Override
    public boolean isExist(String modelName) {
        return foundElementIndex(modelName) != NOT_FOUND_NUMBER;
    }

    @Override
    public void clearModels() {
        listOfModels = new LinkedList<>();
    }

    private Model foundElementByName(String modelName){
        for (Model singleModel : listOfModels){
            if (singleModel.getModelName().equals(modelName)){
                return singleModel;
            }
        }

        return null;
    }

    private int foundElementIndex(String modelName){
        int i = 0;

        for (Model singleModel : listOfModels){
            if (singleModel.getModelName().equals(modelName)){

                return i;
            }

            i++;
        }

        return NOT_FOUND_NUMBER;
    }
}
