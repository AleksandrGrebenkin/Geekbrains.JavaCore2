public class Wall implements ITrainer {
    private double height;

    public Wall(double height) {
        this.height = height;
    }
    public Wall(){
        this(Math.random()*1.5 + 0.5);
    }

    public boolean tryJump(IJumpable jumper){
        getInfo();
        return jumper.jump(height);
    }

    private void getInfo(){
        System.out.printf("Стена. Высота %.2f\n", height);
    }
}
