package statepattern;

public class State {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Character warrior = new Warrior();
        Enemy bigBoss = new BigBoss();

        warrior.attack(bigBoss);
    }
    interface Phase{
        void retaliate(Enemy user, Character target);
        void useSkill();
    }
    interface Character{
        void attack(Enemy target);
        void takeDamage(int damage);
        void takeMultipleHits(int... damage);
    }
    static class Warrior implements Character{
        int damage = 20;
        int hp = 200;
        Enemy enemy;
        @Override
        public void attack(Enemy target) {
            enemy = target;
            System.out.println(BLUE + "(Wojownik) Atakuje wroga: " + target.toString() + RESET);
            target.takeDamage(this, damage);
        }
        @Override
        public void takeDamage(int damage) {
            if (damage > 0) {
                int random = (int) (Math.random() * 10);
                if (random > 3 && random < 7) System.out.println(BLUE + "(Wojownik) Udało się uniknąć ataku!" + RESET);
                else if (random > 7) System.out.println(BLUE + "(Wojownik) Tarcza zatrzymała obrażenia!" + RESET);
                else {
                    hp -= damage;
                    System.out.println(BLUE + "(Wojownik) Otrzymano obrażenia: " + damage + RESET);
                }
                checkHealth();
            }
        }
        @Override
        public void takeMultipleHits(int... damage) {
            int totalDamage = 0;
            for (int i = 0; i < damage.length; i++) {
                totalDamage += damage[i];
                System.out.println(PURPLE + "(Wojownik) Otrzymano obrażenia: " + damage[i] + RESET);
            }
            hp -= totalDamage;
            checkHealth();
        }
        void checkHealth(){
            if (hp <= 0){
                System.out.println(BLUE + "(Wojownik) Odwrót! Wróg jest zbyt potężny!" + RESET);
            }
            else if (hp < 100) {
                System.out.println(GREEN + "(Wojownik) Używa mikstury leczniczej : zregenerowano 100hp." + RESET);
                hp += 100;
            }
            if (hp > 0) {
                System.out.println(BLUE + "(Wojownik) Aktualne punkty życia: " + hp + RESET);
                attack(enemy);
            }
        }
    }
    interface Enemy{
        void takeDamage(Character source, int damage);
        int getHp();
        void setPhase(Phase phase);
        String toString();
    }
    static class BigBoss implements Enemy{

        int hp = 200;
        Phase currentPhase;
        public BigBoss() {
            currentPhase = new PhaseOne();;
        }
        @Override
        public void takeDamage(Character source, int damage) {
            hp -= damage;
            if (hp >= 20){
                System.out.println("(Boss) Otrzymano obrażenia: " + damage + ". Aktualne punkty życia: " + hp);
                currentPhase.retaliate(this, source);
            }
            else{
                System.out.println(RED + "(Boss) Nie!!! Jak to się mogło stać!!?? Jeszcze tu wrócę! Zapamiętaj moje słowa!" + RESET);
                System.out.println(RED + "(Boss) Używa umiejętności: 'Drzwi przez wymiary'" + RESET);
            }
        }
        @Override
        public int getHp() {
            return hp;
        }
        @Override
        public void setPhase(Phase phase) {
            currentPhase = phase;
        }
        @Override
        public String toString() {
            return "Boss";
        }
    }
    static class PhaseOne implements Phase{
        boolean stateJustChanged = false;
        @Override
        public void retaliate(Enemy user, Character target) {
            if (!stateJustChanged){
                stateJustChanged = true;
                System.out.println(RED + "(Boss) Ha ha ha! Twoje żałosne ataki nie wyrządzą mi żadnej krzywdy!" + RESET);
            }
            if (user.getHp() < 150 && user.getHp() >= 70){
                user.setPhase(new PhaseTwo());
            }
            System.out.println(RED + "(Boss) Teraz moja kolej!" + RESET);
            target.takeDamage(20);
        }
        @Override
        public void useSkill() {
        }
    }
    static class PhaseTwo implements Phase{
        boolean stateJustChanged = false;
        Character target;
        @Override
        public void retaliate(Enemy user, Character target) {
            this.target = target;
            if (!stateJustChanged){
                stateJustChanged = true;
                System.out.println(RED + "(Boss) No to teraz mnie wkurzyłeś! Nietoperze, zająć się nim!" + RESET);
            }
            if (user.getHp() < 70 && user.getHp() >= 20){
                user.setPhase(new PhaseThree());
            }
            useSkill();
        }
        @Override
        public void useSkill() {
            System.out.println(YELLOW + "(Boss) Używa umiejętności: 'Atak roju nietoperzy'" + RESET);
            int attack_1 = (int) (Math.random() * 10) + 1;
            int attack_2 = (int) (Math.random() * 10) + 1;
            int attack_3 = (int) (Math.random() * 10) + 1;
            int attack_4 = (int) (Math.random() * 10) + 1;
            target.takeMultipleHits(attack_1, attack_2, attack_3, attack_4);
        }
    }
    static class PhaseThree implements Phase{
        boolean stateJustChanged = false;
        Character target;
        @Override
        public void retaliate(Enemy user, Character target) {
            this.target = target;
            if (!stateJustChanged){
                stateJustChanged = true;
                System.out.println(RED + "(Boss) Nigdy mnie nie pokonasz! Giń żałosna kreaturo!" + RESET);
            }
            System.out.println(RED + "(Boss) Już po tobie!" + RESET);
            useSkill();
        }
        @Override
        public void useSkill() {
            System.out.println(YELLOW + "(Boss) Używa umiejętności: 'Atak kończący'" + RESET);
            target.takeDamage(100);
        }
    }
}
