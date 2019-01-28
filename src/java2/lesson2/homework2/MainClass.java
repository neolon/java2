package java2.lesson2.homework2;

public class MainClass {
    public static void main(String[] args) {
        String[][] arr = {{"6","ghj","4","5"},
                {"6","3","4","5"},
                {"2","7","5","5"},
                {"2","3","4","5"}};

        try {
            arrExp(arr);
        } catch (IndexOutOfBoundsException e){
            throw new MyArraySizeException();
        } catch (MyArrayDataException e){
            System.err.println("В массиве некорректные данные в ячейке " +
                    "["+e.getErrIndexX()+"]"+"["+e.getErrIndexY()+"}");
        }
    }


    public static void arrExp(String[][] arr) {
        if (arr[0].length == 4 && arr.length == 4) {
            System.out.println("Массив нужного размера");
        } else {
            throw new IndexOutOfBoundsException();
        }

        int sumArr = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sumArr+=Integer.parseInt(arr[i][j]);

                } catch (NumberFormatException e ){
                    throw new MyArrayDataException(i,j);
                }
            }
        }
        System.out.println("Сумма массива: " +sumArr);
    }
}
