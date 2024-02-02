package chainofresponsibilitypattern;

public class ChainOfResponsibility {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Hero barbarian = new Barbarian(9);
        Enemy goblin = new Goblin();

        barbarian.attack(goblin);
    }
    interface Hero {
        void attack(Enemy enemy);
        void takeDamage(Enemy enemy, int damage);
    }
    static class Barbarian implements Hero {
        int level;
        int hp;
        int power;
        Enemy target;

        public Barbarian(int level) {
            this.level = level;
            hp = 20 * level;
            power = 10 * level;
            System.out.println(this + " Info: lv" + level + ", " + hp + "hp");
        }
        @Override
        public void attack(Enemy enemy) {
            target = enemy;
            int damage = power + offensiveSkill();
            System.out.println(RED + this + " Atakuje " + enemy + " za: " + damage + " obrażeń." + RESET);
            enemy.takeDamage(this, damage);
        }

        @Override
        public void takeDamage(Enemy enemy, int damage) {
            target = enemy;
            if (isEvadeSkillTriggered()) {
                System.out.println(this + " Udało się uniknąć ataku!");
                attack(target);
            }
            else {
                hp -= damage;
                System.out.println(this + " Otrzymano obrażenia " + damage + " Pozostało " + hp + " punktów życia.");
                if (!isDefeated()) attack(target);
                else {
                    System.out.println(this + " Umieram!");
                    System.exit(0);
                }
            }
        }
        private boolean isDefeated() {
            if (hp > 0) return false;
            return true;
        }
        private boolean isEvadeSkillTriggered() {
            int random = (int) (Math.random() * 100);
            if (level > 6 && random > 60) return true;
            return false;
        }
        private int offensiveSkill() {
            if (level > 3){
                int random = (int) (Math.random() * power);
                System.out.println(this + " Używa umiejętności: 'Atak z wyskoku'");
                return random;
            }
            return 0;
        }
        @Override
        public String toString() {
            return "(Barbarzyńca)";
        }
    }

    static abstract class Enemy{
        int hp = 20;
        int power = 10;
        Hero target;

        abstract void attack(Hero target);

        abstract void retaliate();

        abstract void takeDamage(Hero source, int damage);

        abstract void checkHp();
    }

    static class Goblin extends Enemy{
        @Override
        void attack(Hero target) {
            System.out.println(BLUE + this + " atakuje " + target + RESET);
            target.takeDamage(this, power);
        }

        @Override
        void retaliate() {
            System.out.println(BLUE + this + " atakuje " + target + RESET);
            target.takeDamage(this, power);
        }

        @Override
        void takeDamage(Hero source, int damage) {
            System.out.println(this + " Otrzymano obrażenia " + damage);
            target = source;
            hp -= damage;
            checkHp();
        }
        @Override
        protected void checkHp() {
            if (hp > 0) retaliate();
            if (hp <= 0){
                System.out.println(BLUE + this + " Szefie, nie daję rady! Pomocy!" + RESET);
                Enemy orcWarlord = new OrcWarlord();
                orcWarlord.attack(target);
            }
        }
        @Override
        public String toString() {
            return "(Goblin)";
        }
    }

    static class OrcWarlord extends Enemy{
        int hp = 100;
        int power = 30;

        public OrcWarlord() {
            System.out.println(PURPLE + "---Na polu walki pojawia się: " + this + RESET);
        }

        @Override
        void attack(Hero target) {
            System.out.println(PURPLE + this + " atakuje " + target + RESET);
            target.takeDamage(this, this.power);
        }

        @Override
        void retaliate() {
            System.out.println(PURPLE + this + " atakuje " + target + RESET);
            target.takeDamage(this, this.power);
        }

        @Override
        void takeDamage(Hero source, int damage) {
            System.out.println(this + " Otrzymano obrażenia " + damage);
            target = source;
            hp -= damage;
            checkHp();
        }
        @Override
        protected void checkHp() {
            if (hp > 0) retaliate();
            if (hp <= 0){
                System.out.println(PURPLE + this + " Królu! Poległem!" + RESET);
                Enemy trollKing = new TrollKing();
                trollKing.attack(target);
            }
        }
        @Override
        public String toString() {
            return "(Szef Orków)";
        }
    }
    static class TrollKing extends Enemy{
        int hp = 300;
        int power = 60;

        public TrollKing() {
            System.out.println(YELLOW + "---Na polu walki pojawia się: " + this + RESET);
        }

        @Override
        void attack(Hero target) {
            System.out.println(YELLOW + this + " atakuje " + target + RESET);
            target.takeDamage(this, this.power);
        }

        @Override
        void retaliate() {
            System.out.println(YELLOW + this + " atakuje " + target + RESET);
            target.takeDamage(this, this.power);
        }

        @Override
        void takeDamage(Hero source, int damage) {
            System.out.println(this + " Otrzymano obrażenia " + damage);
            target = source;
            hp -= damage;
            checkHp();
        }
        @Override
        protected void checkHp() {
            if (hp > 0) retaliate();
            if (hp <= 0){
                System.out.println(YELLOW + this + " Nie daliśmy mu rady kamraci! Stał się zbyt potężny!" + RESET);
                System.exit(0);
            }
        }
        @Override
        public String toString() {
            return "(Król Trolli)";
        }
    }
}
