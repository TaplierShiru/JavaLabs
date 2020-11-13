package BaseVehicle;

import CustomReader.VehicleReader;
import Factory.CreatedVehicle;
import Factory.VehicleFactory;
import MainClasses.Motorbike;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.ModelPriceOutOfBoundsException;
import VehicleExceptions.NoSuchModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.*;

public abstract class BaseVehicle implements Vehicle {

    public final int HASH_CODE_MULTIPLYER = 39;
    public final static String EXIT_FROM_READ_MODELS = "None";

    public void createFromChars(VehicleReader in) throws DuplicateModelNameException {
        System.out.println("Enter mark: ");
        this.setMark(in.readLine());

        System.out.println("Fill models with name and price");
        System.out.println("To exit, write None, otherwise write any word and press enter");

        // Skip number of models
        in.readInt();

        while (!in.readLine().equals("None")){

            System.out.println("Enter model name");
            String newModelName = in.readLine();

            System.out.println("Enter price: ");
            float newPrice = in.readFloat();

            this.addNewModel(newModelName, newPrice);

            System.out.println("Fill another models with name and price or");
            System.out.println("To exit, write None, otherwise write any word/click enter");
        }
    }

    public void writeInChars(Writer out) throws VehicleCreationException {
        PrintWriter stream = new PrintWriter(out);
        stream.print(CreatedVehicle.toInteger(this.getType()) + "\n");
        stream.print(this.getMark() + "\n");
        stream.print(this.getModelSize() + "\n");

        String[] arrNames = this.getModelNames();
        float[] arrPrices = this.getModelPrices();

        for (int i = 0;i < arrNames.length; i++){
            stream.print("New model:" + "\n");
            stream.print(arrNames[i] + "\n");
            stream.print(arrPrices[i] + "\n");

        }

        stream.print(BaseVehicle.EXIT_FROM_READ_MODELS);

        stream.flush();
    }

    public void restoreDataFromBytes(DataInputStream dataStream) throws IOException, DuplicateModelNameException {
        // Length of the mark in bytes
        byte[] bytes = new byte[dataStream.readInt()];
        for (int i = 0; i < bytes.length; i++){
            bytes[i] = dataStream.readByte();
        }

        // Length of the model array
        this.setMark(new String(bytes));
        int newSize = dataStream.readInt();

        for (int i = 0; i < newSize; i++) {
            // Read model name
            bytes = new byte[dataStream.readInt()];
            for (int m = 0; m < bytes.length; m++) {
                bytes[m] = dataStream.readByte();
            }
            // Set model name and price
            this.addNewModel(new String(bytes), dataStream.readFloat());
        }
    }

    public void writeInBytes(OutputStream out) throws IOException, VehicleCreationException {

        DataOutputStream dataStream = new DataOutputStream(out);

        // Save type
        dataStream.writeInt(CreatedVehicle.toInteger(this.getType()));

        // Save mark
        writeBytes(this.getMark().getBytes(), dataStream);

        // Save for array of models
        dataStream.writeInt(this.getModelSize());

        String[] arrNames = this.getModelNames();
        float[] arrPrices = this.getModelPrices();

        for (int i = 0;i < arrNames.length; i++){
            // Write model name
            writeBytes(arrNames[i].getBytes(), dataStream);
            dataStream.writeFloat(arrPrices[i]);

        }

        dataStream.close();
    }

    private void writeBytes(byte[] needWrite, DataOutputStream dataStream) throws IOException {
        // First - write length, Second - value itself
        dataStream.writeInt(needWrite.length);

        for (byte elem : needWrite) {

            dataStream.writeByte(elem);
        }
    }

    @Override
    public int hashCode() {
        int resHashCode = this.getMark().hashCode();
        resHashCode *= this.HASH_CODE_MULTIPLYER;

        resHashCode += this.getModelSize();
        resHashCode *= this.HASH_CODE_MULTIPLYER;

        return resHashCode;
    }


    @Override
    public Object clone(){
        try {
            BaseVehicle newVehicle = (BaseVehicle) super.clone();
            newVehicle.clearModels();
            newVehicle.setModels(this.getModelNames(), this.getModelPrices());
            return newVehicle;

        } catch (CloneNotSupportedException | DuplicateModelNameException ex) {
            System.out.println(ex);

            return null;
        }
    }

    @Override
    public boolean equals(Object obj){

        if (this == obj){
            return true;
        }

        if (obj instanceof Vehicle && obj.hashCode() == this.hashCode()){
            Vehicle inVehicle = (Vehicle)obj;

            if (inVehicle.getModelSize() != this.getModelSize() || !inVehicle.getMark().equals(this.getMark())){
                return false;
            }

            String[] inModelNames = inVehicle.getModelNames();
            String[] compareModelNames = this.getModelNames();

            float[] inPrices = inVehicle.getModelPrices();
            float[] comparePrices = this.getModelPrices();

            for (int i = 0; i < this.getModelSize(); i++){
                if (!inModelNames[i].equals(compareModelNames[i]) || inPrices[i] != comparePrices[i]){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuffer stringBuf = new StringBuffer();

        stringBuf.append("Имя марки: ");
        stringBuf.append(this.getMark());
        stringBuf.append("\n");
        stringBuf.append("Кол-во моделей: ");
        stringBuf.append(this.getModelSize());
        stringBuf.append("\n");

        String[] modelNames = this.getModelNames();
        float[] modelPrices = this.getModelPrices();

        for (int i = 0; i < this.getModelSize(); i++) {
            stringBuf.append("->");
            stringBuf.append(modelNames[i]);
            stringBuf.append(": ");
            stringBuf.append(modelPrices[i]);
            stringBuf.append("\n");
        }

        return stringBuf.toString();
    }

    public void setModels(String[] modelNames, float[] prices) throws DuplicateModelNameException {
        for (int i = 0;i < modelNames.length; i++){
            this.addNewModel(modelNames[i], prices[i]);
        }
    }

    protected void checkModelNameOnExisted(String modelName) throws NoSuchModelNameException {
        if (!isExist(modelName)){
            throw new NoSuchModelNameException(modelName);
        }
    }

    protected void checkPrice(float price){
        if (!isPriceInBounds(price)){
            throw new ModelPriceOutOfBoundsException(Float.toString(price));
        }
    }

    protected void checkModelNameOnDuplicate(String modelName) throws DuplicateModelNameException{
        if (isExist(modelName)){
            throw new DuplicateModelNameException(modelName);
        }
    }

    protected boolean isPriceInBounds(float price){
        return Float.isNaN(price) || price >= 0.0f;
    }

}
