package proxypattern;

public class DecoratorProxy {

    public static void main(String[] args) {

        Character wizard = new Wizard();
        Character rogue = new Rogue();
        Treasure treasure = new Chest();

//        treasure.open(wizard);
//        treasure.open(rogue);

        Treasure lockedTreasure = new LockedChest(treasure);
        lockedTreasure.open(wizard);
        lockedTreasure.open(rogue);
    }

    interface Character{}
    static class Wizard implements Character{
        @Override
        public String toString() {
            return "(Czarodziej)";
        }
    }
    static class Rogue implements Character{
        @Override
        public String toString() {
            return "(Łotrzyk)";
        }
    }
    interface Treasure{
        void open(Character character);
    }
    static class Chest implements Treasure{
        @Override
        public void open(Character character) {
            int goldAmount = (int) (Math.random() * 100 + 10);
            System.out.println(character.toString() + " Skrzynia otwarta. Zdobyto: " + goldAmount + " sztuk złota.");
        }
    }
    static class LockedChest implements Treasure{
        Treasure treasure;
        public LockedChest(Treasure treasure) {
            this.treasure = treasure;
        }
        @Override
        public void open(Character character) {
            if (character instanceof Rogue){
                System.out.println(character + " Udało się otworzyć zamek!");
                treasure.open(character);
            }
            else System.out.println(character + " Skrzynia zamknięta. Potrzebujesz klucza albo wytrychów.");
        }
    }
}
