package rmiserver;

import app.main.CentralInterface;
import app.main.InformerInterface;
import app.main.ParcelLockerInterface;
import app.main.RegistrationException;

import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class InformerInterfaceImpl implements InformerInterface {
    private CentralInterface centralInterface;

    public InformerInterfaceImpl()
    {

    }

    @Override
    public void putMessage(String message) throws RemoteException {
        try {
            System.out.println(message);
        }
        catch (Exception e)
        {
            throw new RemoteException("Nie udalo sie odebrac wiadomosci");
        }
    }

    public void registerToCentral() throws RemoteException, NotBoundException, NoSuchObjectException, RegistrationException, AlreadyBoundException {
        //UnicastRemoteObject.unexportObject(this, true);
        Registry registry = LocateRegistry.getRegistry("localhost", 1111);
        centralInterface = (CentralInterface) registry.lookup("Central");
        InformerInterface informerInterface = (InformerInterface) UnicastRemoteObject.exportObject(this, 1110);
        System.out.println("jd");
        centralInterface.registerInformer(informerInterface);
        System.out.println("jd");
        registry.bind("Informer", informerInterface);
    }

    public void unregisterFromCentral() throws RemoteException, NotBoundException, RegistrationException
    {
        Registry registry = LocateRegistry.getRegistry();
        CentralInterface centralInterface = (CentralInterface) registry.lookup("Central");
        centralInterface.unregisterInformer();
    }
}
