package java2.lesson1.marathon;

public class Main {

    public static void main(String[] args) {
        Team team = new Team("\"ПоБеда\"",
                new Cat("Pushok", "Red", 10, 1, 2),
                new Dog("Druzhok", "Brown", 10, 2, 1),
                new Turtle("Tortila", "Green", 2, 10, 0),
                new Human("Sergey", "Male", 5, 1, 2));
        team.info();
        Course course = new Course(new Road(5), new Wall(2), new Water(1));
        course.info();
        course.doIt(team);
        team.showResults();
    }
}