public class Main {
    public static void main(String[] args) {


        ISportsman[] sportsmens = new ISportsman[5];
        for (int i = 0; i < sportsmens.length; i++){
            switch (i%3) {
                case (0):
                    sportsmens[i] = new Human("Human" + i);
                    break;
                case (1):
                    sportsmens[i] = new Robot("Robot"+1);
                    break;
                case (2):
                    sportsmens[i] = new Cat("Cat"+i);
                    break;
            }
        }

        ITrainer[] trainers = new ITrainer[5];
        for (int i = 0; i < trainers.length; i++){
            switch (i%2){
                case 0:
                    trainers[i] = new Treadmill();
                    break;
                case 1:
                    trainers[i] = new Wall();
                    break;
            }
        }

        for(ISportsman sportsman : sportsmens){
            for (ITrainer trainer : trainers){
                if(trainer instanceof Treadmill && sportsman instanceof IRunnable){
                    if(!((Treadmill) trainer).tryRun((IRunnable) sportsman)){
                        break;
                    }
                }
                if (trainer instanceof Wall && sportsman instanceof IJumpable){
                    if(!((Wall) trainer).tryJump((IJumpable) sportsman)){
                        break;
                    }
                }
            }
            System.out.println("Закончил упражнение");
        }

    }
}
