package org.example.strategypattern;

public class Strategy {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        Troll troll = new Troll();

        wizard.attack(troll);
        wizard.attack(troll);
        System.out.println("Zmiana strategii");
        wizard.spell = new FireArrow();
        wizard.attack(troll);
    }

    static class Wizard {

        Spell spell = new MagicMissle();

        void attack(Troll troll){
            int damage = spell.castSpell();
            troll.takeDamage(damage, spell);
        }

    }

    static class Troll {
        int hp = 10;
        boolean isFaint = false;

        void takeDamage(int damageTaken, Spell spell){
            hp -= damageTaken;
            if (!isFaint && hp <= 0) {
                System.out.println("Troll stracił przytomność!");
                isFaint = true;
            }
            else if (spell instanceof MagicMissle && isFaint) System.out.println("Ten czar nie działa! Aby pokonać trola potraktuj jego ciało ogniem albo kwasem!");
            else if (spell instanceof FireArrow && isFaint) System.out.println("Troll nie żyje!");
        }

    }

    interface Spell{
        int castSpell();
    }

    static class MagicMissle implements Spell{

        int damage = 3;
        @Override
        public int castSpell() {
            System.out.println("Magiczne pociski!");
            return doDamage(4);
        }

        private int doDamage(int missleCount){
            int totalDamage = 0;
            for (int i = 0; i < missleCount; i++) {
                System.out.println("Trafiony!");
                totalDamage += damage;
            }
            return totalDamage;
        }

    }

    static class FireArrow implements Spell{

        int damage = 10;
        @Override
        public int castSpell() {
            System.out.println("Ognista strzała!");
            return damage;
        }
    }
}
