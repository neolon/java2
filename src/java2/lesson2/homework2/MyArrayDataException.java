package java2.lesson2.homework2;

public class MyArrayDataException extends RuntimeException {

    private int errIndexY;
    private int errIndexX;

    public int getErrIndexY() {
        return errIndexY;
    }

    public int getErrIndexX() {
        return errIndexX;
    }

    public MyArrayDataException(int errIndexX, int errIndexY) {
        this.errIndexX = errIndexX;
        this.errIndexY = errIndexY;
    }
}
