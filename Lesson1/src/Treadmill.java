public class Treadmill implements ITrainer{
    private double distance;

    public Treadmill(double distance) {
        this.distance = distance;
    }

    public Treadmill() {
        this(Math.random()*10000+500);
    }

    public boolean tryRun(IRunnable runner){
        getInfo();
        return runner.run(distance);
    }

    private void getInfo(){
        System.out.printf("Беговаря дорожка. Дистанция %.2f\n", distance);
    }
}
