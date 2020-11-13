package Vehicles;

import BaseVehicle.*;
import Factory.CreatedVehicle;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.NoSuchModelNameException;

import java.util.HashMap;


public class Skuter extends BaseVehicle {

    public final static CreatedVehicle NUM_TYPE = CreatedVehicle.SKUTER;

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

    private HashMap<String, Model> hashMapModels;
    private String mark;

    public Skuter(){
        this.mark = NON_SET_NAME;
        this.hashMapModels = new HashMap<>();
    }

    public Skuter(String mark){
        this.mark = mark;
        this.hashMapModels = new HashMap<>();
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

        Model takedModel = hashMapModels.get(oldModelName);
        takedModel.setModelName(newModelName);

        hashMapModels.remove(oldModelName);

        hashMapModels.put(newModelName, takedModel);

    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[hashMapModels.size()];

        int ind = 0;
        for (String key : hashMapModels.keySet()) {
            modelNames[ind] = key;

            ind++;
        }

        return modelNames;
    }

    @Override
    public float[] getModelPrices() {
        float[] modelNames = new float[hashMapModels.size()];

        int ind = 0;
        for (Model singleValue : hashMapModels.values()) {
            modelNames[ind] = singleValue.getPrice();

            ind++;
        }

        return modelNames;
    }

    @Override
    public int getModelSize() {
        return hashMapModels.size();
    }

    @Override
    public CreatedVehicle getType() {
        return NUM_TYPE;
    }

    @Override
    public void addNewModel(String modelName, float price) throws DuplicateModelNameException {
        checkPrice(price);

        checkModelNameOnDuplicate(modelName);

        hashMapModels.put(modelName, new Model(modelName, price));
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        checkModelNameOnExisted(modelName);

        hashMapModels.remove(modelName);
    }

    @Override
    public float getPriceByModelName(String modelName) throws NoSuchModelNameException {
        checkModelNameOnExisted(modelName);

        return hashMapModels.get(modelName).getPrice();
    }

    @Override
    public void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException {
        checkPrice(newPrice);
        checkModelNameOnExisted(modelName);

        hashMapModels.get(modelName).setPrice(newPrice);
    }

    @Override
    public boolean isExist(String modelName) {
        return hashMapModels.containsKey(modelName);
    }

    @Override
    public void clearModels() {
        hashMapModels.clear();
    }
}
