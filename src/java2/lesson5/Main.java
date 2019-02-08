package java2.lesson5;

public class Main {

    private static int i;
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;
    private static float[] arr = new float[SIZE];
    private static float[] a1 = new float[HALF_SIZE];
    private static float[] a2 = new float[HALF_SIZE];
    private static float startTime;
    private static float endTime;
    private static float nanoSecondsToSeconds = 0.000000001f;

    public static void main(String[] args) {

        startTime = System.nanoTime();
        fillArrayWithOnes();
        endTime = (System.nanoTime() - startTime) * nanoSecondsToSeconds;
        System.out.printf("Filling array with ones - %.6f\n", endTime);

        startTime = System.nanoTime();
        arrayRefill(arr);
        endTime = (System.nanoTime() - startTime) * nanoSecondsToSeconds;
        System.out.printf("Time of straight array refilling is - %.6f\n", endTime);

        startTime = System.nanoTime();
        fillArrayWithOnes();
        endTime = (System.nanoTime() - startTime) * nanoSecondsToSeconds;
        System.out.printf("Filling array with ones - %.6f\n", endTime);

        startTime = System.nanoTime();
        threadedRefill();
        endTime = (System.nanoTime() - startTime) * nanoSecondsToSeconds;
        System.out.printf("Time of two threaded array refilling is - %.6f\n", endTime);

    }

    private static void fillArrayWithOnes() {
        int arrayLength = arr.length;
        for (i = 0; i < arrayLength; i++) {
            arr[i] = 1;
        }
    }

    private static float formula(float input, int counter) {
        return (float)(input
                * Math.sin(0.2f + counter / 5)
                * Math.cos(0.2f + counter / 5)
                * Math.cos(0.4f + counter / 2));
    }

    private static void arrayRefill(float[] array) {
        int arrayLength = array.length;

        for (i = 0; i < arrayLength; i++) {
            array[i] = formula(array[i], i);
        }
    }

    private static void threadedRefill() {
        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                arrayRefill(a1);
            }
        }).start();
        new Thread(()->Main.arrayRefill(a2)).start();

        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
    }
}
