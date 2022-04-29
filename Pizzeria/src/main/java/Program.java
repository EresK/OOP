import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String fileMakers = "/home/eres/IdeaProjects/Pizzeria2/src/main/resources/makers";
        String fileDeliverers = "/home/eres/IdeaProjects/Pizzeria2/src/main/resources/deliverers";

        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> makers = new ArrayList<>();
        ArrayList<Integer> deliverers = new ArrayList<>();

        System.out.println("enter:\n" +
                "\"run\" - to run a pizzeria\n" +
                "\"add p number\" to add pizza maker, number - experience\n" +
                "\"add d number\" to add deliverer, number - backpack size\n" +
                "\"exit\" to exit");

        while (true) {
            String[] in = scanner.nextLine().split(" ");

            if (in.length > 0 && in[0].equals("exit")) {
                if (!makers.isEmpty())
                    writeEmployee(makers, fileMakers);
                if (!deliverers.isEmpty())
                    writeEmployee(deliverers, fileDeliverers);
                break;
            }

            if (in.length == 1 || in.length == 3) {
                switch (in[0]) {
                    case "run":
                        runPizzeria(fileMakers, fileDeliverers);
                        break;

                    case "add":
                        if (in.length == 3) {
                            if (in[1].equalsIgnoreCase("p")) {
                                int n = Integer.parseInt(in[2]);
                                makers.add(n);
                            }
                            else if (in[1].equalsIgnoreCase("d")) {
                                int n = Integer.parseInt(in[2]);
                                deliverers.add(n);
                            }
                        }
                        break;

                    default:
                        System.out.println("met unknown command");
                        break;
                }
            }
        }
    }

    private static void runPizzeria(String filenamePizzaMakers, String filenameDeliverers) {
        File filePMs = new File(filenamePizzaMakers);
        File fileDs = new File(filenameDeliverers);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ArrayList<Integer> makers = objectMapper.readValue(filePMs, new TypeReference<>(){});
            ArrayList<Integer> deliverers = objectMapper.readValue(fileDs, new TypeReference<>() {});

            Pizzeria pizzeria = new Pizzeria(5, 5, makers, deliverers);
            pizzeria.run();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void writeEmployee(ArrayList<Integer> list, String filename) {
        File file = new File(filename);

        ObjectMapper op = new ObjectMapper();

        try {
            op.writeValue(file, list);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
