package MainClasses;

import BaseVehicle.Vehicle;
import VehicleExceptions.*;
import Vehicles.*;

import java.awt.desktop.SystemSleepEvent;
import java.lang.reflect.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException,
            NoSuchModelNameException, IOException, ClassNotFoundException,
            VehicleCreationException, NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        main_lab5(args);
    }


    private static void main_lab5(String[] args) throws ClassNotFoundException, DuplicateModelNameException,
            NoSuchModelNameException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, VehicleCreationException, IOException {
        System.out.println("Hello world");

        // Number 1

        // Class    method                  method      name Mark   Num Models  Name of model   Price
        // Car      setPriceByModelName     toString    SuperMark   4           None            1.2
        /*
        if (args.length == 7) {
            try {
                Class<?> newVehicleFromARGS = Class.forName("Vehicles." + args[0]);
                Constructor<?> constructor = newVehicleFromARGS.getConstructor(String.class, Integer.TYPE);
                // Set price
                Method methodSetPrice = newVehicleFromARGS.getMethod(args[1], Float.TYPE, String.class);
                // To string
                Method methodToString = newVehicleFromARGS.getMethod(args[2]);
                // Create vehicle
                Object obj = constructor.newInstance(args[3], Integer.parseInt(args[4]));

                methodSetPrice.invoke(obj, Float.parseFloat(args[6]), args[5]);
                System.out.println(methodToString.invoke(obj));

            } catch (InstantiationException e) {
                System.out.println("Ошибка создания модели");
                System.out.println(e);
            } catch (NoSuchMethodException e) {
                System.out.println("Метод не был найден");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("Доступ к приватному полю");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Ошибка");
                e.printStackTrace();
            }

        }
        */

        String first = "Meow first";
        String second = "Meow second";

        // Number 2

        /*
        Vehicle catVehicle = new Motorbike("CatMachine", 3);

        catVehicle.setModelName(first, catVehicle.NON_SET_NAME);
        catVehicle.setPriceByModelName(100.0f, first);

        catVehicle.setModelName(second, catVehicle.NON_SET_NAME);
        catVehicle.setPriceByModelName(10.0f, second);

        //catVehicle.addNewModel(second, 2.0f);

        catVehicle.removeModel(catVehicle.NON_SET_NAME);
        System.out.println(catVehicle);


        Vehicle newVehicle = VehicleAdditionalTool.createVehicleByName("MarkADD", 2, catVehicle);
        System.out.println("Print vehicle by example");
        System.out.println(newVehicle);
        System.out.println(newVehicle.getClass());
        */


        // Number 3
        /*
        Vehicle newSkuter = new Skuter("SuperMark!!!");

        newSkuter.addNewModel(first, 1.0f);
        newSkuter.addNewModel(second, 22.0f);

        System.out.println(newSkuter);
        */

        // Number 4
        /*
        Vehicle quadBike = new Quadbike("SuperBike_12!!!", 0);

        quadBike.addNewModel(first, 10.0f);
        quadBike.addNewModel(second, 5.0f);

        System.out.println(quadBike);
        */

        // Number 5

        Vehicle moped = new Moped("SuperMoped", 0);

        moped.addNewModel(first, 1.0f);
        moped.addNewModel(second, 22.0f);

        System.out.println(moped);

        // Number 6

        //System.out.println("Avg: " + VehicleAdditionalTool.averagePrice(moped, quadBike));

        // Number 7
        // Output
        /*
        String saveToFileName = "saved.txt";
        VehicleAdditionalTool.writeVehicle(moped, new FileWriter(saveToFileName));//new OutputStreamWriter(System.out));

        Vehicle createdVehicleFromTXT = VehicleAdditionalTool.readVehicleWithScanner(saveToFileName);
        System.out.print("\n-------------------------------------------------\n");

        System.out.println(createdVehicleFromTXT);
        */
    }

    private static void main_lab4() throws DuplicateModelNameException, NoSuchModelNameException {
        System.out.println("Hello world");

        String first = "Meow first";
        String second = "Meow second";
        String third = "Meow third";
        String four = "Meow four";

        Vehicle newVehicle = new Motorbike("CatMachine", 3);

        newVehicle.setModelName(first, newVehicle.NON_SET_NAME);
        newVehicle.setPriceByModelName(100.0f, first);

        newVehicle.setModelName(second, newVehicle.NON_SET_NAME);
        newVehicle.setPriceByModelName(10.0f, second);

        newVehicle.setModelName(third, newVehicle.NON_SET_NAME);
        newVehicle.setPriceByModelName(1.0f, third);

        System.out.println(newVehicle);

        Vehicle superVehicle = (Vehicle) newVehicle.clone();

        System.out.println("Object are equal (after clone)? ");
        System.out.println(superVehicle.equals(newVehicle));

        superVehicle.removeModel(superVehicle.getModelNames()[2]);

        System.out.println(newVehicle);
        System.out.println(superVehicle);

        System.out.println("Object are equal (after change cloned)? ");
        System.out.println(superVehicle.equals(newVehicle));
    }

    private static void main_lab3() throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, VehicleCreationException {
        System.out.println("Hello world");

        String first = "Meow first";
        String second = "Meow second";
        String third = "Meow third";
        String four = "Meow four";


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
        /*
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

        */
        // Chars
        String saveToFileName = "savedVehicle.txt";
        VehicleAdditionalTool.writeVehicle(classMotorbike, new FileWriter(saveToFileName));
        Vehicle newVehicle = VehicleAdditionalTool.readVehicle(saveToFileName);

        System.out.println("Read from txt/console");
        System.out.println(newVehicle);

        /*
        String filename = "data.obj";
        // Serialize. Write
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filename));
        objOut.writeObject(newVehicle);
        objOut.close();
        // Read
        FileInputStream inFileObj = new FileInputStream(filename);
        ObjectInputStream inObj = new ObjectInputStream(inFileObj);
        Vehicle newVehicleFromObj = (Vehicle)inObj.readObject();
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

        Vehicle vehicle = new Motorbike("CatMachine", 4);

        vehicle.setModelName(first, vehicle.NON_SET_NAME);
        vehicle.setPriceByModelName(100.0f, first);

        // THrow. Duplicate
        //vehicle.setModelName(first, first);
        //vehicle.setPriceByModelName(21, first);

        vehicle.setModelName(second, vehicle.NON_SET_NAME);
        vehicle.setPriceByModelName(10.0f, second);

        vehicle.setModelName(third, vehicle.NON_SET_NAME);
        vehicle.setPriceByModelName(23.0f, third);

        vehicle.setModelName(four, vehicle.NON_SET_NAME);
        vehicle.setPriceByModelName(131231.0f, four);

        String[] modelNames = vehicle.getModelNames();
        float[] modelPrices = vehicle.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        // Throw. Remove model which does not exist
        //vehicle.removeModel("SUPER meow 999999");

        // Remove second
        vehicle.removeModel(first);
        System.out.println("After remove " + second);
        System.out.println();

        modelNames = vehicle.getModelNames();
        modelPrices = vehicle.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        // Throw. Set new model with wrong price
        //System.out.println("Set cheaper model.");
        //vehicle.addNewModel("bEST MEOW", -2.0f);

        // Set new model
        System.out.println("Set new model.");
        vehicle.addNewModel(four, 10000000000.0f);

        modelNames = vehicle.getModelNames();
        modelPrices = vehicle.getModelPrices();

        for (int i = 0; i < modelNames.length; i++){
            System.out.println("Model name: " + modelNames[i] + ", price: " + modelPrices[i]);
        }
        System.out.println();

        System.out.println("Avg price: " + VehicleAdditionalTool.calculateAvgOfPrices(vehicle));
        System.out.println("Show model names:");
        VehicleAdditionalTool.showModelNames(vehicle);
        System.out.println("Show model prices");
        VehicleAdditionalTool.showModelPrices(vehicle);
    }
}
