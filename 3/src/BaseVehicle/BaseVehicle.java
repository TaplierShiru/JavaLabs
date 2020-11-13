package BaseVehicle;

import CustomReader.VehicleReader;
import Factory.CreatedVehicle;
import MainClasses.Motorbike;
import VehicleExceptions.DuplicateModelNameException;
import VehicleExceptions.ModelPriceOutOfBoundsException;
import VehicleExceptions.NoSuchModelNameException;
import VehicleExceptions.VehicleCreationException;

import java.io.*;

public abstract class BaseVehicle implements Vehicle {


    public void createFromChars(VehicleReader in) throws DuplicateModelNameException {
        System.out.println("Enter mark: ");
        this.setMark(in.readLine());

        System.out.println("Fill models with name and price");
        System.out.println("To exit, write None, otherwise write any word and press enter");

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

    public void writeInChars(Writer out){
        PrintWriter stream = new PrintWriter(out);

        stream.print(this.getMark() + "\n");
        stream.print(this.getModelSize() + "\n");

        String[] arrNames = this.getModelNames();
        float[] arrPrices = this.getModelPrices();

        for (int i = 0;i < arrNames.length; i++){
            stream.print(arrNames[i] + "\n");
            stream.print(arrPrices[i] + "\n");

        }

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
        dataStream.writeInt(CreatedVehicle.toInteger(Motorbike.NUM_TYPE));

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
