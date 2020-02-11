package rmiclient;

import app.main.InformerInterface;
import app.main.RegistrationException;
import rmiserver.InformerInterfaceImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RunInformer {
    public static void main(String[] args) throws RemoteException, RegistrationException, AlreadyBoundException, NotBoundException {
        InformerInterfaceImpl informerInterface = new InformerInterfaceImpl();
        informerInterface.registerToCentral();

    }
}
