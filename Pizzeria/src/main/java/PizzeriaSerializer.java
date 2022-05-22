import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class PizzeriaSerializer {
    public void serialize(String filename, Pizzeria pizzeria) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), pizzeria);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
