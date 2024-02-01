package chainofresponsibilitypattern;

public class ChainOfResponsibilityEasy {
    public static void main(String[] args) {
        Barbarian barbarian = new Barbarian(11);
        barbarian.setEnemy(new Goblin(new OrcWarlord(new TrollKing(null))));
    }
    static class Barbarian{
        int level;
        Enemy enemy;
        public Barbarian(int level) {
            this.level = level;
        }
        public void attack (){
            if (enemy == null) System.out.println("Barbarzyńca: Zwycięstwo!");
            else {
                System.out.println("Barbarzyńca: Atakuje " + enemy);
                enemy.defend(this);
            }
        }
        public void setEnemy(Enemy enemy) {
            this.enemy = enemy;
            attack();
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
            if (barbarian.level <= 9) System.out.println("Szef Orków: Dam mu radę sam!");
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
