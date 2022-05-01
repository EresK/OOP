import java.util.Random;

public class PizzaBuilder {
    private final Random random = new Random();

    private final String[] names = {"New York", "Sicilian",
            "Four Cheese" , "Pepperoni", "Hawaiian", "Vegetarian"};

    private final String[] ingredients = {"classic", "with cheese", "with mushrooms",
            "with garlic", "with pineapple", "with sausage", "with chicken"};

    public String build() {
        return names[random.nextInt(0, names.length - 1)] + " " +
                ingredients[random.nextInt(0, ingredients.length - 1)];
    }
}
