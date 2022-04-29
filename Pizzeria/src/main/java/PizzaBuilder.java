import java.util.Random;

public class PizzaBuilder {
    private final Random random = new Random();

    private final String[] names = {"Нью Йорк", "Сицилия",
            "Четыре сыра" , "Пепперони",
            "Охотничья", "Вегетарианская"};

    private final String[] ingredients = {"", "с сыром", "с грибами",
            "с чесноком", "с ананасом",
            "с колбаской", "с курицей"};

    public String build() {
        return names[random.nextInt(0, names.length - 1)] + " " +
                ingredients[random.nextInt(0, ingredients.length - 1)];
    }
}
