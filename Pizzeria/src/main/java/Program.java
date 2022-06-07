import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        String pathSerialized = "./src/main/resources/serialized";

        try {
            ArrayList<Integer> exp = new ArrayList<>();
            exp.add(1);
            exp.add(2);
            exp.add(3);
            ArrayList<Integer> back = new ArrayList<>();
            back.add(5);
            back.add(5);
            back.add(5);

            Pizzeria pizzeria = new Pizzeria(15, 10, 1, exp, back);

            /*PizzeriaSerializer s = new PizzeriaSerializer();
            s.serialize(pathSerialized, pizzeria);*/

            /*PizzeriaDeserializer d = new PizzeriaDeserializer();
            pizzeria = d.deserialize(pathSerialized);*/

            // pizzeria.run();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
