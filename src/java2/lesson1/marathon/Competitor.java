package java2.lesson1.marathon;

public interface Competitor {

    void run(int dist);
    void swim(int dist);
    void jump(int height);

    boolean onDistance();
    void info();
    String getName();
}
