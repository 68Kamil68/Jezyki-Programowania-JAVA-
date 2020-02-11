package rmiclient;

import rmiserver.CentralInterfaceImpl;
import rmiserver.ParcelLockerInterfaceImpl;

public class RunCentral {

    public static void main(String[] args) throws Exception
    {
        CentralInterfaceImpl.registerCentral();
    }
}
