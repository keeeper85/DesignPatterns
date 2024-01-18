package decoratorpattern;

public class Decorator {

    public static void main(String[] args) {

        Spellcaster warrior = new InvisibilityRing(new BootsOfSpeed(new CloakOfDeflection(new Warrior())));
        warrior.castSpell();

    }

    interface Spellcaster{
        void castSpell();
    }

    static class Warrior implements Spellcaster{

        @Override
        public void castSpell() {
            System.out.println("Wojownik rzuca czar:");
        }
    }

    static class InvisibilityRing implements Spellcaster{

        Spellcaster spellcaster;

        public InvisibilityRing(Spellcaster spellcaster) {
            this.spellcaster = spellcaster;
        }

        @Override
        public void castSpell() {
            spellcaster.castSpell();
            System.out.println("Niewidzialność.");
        }
    }

    static class BootsOfSpeed implements Spellcaster{

        Spellcaster spellcaster;

        public BootsOfSpeed(Spellcaster spellcaster) {
            this.spellcaster = spellcaster;
        }

        @Override
        public void castSpell() {
            spellcaster.castSpell();
            System.out.println("Przyśpieszenie.");
        }
    }

    static class CloakOfDeflection implements Spellcaster{

        Spellcaster spellcaster;

        public CloakOfDeflection(Spellcaster spellcaster) {
            this.spellcaster = spellcaster;
        }

        @Override
        public void castSpell() {
            spellcaster.castSpell();
            System.out.println("Ochrona przed pociskami.");
        }
    }
}
