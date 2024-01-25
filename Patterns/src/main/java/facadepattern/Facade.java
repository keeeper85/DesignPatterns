package facadepattern;

public class Facade {
    public static void main(String[] args) {

        new Macro(new Warrior(), new Rogue(), new Ranger(), new Druid(), new Cleric(), new Wizard()).prepareForBattle();

    }

    static abstract class Character{}

    interface Skilled{
        void useSkill();
    }

    interface Spellcaster{
        void castSpell(Character... character);
    }
    static class Warrior extends Character implements Skilled{

        @Override
        public void useSkill() {
            System.out.println("(Wojownik) Przyjmuje postawę defensywną. (klasa pancerza -4)");
        }
        @Override
        public String toString() {
            return "Wojownik";
        }
    }

    static class Rogue extends Character implements Skilled{

        @Override
        public void useSkill() {
            System.out.println("(Łotrzyk) Ukrycie w cieniu: włączone.");
        }
        @Override
        public String toString() {
            return "Łotrzyk";
        }
    }

    static class Ranger extends Character implements Skilled, Spellcaster{

        @Override
        public void useSkill() {
            System.out.println("(Łowca) Nałożono truciznę na strzały.");
        }
        @Override
        public void castSpell(Character... character) {
            System.out.println("(Łowca) Rzuca czar 'Korowa skóra' na: " + character[0].toString());
        }
        @Override
        public String toString() {
            return "Łowca";
        }
    }

    static class Druid extends Character implements Skilled, Spellcaster{

        @Override
        public void useSkill() {
            System.out.println("(Druid) Przemiana w śnieżnego wilka.");
        }

        @Override
        public void castSpell(Character... character) {
            System.out.println("(Druid) Rzuca czar 'Przyzwanie niedźwiedzia'.");
            Bear bear = new Bear();
            System.out.println(bear);
        }

        @Override
        public String toString() {
            return "Druid";
        }
    }

    static class Bear{
        @Override
        public String toString() {
            return "--Niedźwiedź brunatny (zdrowy)";
        }
    }

    static class Cleric extends Character implements Spellcaster{

        @Override
        public void castSpell(Character... character) {
            System.out.println("(Kapłan) Rzuca czar grupowy: 'Psalm':");
            for (Character character1 : character) {
                System.out.println("Psalm: " + character1.toString());
            }
        }
        @Override
        public String toString() {
            return "Kapłan";
        }
    }

    static class Wizard extends Character implements Spellcaster{

        @Override
        public void castSpell(Character... character) {
            System.out.println("(Czarodziej) Rzuca czar grupowy: 'Przyśpieszenie ruchów':");
            for (Character character1 : character) {
                System.out.println("Przyśpieszenie: " + character1.toString());
            }
        }
        @Override
        public String toString() {
            return "Czarodziej";
        }
    }
    static class Macro{
        Warrior warrior;
        Rogue rogue;
        Ranger ranger;
        Druid druid;
        Cleric cleric;
        Wizard wizard;

        public Macro(Warrior warrior, Rogue rogue, Ranger ranger, Druid druid, Cleric cleric, Wizard wizard) {
            this.warrior = warrior;
            this.rogue = rogue;
            this.ranger = ranger;
            this.druid = druid;
            this.cleric = cleric;
            this.wizard = wizard;
        }

        public void prepareForBattle(){
            warrior.useSkill();
            rogue.useSkill();
            ranger.useSkill();
            ranger.castSpell(warrior);
            druid.castSpell(null);
            druid.useSkill();
            cleric.castSpell(warrior,rogue,ranger,druid,cleric,wizard);
            wizard.castSpell(warrior,rogue,ranger,druid,cleric,wizard);
        }
    }
}
