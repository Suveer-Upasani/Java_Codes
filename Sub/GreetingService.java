import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GreetingService extends Remote {
    String greet(String name) throws RemoteException;
}
