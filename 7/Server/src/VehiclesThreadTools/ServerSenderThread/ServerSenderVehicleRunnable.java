package VehiclesThreadTools.ServerSenderThread;

import BaseVehicle.Vehicle;
import Vehicles.VehicleAdditionalTool;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSenderVehicleRunnable implements Runnable{

    private ServerSocket serverSocket;

    public ServerSenderVehicleRunnable(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    @Override
    public void run(){
        try{
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection is established");
            System.out.println("Start program");
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            System.out.println("Read objects...");
            Vehicle[] vehicles = (Vehicle[]) in.readObject();
            out.writeFloat(VehicleAdditionalTool.averagePrice(vehicles));
            out.flush();

            out.close();
            in.close();

            System.out.println("Send data");

            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }
}
