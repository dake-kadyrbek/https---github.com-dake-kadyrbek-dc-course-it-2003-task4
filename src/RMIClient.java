import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIClient {
    static int handleInput(int input){
        return input+1;
    }

    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (service.isWorking()) {
                if (service.isEmpty()) {
                    System.out.println("Result: " + service.getResult());
                    service.setWorking(false); // если очередь пуста, то клиент оканчивает работу и показывает результат
                    return;
                }

                int integer = service.pollElem();
                System.out.println("Received: " + integer);
                int result = handleInput(integer);
                service.addResult(result);
                System.out.println("Sent: " + result);
                Thread.sleep(5000);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}