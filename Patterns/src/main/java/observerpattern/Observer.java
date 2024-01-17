package observerpattern;

import java.util.Observable;

public class Observer {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        Orc orc = new Orc();
        wizard.addObserver(new Cleric());
        wizard.addObserver(new Contingency());

        orc.meleeAttack(wizard);
        orc.meleeAttack(wizard);
        orc.meleeAttack(wizard);
    }

    static class Character extends Observable{
        int hp = 10;
        void takeDamage(int damage){
            hp -= damage;
            System.out.println(hp);
        }
    }

    static class Wizard extends Character {
        int stoneSkins = 1;

        @Override
        public void takeDamage(int damage) {
            if (stoneSkins > 0){
                System.out.println("Czarodziej: Kamienna skóra zatrzymała obrażenia. Aktualne punkty życia: " + hp + "hp.");
                stoneSkins--;
            }
            else {
                hp -= damage;
                System.out.println("Czarodziej: Jestem ranny! Zostało mi tylko " + hp + "hp!");
                setChanged();
                notifyObservers();
            }
        }
    }
    static class Cleric extends Character implements java.util.Observer {
        Spell spell = new HealingLesserWounds();
        void heal(Character character){
            System.out.println("Kleryk: Zajmę się tym!");
            System.out.println("Kleryk: Rzuca czar: " + spell.toString());
            spell.castSpell(character);
        }
        @Override
        public void update(Observable o, Object arg) {
            Character character = null;
            if (o instanceof Character) {
                character = (Character) o;
                heal(character);
            }
        }
        @Override
        public void takeDamage(int damage) {}
    }

    static class Orc extends Character{
        int damage = 7;
        void meleeAttack(Character character){
            Wizard wizard;
            if (character instanceof Wizard){
                wizard = (Wizard) character;
                if (wizard.stoneSkins > 0) {
                    System.out.println("Ork: Atakuje " + character.getClass().getSimpleName() + " za 0 obrażeń.");
                    character.takeDamage(0);
                }
                else {
                    System.out.println("Ork: Atakuje " + character.getClass().getSimpleName() + " za " + damage + " obrażeń.");
                    character.takeDamage(damage);
                }
            }
        }

        @Override
        public void takeDamage(int damage) {}
    }

    interface Spell{
        void castSpell(Character character);
        String toString();
    }

    static class Contingency implements Spell, java.util.Observer {
        Spell spell = new StoneSkin();
        @Override
        public void castSpell(Character character) {
            spell.castSpell(character);
            System.out.println("Warkunkowanie: Wyzwala czar " + spell.toString() + " na " +  character.getClass().getSimpleName());
        }
        @Override
        public void update(Observable o, Object arg) {
            Character character;
            if (o instanceof Character) {
                character = (Character) o;
                castSpell(character);
            }
        }
        @Override
        public String toString() {
            return "Warunkowanie";
        }
    }

    static class StoneSkin implements Spell{
        @Override
        public void castSpell(Character character) {
            Wizard wizard;
            if (character instanceof Wizard){
                wizard = (Wizard) character;
                wizard.stoneSkins = 4;
            }
        }
        @Override
        public String toString() {
            return "Kamienna skóra";
        }
    }

    static class HealingLesserWounds implements Spell{
        @Override
        public void castSpell(Character character) {
            character.hp += 7;
        }
        @Override
        public String toString() {
            return "Leczenie lekkich ran";
        }
    }
}
