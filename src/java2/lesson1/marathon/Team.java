package java2.lesson1.marathon;

public class Team {
    private String name;
    Competitor[] team;

    public Competitor[] getTeam() {
        return team;
    }

    public Team(String name, Competitor... comp) {
        this.name = name;
        this.team = new Competitor[comp.length];
        for (int i = 0; i < comp.length; i++) {
            this.team[i] = comp[i];
        }
    }

    public void info() {
        System.out.println("Название команды: " + name + "\nУчастники: ");
        for (int i = 0; i < team.length; i++) {
            System.out.println(i + 1 + ". " + team[i].getName());
        }
    }

    public void showResults() {
        System.out.println("\nНазвание команды: " + name + "\nПрошли полосу препятствий: ");
        for (int i = 0; i < team.length; i++) {
            if (team[i].onDistance()) {
                System.out.println(team[i].getName());
            }
        }
    }
}
