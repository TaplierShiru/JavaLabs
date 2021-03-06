import InterfaceCollect.*;
import model_expcetions.*;

public class Motorbike implements Vehicle {

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

    public Motorbike(String mark, int sizeOfArray){
        this.mark = mark;

        size = sizeOfArray;
        Model temp = head;
        // --------
        while (sizeOfArray != 0){
            Model create_new = new Model();
            temp.next = create_new;
            create_new.prev = temp;

            create_new.next = head;
            head.prev = create_new;

            temp = create_new;
            sizeOfArray--;
        }

        temp.next = head;
        head.prev = temp;
    }

    public String getMark(){
        return mark;
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

        if (head.next.equals(head)){
            head.next = newModel;
            head.prev = newModel;

            newModel.next = head;
            newModel.prev = head;
        } else{
            newModel.next = head.next;
            head.next.prev = newModel;

            head.next = newModel;
            newModel.prev = head;

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

    private void checkModelNameOnExisted(String modelName) throws NoSuchModelNameException{
        if (!isExist(modelName)){
            throw new NoSuchModelNameException(modelName);
        }
    }

    private void checkPrice(float price){
        if (!isPriceInBounds(price)){
            throw new ModelPriceOutOfBoundsException(Float.toString(price));
        }
    }

    private void checkModelNameOnDuplicate(String modelName) throws DuplicateModelNameException{
        if (isExist(modelName)){
            throw new DuplicateModelNameException(modelName);
        }
    }

    private boolean isPriceInBounds(float price){
        return Float.isNaN(price) || price >= 0.0f;
    }

    private boolean isExist(String modelName){
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
