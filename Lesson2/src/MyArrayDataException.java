public class MyArrayDataException extends RuntimeException {
    public MyArrayDataException(int i, int j) {
        super(String.format("В ячейке [%d][%d] находится не целое число", i , j));
    }
}
