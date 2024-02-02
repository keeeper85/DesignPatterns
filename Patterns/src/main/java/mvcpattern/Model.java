package mvcpattern;

import java.util.Observable;

public class Model extends Observable {

    private int curentDamage;
    private String currentAction;
    private int yourHp = 100;
    private int enemyHp = 100;
    private int attackChance = 60;
    private int score = 0;

    public void processCommand(String command){
        if (command.equals("hit")) fight();
        else if (command.equals("block")) defend();
        else currentAction = "wait";
        updateScore();
        getSuccessChance();
        setChanged();
        notifyObservers();
    }
    private void fight(){
        currentAction = "zaatakować wroga";
        if (isEnemyAttacking()) {
            curentDamage = (int) (Math.random() * 20) + 1;
            yourHp -= curentDamage;
        }
        else {
            enemyHp -= (int) (Math.random() * 20) + 1;
            curentDamage = 0;
        }
    }
    private void defend(){
        currentAction = "obronić przed atakiem wroga";
        if (isEnemyAttacking()) curentDamage = 0;
        else {
            curentDamage = (int) (Math.random() * 10) + 1;
            yourHp -= curentDamage;
        }
    }
    private boolean isEnemyAttacking(){
        int chance = (int) (Math.random() * 100);
        if (chance < attackChance) return true;
        return false;
    }
    public void updateScore() {
        if (yourHp > 0 && enemyHp > 0) score = 0;
        else if (yourHp > 0 && enemyHp <= 0) score = 1;
        else if (yourHp <= 0 && enemyHp > 0) score = -1;
        else score = -2;
    }
    private void getSuccessChance(){
        attackChance = (int) (Math.random() * 100);
    }
    public int getCurentDamage() {
        return curentDamage;
    }
    public String getCurrentAction() {
        return currentAction;
    }
    public int getYourHp() {
        return yourHp;
    }
    public int getEnemyHp() {
        return enemyHp;
    }
    public int getAttackChance() {
        return attackChance;
    }
    public int getScore() {
        return score;
    }
}
