package java2.lesson1.marathon;

public class Human implements Competitor {
    private String name;
    private String gender;

    private int maxRunDistance;
    private int maxSwimDistance;
    private int maxJumpHeight;

    private boolean onDistance = true;

    public Human(String name, String gender, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.name = name;
        this.gender = gender;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance){
            System.out.println(gender + " " + name + " пробегает " + dist + "km");
        }else {
            System.out.println(gender + " " + name + " не пробегает " + dist + "km");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance){
            System.out.println(gender + " " + name + " проплывает " + dist + "km");
        }else {
            System.out.println(gender + " " + name + " не проплывает " + dist + "km");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight){
            System.out.println(gender + " " + name + " перепрыгивает " + height + "m");
        }else {
            System.out.println(gender + " " + name + " не перепрыгивает " + height + "m");
            onDistance = false;
        }
    }

    @Override
    public boolean onDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println(name + (onDistance ? " прошел" : " не прошел" ));
    }

    @Override
    public String getName() {
        return name;
    }
}
