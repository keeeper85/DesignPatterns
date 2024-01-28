package compositepattern;

import java.util.ArrayList;
import java.util.List;

public class Composite {

    public static void main(String[] args) {
        CompositeSpell compositeSpell = new CompositeSpell();
        compositeSpell.spells.add(new FireShield());
        compositeSpell.spells.add(new StoneSkin());
        compositeSpell.spells.add(new SummonAirElemental());

        new Wizard().castSpell(compositeSpell);
    }
    interface Spell{
        void castSpell();
    }
    static class FireShield implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Ognista tarcza' ");
        }
    }
    static class StoneSkin implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Kamienna skóra' ");
        }
    }
    static class SummonAirElemental implements Spell{
        @Override
        public void castSpell() {
            System.out.print("'Przyzwanie żywiołaka powietrza' ");
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
    }
    static class Wizard{
        void castSpell(Spell spell){
            System.out.print("(Czarodziej) Rzuca czar(y): ");
            spell.castSpell();
        }
    }
}
