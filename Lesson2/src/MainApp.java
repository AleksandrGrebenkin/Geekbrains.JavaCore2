public class MainApp {
    public static void main(String[] args) {
        String[][] strings = {{"1","1","1","1"},{"1","1","1","1"},{"1","1","1","1"},{"1","1","1","1"}};
        int sum = 0;
        try {
            sum = showIntSumInStringArray(strings);
            System.out.println("Сумма элеменов массива = " + sum);
        }
        catch (MyArraySizeException e){
            System.err.println(e.getMessage());
        }
        catch (MyArrayDataException e){
            System.err.println(e.getMessage());
        }
    }

    public static int showIntSumInStringArray(String[][] strings) {
        int size = 4;
        if (strings.length != size){
            throw new MyArraySizeException(size);
        } else {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length != size){
                        throw new MyArraySizeException(size);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    result += Integer.parseInt(strings[i][j]);
                }
                catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return result;
    }
}
