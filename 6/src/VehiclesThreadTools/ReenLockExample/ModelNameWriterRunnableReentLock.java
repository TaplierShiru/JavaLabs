package VehiclesThreadTools.ReenLockExample;

import BaseVehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class ModelNameWriterRunnableReentLock  implements Runnable{

    private Vehicle vehicle;
    private ReentrantLock lock;

    public ModelNameWriterRunnableReentLock(Vehicle vehicle, ReentrantLock lock) {
        this.vehicle = vehicle;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            this.lock.lock();

            String[] modelNames = this.vehicle.getModelNames();

            for (int i = 0;i < modelNames.length; i++){
                System.out.println(modelNames[i]);
            }
        } finally {
            this.lock.unlock();
        }
    }

}
