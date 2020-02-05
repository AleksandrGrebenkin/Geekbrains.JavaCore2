public class Robot implements IRunnable, IJumpable, ISportsman {
    private String name;
    private double maxDistance;
    private double maxHeight;

    public Robot(String name){
        this.name = name;
        this.maxDistance = Math.random() * 10000 + 5000;
        this.maxHeight = Math.random() + 1.5;
    }

    @Override
    public boolean run(double distance) {
        boolean result = distance > maxDistance ? false : true;
        String resultMessage = result? "пробежал успешно": "не смог пробежать";
        System.out.printf("%s может пробежать %.2f. Пытается пробежать %.2f. Результат: %s\n"
                , name, maxDistance, distance, resultMessage);
        return result;
    }

    @Override
    public boolean jump(double height) {
        boolean result = height > maxHeight ? false : true;
        String resultMessage = result? "перепрыгнул успешно": "не смог перепрыгнуть";
        System.out.printf("%s может перепрыгнуть %.2f. Пытается перепрыгнуть %.2f. Результат: %s\n"
                , name, maxHeight, height, resultMessage);
        return result;
    }

    @Override
    public String toString() {
        return "Robot:" +
                " name = " + name +
                ", maxDistance = " + maxDistance +
                ", maxHeight = " + maxHeight;
    }

}
