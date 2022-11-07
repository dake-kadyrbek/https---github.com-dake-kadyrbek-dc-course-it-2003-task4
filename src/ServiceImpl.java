import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;
    private int result =0;
    private boolean isWorking = true;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public int pollElem() throws RemoteException, NullPointerException {
        return this.queue.poll();
    }

    @Override
    public void addElem(int integer) throws RemoteException {
        this.queue.add(integer);
        System.out.println("Added: " + integer);
    }

    @Override
    public void addResult(int integer){
        this.result+=integer;
    }

    @Override
    public int getResult(){
        return this.result;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int returnRes() throws InterruptedException{
        while (!isEmpty()&&isWorking()){

        }
        Thread.sleep(1000); // клиент не успевает добавить уже обработанный резузльтат в конечный результат, поэтому и добавил таймер
        return getResult();
    }

    @Override
    public boolean isWorking(){
        return isWorking;
    }

    @Override
    public void setWorking(boolean isWorking) throws RemoteException {
        this.isWorking = isWorking;
    }
}