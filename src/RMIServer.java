import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Service service = new ServiceImpl();
            // Init service
            int numOfTasks = 30;
            for (int i=1;i<numOfTasks+1; i++){
                service.addElem(i);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
            int result = service.returnRes();
            long endTime = System.nanoTime();
            System.out.println("Result: "+result+" Time it took to complete the task: "+(endTime-startTime)/1000000000+" seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}