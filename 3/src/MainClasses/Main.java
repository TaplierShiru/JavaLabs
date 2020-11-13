package MainClasses;

import BaseVehicle.Vehicle;
import VehicleExceptions.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, IOException, ClassNotFoundException, VehicleCreationException {
        main_lab3();
    }

    private static void main_lab3() throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, VehicleCreationException {
        System.out.println("Hello world");

        String first = "Meow first";
        String second = "Meow second";
        String third = "Meow third";
        String four = "Meow four";

        // Test q2

        Vehicle classMotorbike = new Motorbike("CatMachine", 4);

        classMotorbike.setModelName(first, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(100.0f, first);

        classMotorbike.setModelName(second, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(10.0f, second);

        classMotorbike.setModelName(third, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(23.0f, third);

        classMotorbike.setModelName(four, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(131231.0f, four);

        String[] modelNames = classMotorbike.getModelNames();
        float[] modelPrices = classMotorbike.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        // BYtes

        FileOutputStream out = new FileOutputStream("test.bytes");
        VehicleAdditionalTool.outputVehicle(classMotorbike, out);
        out.close();

        FileInputStream in = new FileInputStream("test.bytes");
        Vehicle newVich = VehicleAdditionalTool.inputVehicle(in);
        in.close();

        System.out.println("After restore!");
        modelNames = newVich.getModelNames();
        modelPrices = newVich.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();


        // Chars
        /*
        Vehicle newVehicle = VehicleAdditionalTool.readVehicle("s");
        VehicleAdditionalTool.writeVehicle(newVehicle, new OutputStreamWriter(System.out));
        */
        /*
        String filename = "data.obj";
        // Serialize. Write
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename));
        objOut.writeObject(newVehicle);
        objOut.close();
        // Read
        FileInputStream inFileObj = new FileInputStream(filename);
        ObjectInputStream inObj = new ObjectInputStream(inFileObj);
        Vehicle newVehicleFromObj = (Motorbike)inObj.readObject();
        inFileObj.close();

        System.out.println("Loaded from obj: ");
        VehicleAdditionalTool.showModelNames(newVehicleFromObj);
        VehicleAdditionalTool.showModelPrices(newVehicleFromObj);
        */
    }

    private static void main_lab2() throws DuplicateModelNameException, NoSuchModelNameException {
        System.out.println("Hello world");

        String first = "Meow first";
        String second = "Meow second";
        String third = "Meow third";
        String four = "Meow four";

        // Test q2

        Vehicle classMotorbike = new Motorbike("CatMachine", 4);

        classMotorbike.setModelName(first, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(100.0f, first);

        // THrow. Duplicate
        //classMotorbike.setModelName(first, first);
        //classMotorbike.setPriceByModelName(21, first);

        classMotorbike.setModelName(second, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(10.0f, second);

        classMotorbike.setModelName(third, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(23.0f, third);

        classMotorbike.setModelName(four, classMotorbike.NON_SET_NAME);
        classMotorbike.setPriceByModelName(131231.0f, four);

        String[] modelNames = classMotorbike.getModelNames();
        float[] modelPrices = classMotorbike.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        // Throw. Remove model which does not exist
        //classMotorbike.removeModel("SUPER meow 999999");

        // Remove second
        classMotorbike.removeModel(first);
        System.out.println("After remove " + second);
        System.out.println();

        modelNames = classMotorbike.getModelNames();
        modelPrices = classMotorbike.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        // Throw. Set new model with wrong price
        //System.out.println("Set cheaper model.");
        //classMotorbike.addNewModel("bEST MEOW", -2.0f);

        // Set new model
        System.out.println("Set new model.");
        classMotorbike.addNewModel(four, 10000000000.0f);

        modelNames = classMotorbike.getModelNames();
        modelPrices = classMotorbike.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        System.out.println("Avg price: " + VehicleAdditionalTool.calculateAvgOfPrices(classMotorbike));
        System.out.println("Show model names:");
        VehicleAdditionalTool.showModelNames(classMotorbike);
        System.out.println("Show model prices");
        VehicleAdditionalTool.showModelPrices(classMotorbike);
    }
}
