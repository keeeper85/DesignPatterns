package proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {

        Character wizard = new Wizard();
        Character rogue = new Rogue();
        Treasure treasure = new Chest();

        Treasure lockedTreasure = (Treasure) Proxy.newProxyInstance(
                treasure.getClass().getClassLoader(),
                treasure.getClass().getInterfaces(),
                new LockedChestHandler(treasure)
        );

        lockedTreasure.open(wizard);
        lockedTreasure.open(rogue);
    }
    interface Character {}
    static class Wizard implements Character {
        @Override
        public String toString() {
            return "(Czarodziej)";
        }
    }
    static class Rogue implements Character {
        @Override
        public String toString() {
            return "(Łotrzyk)";
        }
    }
    interface Treasure {
        void open(Character character);
    }
    static class Chest implements Treasure {
        @Override
        public void open(Character character) {
            int goldAmount = (int) (Math.random() * 100 + 10);
            System.out.println(character.toString() + " Skrzynia otwarta. Zdobyto: " + goldAmount + " sztuk złota.");
        }
    }
    static class LockedChestHandler implements InvocationHandler {

        Treasure treasure;
        public LockedChestHandler(Treasure treasure) {
            this.treasure = treasure;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("open")) {
                Character character = (Character) args[0];
                if (character instanceof Rogue) {
                    System.out.println(character + " Udało się otworzyć zamek!");
                    return method.invoke(treasure, args);
                } else {
                    System.out.println(character + " Skrzynia zamknięta. Potrzebujesz klucza albo wytrychów.");
                    return null;
                }
            }
            return null;
        }
    }
}
