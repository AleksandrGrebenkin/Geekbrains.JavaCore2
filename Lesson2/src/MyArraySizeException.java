public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException(int i) {
        super(String.format("Размер входного массива не %dх%d", i, i));
    }
}
