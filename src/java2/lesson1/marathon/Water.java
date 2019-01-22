package java2.lesson1.marathon;

public class Water extends Obstacle {

    private int dist;

    public Water(int dist) {
        this.dist = dist;
    }

    @Override
    void doIt(Competitor competitor) {
        competitor.swim(dist);
    }

    @Override
    public int getValue() {
        return dist;
    }
}
