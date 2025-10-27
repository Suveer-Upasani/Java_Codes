import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class GreetingServiceImpl extends UnicastRemoteObject implements GreetingService {

    protected GreetingServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String greet(String name) throws RemoteException {
        return "Hello, " + name + "! Welcome to RMI world!";
    }
}
