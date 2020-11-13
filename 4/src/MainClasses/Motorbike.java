package MainClasses;

import Factory.CreatedVehicle;
import BaseVehicle.*;
import VehicleExceptions.*;


public class Motorbike extends BaseVehicle {

    public final static CreatedVehicle NUM_TYPE = CreatedVehicle.MOTORBIKE;

    private class Model implements ModelInterface{
        private String modelName;
        private float price;

        Model prev = null;
        Model next = null;

        public Model(){
            modelName = NON_SET_NAME;
            price = Float.NaN;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }
    }
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }
    private int size = 0;
    private String mark;

    public Motorbike() {
        this.mark = null;
    }

    public Motorbike(String mark, int sizeOfArray){
        this.mark = mark;

        size = sizeOfArray;
        // --------
        while (sizeOfArray != 0){
            Model create_new = new Model();
            head.prev.next = create_new;
            create_new.prev = head.prev;

            create_new.next = head;
            head.prev = create_new;
            sizeOfArray--;
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

        foundElementByName(oldModelName).setModelName(newModelName);

    }

    public String[] getModelNames(){
        String[] modelNames = new String[size];

        int curIndex = 0;

        Model temp = head.next;
        while (!temp.equals(head)){
            modelNames[curIndex] = temp.getModelName();

            curIndex++;
            temp = temp.next;
        }

        return modelNames;
    }

    public float[] getModelPrices(){
        float[] modelPrices = new float[size];

        int curIndex = 0;

        Model temp = head.next;
        while (!temp.equals(head)){
            modelPrices[curIndex] = temp.getPrice();

            curIndex++;
            temp = temp.next;
        }

        return modelPrices;
    }

    public int getModelSize(){
        return size;
    }

    public void addNewModel(String modelName, float price) throws DuplicateModelNameException {

        checkPrice(price);
        checkModelNameOnDuplicate(modelName);

        Model newModel = new Model();
        newModel.setModelName(modelName);
        newModel.setPrice(price);

        if (head.next == head){
            head.next = newModel;
            head.prev = newModel;

            newModel.next = head;
            newModel.prev = head;
        } else{
            newModel.prev = head.prev;
            head.prev.next = newModel;

            head.prev = newModel;
            newModel.next = head;
        }

        size++;
    }

    public void removeModel(String modelName) throws NoSuchModelNameException{

        checkModelNameOnExisted(modelName);

        boolean wasFound = false;

        Model temp = head.next;
        while (!temp.equals(head) && !wasFound){
            if (temp.getModelName().equals(modelName)) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;

                wasFound = true;
            }

            temp = temp.next;
        }

        size--;

    }

    public float getPriceByModelName(String modelName) throws NoSuchModelNameException{

        checkModelNameOnExisted(modelName);

        float foundPrice = -1.0f;

        Model temp = head.next;
        boolean wasFound = false;

        while (!temp.equals(head) && !wasFound) {
            if (temp.getModelName().equals(modelName)){
                foundPrice = temp.getPrice();
                wasFound = true;
            }

            temp = temp.next;
        }

        return foundPrice;
    }

    public void setPriceByModelName(float newPrice, String modelName) throws NoSuchModelNameException {

        checkPrice(newPrice);
        checkModelNameOnExisted(modelName);

        Model temp = head.next;
        boolean wasFound = false;

        while (!temp.equals(head) && !wasFound) {
            if (temp.getModelName().equals(modelName)){
                temp.setPrice(newPrice);
                wasFound = true;
            }

            temp = temp.next;
        }

    }

    public void clearModels(){
        this.head = new Model();
        this.head.next = head;
        this.head.prev = head;

        this.size = 0;
    }

    public boolean isExist(String modelName){
        boolean wasFound = false;

        Model temp = head.next;

        while (!temp.equals(head) && !wasFound){
            if (temp.getModelName().equals(modelName)){
                wasFound = true;
            }

            temp = temp.next;
        }

        return wasFound;
    }

    private Model foundElementByName(String modelName){
        Model found = null;
        boolean wasFound = false;

        Model temp = head.next;

        while (!temp.equals(head) && !wasFound){
            if (temp.getModelName().equals(modelName)){
                wasFound = true;
                found = temp;
            }

            temp = temp.next;
        }

        return found;
    }

}
