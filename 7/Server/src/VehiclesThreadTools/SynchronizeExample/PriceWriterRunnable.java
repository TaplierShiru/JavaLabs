package VehiclesThreadTools.SynchronizeExample;

import java.util.logging.Level;
import java.util.logging.Logger;


public class PriceWriterRunnable implements Runnable {

    private VehicleSynchronizer vehicleSynchronizer;

    public PriceWriterRunnable(VehicleSynchronizer vehicleSynchronizer) {
        this.vehicleSynchronizer = vehicleSynchronizer;
    }

    @Override
    public void run() {
        try {
            while (this.vehicleSynchronizer.canPrintPrice()) {
                this.vehicleSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            System.out.println("Error print price " + e);
        }
    }


}
