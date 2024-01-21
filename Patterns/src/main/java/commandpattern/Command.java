package commandpattern;

import java.util.Stack;

public class Command {

    public static void main(String[] args) {

        Wizard wizard = new Wizard();
        IceGiant iceGiant = new IceGiant();
        FireBall fireBall = new FireBall();
        Paralyze paralyze = new Paralyze();

        wizard.currentCommand = fireBall;
        wizard.castSpell(iceGiant);
        wizard.currentCommand = paralyze;
        wizard.castSpell(iceGiant);
        wizard.currentCommand = fireBall;
        wizard.castSpell(iceGiant);
        wizard.loadGame();

//        wizard.currentCommand = fireBall;
//        wizard.addToSequencer(iceGiant);
//        wizard.currentCommand = paralyze;
//        wizard.addToSequencer(iceGiant);
//        wizard.currentCommand = fireBall;
//        wizard.addToSequencer(iceGiant);
//        wizard.fireSequencer();

    }

    static class Wizard{
        Enemy currentEnemy;
        Commands currentCommand;
        Stack<Commands> allCommands = new Stack<>();

        void castSpell(Enemy enemy){
            currentEnemy = enemy;
            System.out.print("Czarodziej rzuca czar: ");
            currentCommand.engage(enemy);
            allCommands.add(currentCommand);
        }

        void loadGame(){
            for (Commands command : allCommands) {
                command.revert();
            }
            System.out.println("Gra została wczytana.");
            System.out.println(currentEnemy);
        }

        void addToSequencer(Enemy enemy){
            currentEnemy = enemy;
            allCommands.add(currentCommand);
        }

        void fireSequencer(){
            for (Commands command : allCommands) {
                command.engage(currentEnemy);
            }
        }
    }

    static abstract class Enemy{
        int hp = 120;
        String status = "Brak negatywnych efektów";
    }

    static class IceGiant extends Enemy{

        @Override
        public String toString() {
            return "Lodowy Gigant (" +
                    "hp = " + hp +
                    ", status = '" + status + '\'' +
                    ")";
        }
    }

    interface Commands{
        void engage(Enemy enemy);
        void revert();
    }

    static class FireBall implements Commands{

        Enemy enemy;

        @Override
        public void engage(Enemy enemy) {
            this.enemy = enemy;
            System.out.println("Kula ognia.");
            enemy.hp -= 33;
            System.out.println(enemy);
        }

        @Override
        public void revert() {
            enemy.hp += 33;
        }
    }

    static class Paralyze implements Commands{

        Enemy enemy;
        String status;

        @Override
        public void engage(Enemy enemy) {
            this.enemy = enemy;
            System.out.println("Unieruchomienie!");
            this.status = enemy.status;
            enemy.status = "sparaliżowany";
            System.out.println(enemy);
        }

        @Override
        public void revert() {
            enemy.status = status;
        }
    }
}
