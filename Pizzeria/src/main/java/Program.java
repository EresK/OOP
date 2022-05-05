import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        String pathMakers = "./src/main/resources/makers";
        String pathDeliverers = "./src/main/resources/deliverers";
        String pathQueue = "./src/main/resources/queue";

        try {
            runPizzeria(pathMakers, pathDeliverers, pathQueue);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void runPizzeria(String pathMakers, String pathDeliverers, String pathQueue) throws Exception {
        File fileMakers = new File(pathMakers);
        File fileDeliverers = new File(pathDeliverers);
        File fileQueue = new File(pathQueue);

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<Integer> makers = objectMapper.readValue(fileMakers, new TypeReference<>(){});
        ArrayList<Integer> deliverers = objectMapper.readValue(fileDeliverers, new TypeReference<>() {});
        ArrayList<Integer> queues = objectMapper.readValue(fileQueue, new TypeReference<>() {});

        if (queues.size() == 3) {
            Pizzeria pizzeria = new Pizzeria(queues.get(0), queues.get(1), queues.get(2), makers, deliverers);
            pizzeria.run();
        }
    }
}
