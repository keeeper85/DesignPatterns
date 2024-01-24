package adapterpattern;

public class Adapter {

    public static void main(String[] args) {

        Druid druid = new Druid();
        BrownBear bear = new BrownBear();

//        druid.heal(bear);

        Ally friendlyBear = druid.speakWithAnimals(bear);
        druid.heal(friendlyBear);
        System.out.println("Patrzysz na: " + friendlyBear);
    }

    static class Druid{

        int healingPower = 10;
        void heal(Ally ally){
            System.out.println("Druid rzuca czar 'Leczenie': " + ally.toString());
            ally.recoverHP(healingPower);
            if (ally instanceof Animal){
                Animal animalCompanion = (Animal) ally;
                System.out.println("Uleczono " + healingPower + " punktów życia. Aktualne punkty życia: " + animalCompanion.hp);
            }
        }

        Ally speakWithAnimals(Enemy enemy){
            System.out.println("Druid rzuca czar 'Rozmowa ze zwierzętami': " + enemy);
            if (enemy instanceof BrownBear) {
                System.out.println("[Sukces. Otrzymano nowego sojusznika.]");
                return new EnemyToAllyAdapter(enemy);
            }
            return null;
        }
    }

    static abstract class Animal{
        int hp = 30;

        String healthDescription(){
            if (hp < 40) return " (lekko ranny)";
            else return " (zdrowy)";
        }
    }

    interface Ally {
        void recoverHP(int hp);
        String toString();
    }
    interface Enemy{
        String toString();
    }
    static class BrownBear extends Animal implements Enemy{
        @Override
        public String toString() {
            return "Niedźwiedź brunatny" + healthDescription();
        }
    }
    static class EnemyToAllyAdapter extends Animal implements Ally {
        Enemy enemy;

        public EnemyToAllyAdapter(Enemy enemy) {
            this.enemy = enemy;
        }
        @Override
        public void recoverHP(int hp) {
            this.hp += hp;
        }
        @Override
        public String toString() {
            return "Niedźwiedź brunatny (sojusznik)" + healthDescription();
        }
    }
}
