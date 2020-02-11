package app.main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InformerInterface extends Remote{
    public void putMessage(String message) throws RemoteException;
}
