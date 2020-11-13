package VehiclesThreadTools.ReenLockExample;

import BaseVehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class PriceWriterRunnableReentLock  implements Runnable{

    private Vehicle vehicle;
    private ReentrantLock lock;

    public PriceWriterRunnableReentLock(Vehicle vehicle, ReentrantLock lock) {
        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            this.lock.lock();

            float[] modelPrices = this.vehicle.getModelPrices();

            for (int i = 0;i < modelPrices.length; i++){
                System.out.println(modelPrices[i]);
            }
        } finally {
            this.lock.unlock();
        }
    }

}
