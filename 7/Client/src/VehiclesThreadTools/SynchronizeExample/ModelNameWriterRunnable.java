package VehiclesThreadTools.SynchronizeExample;


public class ModelNameWriterRunnable implements Runnable{

    private VehicleSynchronizer vehicleSynchronizer;

    public ModelNameWriterRunnable(VehicleSynchronizer vehicleSynchronizer) {
        this.vehicleSynchronizer = vehicleSynchronizer;
    }

    @Override
    public void run() {
        try {
            while (this.vehicleSynchronizer.canPrintModel()) {
                this.vehicleSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            System.out.println("Error print modelName " + e);
        }
    }

}
