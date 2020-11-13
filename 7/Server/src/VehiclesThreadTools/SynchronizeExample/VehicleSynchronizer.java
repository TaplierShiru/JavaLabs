package VehiclesThreadTools.SynchronizeExample;

import BaseVehicle.Vehicle;

public class VehicleSynchronizer {
    private Vehicle vehicle;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public VehicleSynchronizer(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public float printPrice() throws InterruptedException {
        float val = 0.0f;

        synchronized (lock) {
            float[] prices = this.vehicle.getModelPrices();

            if (!canPrintPrice()) {
                throw new InterruptedException();
            }

            while (!set) {
                lock.wait();
            }

            System.out.println("Print price: " + prices[current]);

            current += 1;
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printModel() throws InterruptedException {
        synchronized (lock) {
            String[] modelNames = this.vehicle.getModelNames();

            if (!canPrintModel()) {
                throw new InterruptedException();
            }

            while (set) {
                lock.wait();
            }

            System.out.println("Print model: " + modelNames[current]);

            set = true;
            lock.notifyAll();
        }
    }

    public boolean canPrintPrice() {
        return current < vehicle.getModelSize();
    }

    public boolean canPrintModel() {
        return (!set && current < vehicle.getModelSize()) || (set && current < vehicle.getModelSize() - 1);
    }

}
