package compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Composite {

    public static void main(String[] args) {
        CompositeSpell compositeSpell = new CompositeSpell();
        compositeSpell.spells.add(new FireShield());
        compositeSpell.spells.add(new StoneSkin());
        compositeSpell.spells.add(new SummonAirElemental());

        Wizard wizard = new Wizard();
        wizard.castSpell(compositeSpell);
        wizard.unsummon();
    }
    interface Spell{
        void castSpell();
        void unsummon();
    }
    static class SummonAirElemental implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Przyzwanie żywiołaka powietrza' ");
        }

        @Override
        public void unsummon() {
            System.out.println("Żywiołak powietrza - odesłano. ");
        }
    }
    static class FireShield implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Ognista tarcza' ");
        }

        @Override
        public void unsummon() {
            throw new UnsupportedOperationException();
        }
    }
    static class StoneSkin implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Kamienna skóra' ");
        }

        @Override
        public void unsummon() {
            throw new UnsupportedOperationException();
        }
    }
    static class CompositeSpell implements Spell{
        List<Spell> spells = new ArrayList<>();
        @Override
        public void castSpell() {
            for (Spell spell : spells) {
                spell.castSpell();
            }
        }
        @Override
        public void unsummon() {
            for (Spell spell : spells) {
                try{
                    spell.unsummon();
                } catch (UnsupportedOperationException ignored){}
            }
        }
    }
    static class Wizard{
        Spell spell;
        void castSpell(Spell spell){
            this.spell = spell;
            System.out.print("(Czarodziej) Rzuca czar(y): ");
            spell.castSpell();
        }
        void unsummon(){
            System.out.print("\n(Czarodziej) ");
            spell.unsummon();
        }
    }
}
