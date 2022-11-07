import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    int pollElem() throws RemoteException;
    void addElem(int integer) throws RemoteException;
    public void addResult(int integer) throws RemoteException;
    public int getResult() throws RemoteException;
    public boolean isEmpty() throws RemoteException;
    public void setWorking(boolean isWorking) throws RemoteException;
    public boolean isWorking() throws RemoteException;
    public int returnRes() throws RemoteException, InterruptedException;

}
