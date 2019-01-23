package java2.lesson2.homework2;


public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Размер массива должен быть 4х4!");
    }
}
