package chainofresponsibilitypattern;

public class ChainOfResponsibilityEasy {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static void main(String[] args) {
        Barbarian barbarianLv1 = new Barbarian(1);
        barbarianLv1.setEnemy(new Goblin(new OrcWarlord(new TrollKing(null))));
        System.out.println("---------------");

        Barbarian barbarianLv4 = new Barbarian(4);
        barbarianLv4.setEnemy(new Goblin(new OrcWarlord(new TrollKing(null))));
        System.out.println("---------------");

        Barbarian barbarianLv8 = new Barbarian(8);
        barbarianLv8.setEnemy(new Goblin(new OrcWarlord(new TrollKing(null))));
        System.out.println("---------------");

        Barbarian barbarianLv11 = new Barbarian(11);
        barbarianLv11.setEnemy(new Goblin(new OrcWarlord(new TrollKing(null))));
    }
    static class Barbarian{
        int level;
        Enemy enemy;
        public Barbarian(int level) {
            this.level = level;
        }
        public void attack (){
            if (enemy == null) System.out.println(RED + this + "Zwycięstwo!" + RESET);
            else {
                System.out.println(RED + this + "Atakuje " + enemy + RESET);
                enemy.defend(this);
            }
        }
        public void setEnemy(Enemy enemy) {
            this.enemy = enemy;
            attack();
        }
        @Override
        public String toString() {
            return "Barbarzyńca (poziom " + level + "): ";
        }
    }
    interface Enemy{
        void defend(Barbarian barbarian);
    }
    static class Goblin implements Enemy{
        Enemy nextEnemy;

        public Goblin(Enemy nextEnemy) {
            this.nextEnemy = nextEnemy;
        }

        @Override
        public void defend(Barbarian barbarian) {
            if (barbarian.level <= 3) System.out.println("Goblin: Dam mu radę sam!");
            else {
                System.out.println("Goblin: Potrzebuję pomocy!");
                barbarian.setEnemy(nextEnemy);
            }
        }
        @Override
        public String toString() {
            return "Goblin";
        }
    }
    static class OrcWarlord implements Enemy{
        Enemy nextEnemy;
        public OrcWarlord(Enemy nextEnemy) {
            this.nextEnemy = nextEnemy;
        }
        @Override
        public void defend(Barbarian barbarian) {
            if (barbarian.level <= 6) System.out.println("Szef Orków: Dam mu radę sam!");
            else {
                System.out.println("Szef Orków: Potrzebuję pomocy!");
                barbarian.setEnemy(nextEnemy);
            }
        }
        @Override
        public String toString() {
            return "Szef Orków";
        }
    }
    static class TrollKing implements Enemy{
        Enemy nextEnemy;
        public TrollKing(Enemy nextEnemy) {
            this.nextEnemy = nextEnemy;
        }
        @Override
        public void defend(Barbarian barbarian) {
            if (barbarian.level <= 9) System.out.println("Król Trolli: Dam mu radę sam!");
            else {
                System.out.println("Król Trolli: Nie damy mu rady!");
                barbarian.setEnemy(nextEnemy);
            }
        }
        @Override
        public String toString() {
            return "Król Trolli";
        }
    }
}
