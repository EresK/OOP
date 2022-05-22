import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;

public class PizzeriaDeserializer {
    public Pizzeria deserialize(String filename) {
        Pizzeria pizzeria = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(new File(filename));
            int orderQueueSize = node.get("orderSize").asInt(0);
            int stockQueueSize = node.get("stockSize").asInt(0);
            int orderCreators = node.get("orderCreators").asInt(0);

            ArrayList<Integer> exp = new ArrayList<>();
            if (node.get("experiences").isArray()) {
                for (JsonNode n : node.get("experiences"))
                    exp.add(n.asInt());
            }

            ArrayList<Integer> back = new ArrayList<>();
            if (node.get("backpackSizes").isArray()) {
                for (JsonNode n : node.get("backpackSizes"))
                    back.add(n.asInt());
            }

            pizzeria = new Pizzeria(orderQueueSize, stockQueueSize, orderCreators, exp, back);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return pizzeria;
    }
}
