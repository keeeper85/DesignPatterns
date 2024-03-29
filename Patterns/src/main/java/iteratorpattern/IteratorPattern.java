package iteratorpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args) {
        WeaponShop weaponShop = new WeaponShop();
        ElixirStore elixirStore = new ElixirStore();

//        for (String elixir : elixirStore.elixirs) {
//            System.out.println(elixir);
//        }
//
//        for (String weapon : weaponShop.weapons) {
//            System.out.println(weapon);
//        }

        new GeneralStore(elixirStore.getIterator(), weaponShop.getIterator()).printOffer();
    }
    static class GeneralStore{
        Iterator<?>[] iterators;

        public GeneralStore(Iterator<?>... iterators) {
            this.iterators = iterators;
        }
        void printOffer(){
            for (Iterator<?> iterator : iterators) {
                printOffer(iterator);
            }
        }
        void printOffer(Iterator<?> iterator){
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }
    static class ElixirStore{
        String[] elixirs = new String[6];
        public ElixirStore() {
            elixirs[0] = "Mała mikstura regeneracji zdrowia";
            elixirs[1] = "Średnia mikstura regeneracji zdrowia";
            elixirs[2] = "Duża mikstura regeneracji zdrowia";
            elixirs[3] = "Mała mikstura regeneracji many";
            elixirs[4] = "Średnia mikstura regeneracji many";
            elixirs[5] = "Duża mikstura regeneracji many";
        }
        Iterator<String> getIterator(){
            return new ElixirStoreIterator(elixirs);
        }
    }
    static class ElixirStoreIterator implements Iterator<String>{
        String[] elixirs;
        int position = 0;
        public ElixirStoreIterator(String[] elixirs) {
            this.elixirs = elixirs;
        }
        @Override
        public boolean hasNext() {
            if (position >= elixirs.length || elixirs[position] == null) return false;
            return true;
        }
        @Override
        public String next() {
            String elixir = elixirs[position];
            position += 1;
            return elixir;
        }
    }
    static class WeaponShop{
        List<String> weapons = new ArrayList<>();
        public WeaponShop() {
            for (int i = 0; i < 10; i++) {
                generateAndAddWeapon();
            }
        }
        void generateAndAddWeapon(){
            int random = (int) ((Math.random() * 10) % 3); ;
            int power = (int) (Math.random() * 20) + 1;
            String weapon;

            if (random == 2){
                weapon = "Kostur czarodzieja (inteligencja + " + power + ")";
                weapons.add(weapon);
            }
            else if (random == 1){
                weapon = "Łuk kompozytowy (zręczność + " + power + ")";
                weapons.add(weapon);
            }
            else{
                weapon = "Miecz dwuręczny (siła + " + power + ")";
                weapons.add(weapon);
            }
        }
        Iterator<String> getIterator(){
            return weapons.iterator();
        }
    }
}
