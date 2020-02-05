public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;

    public static void main(String[] args) {
        singleThread();
        multiThread();
    }

    public static void singleThread(){
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long t1 = System.currentTimeMillis();
        calculateNewValues(arr);
        long t2 = System.currentTimeMillis();
        System.out.println("Singlethread: " + (t2-t1) + " ms");
    }

    public static void multiThread(){
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long t1 = System.currentTimeMillis();
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread thread1 = new Thread(() -> calculateNewValues(arr1));
        Thread thread2 = new Thread(() -> calculateNewValues(arr2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        long t2 = System.currentTimeMillis();
        System.out.println("Multithread: " + (t2-t1) + " ms");
    }

    public static void calculateNewValues(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
