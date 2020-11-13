import InterfaceCollect.Vehicle;

public class VehicleAdditionalTool {

    public static float calculateAvgOfPrices(Vehicle certainVehicle){
        float[] prices = certainVehicle.getModelPrices();

        float totalPrice = 0.0f;

        for (float elem : prices){
            totalPrice += elem;
        }

        return totalPrice / prices.length;
    }

    public static void showModelNames(Vehicle certainVehicle){
        String[] names = certainVehicle.getModelNames();

        for (String elem : names){
            System.out.println(elem);
        }
    }

    public static void showModelPrices(Vehicle certainVehicle){
        float[] prices = certainVehicle.getModelPrices();

        for (float elem : prices){
            System.out.println(elem);
        }
    }
}
