package Task2;

public class MainApp {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.add("Vetal", "79856987445");
        pb.add("Vetal", "79856987445");
        pb.add("Vetal", "79856987446");
        pb.add("Vetal", "79856987447");
        pb.add("Irina", "79856987445");
        pb.add("Irina", "79856987446");
        pb.add("Semen", "79856987000");

        pb.get("Vetal");
    }
}
