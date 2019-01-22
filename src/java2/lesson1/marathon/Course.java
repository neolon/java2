package java2.lesson1.marathon;

public class Course {
    Obstacle[] obstacles;

    public Course(Obstacle... obs) {
        this.obstacles = new Obstacle[obs.length];
        for (int i = 0; i < obs.length; i++) {
            this.obstacles[i] = obs[i];
        }
    }

    public void doIt(Team team) {
        for (int i = 0; i < team.getTeam().length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (team.getTeam()[i].onDistance()) {
                    obstacles[j].doIt(team.getTeam()[i]);
                }
            }
        }
    }

    public void info () {
        System.out.println("\nПолоса препятствий: ");
        for (int i = 0; i < obstacles.length; i++) {
            System.out.println(i + 1 + ". " + obstacles[i].getClass().getSimpleName() + " - " + obstacles[i].getValue());
        }
        System.out.println();
    }
}
