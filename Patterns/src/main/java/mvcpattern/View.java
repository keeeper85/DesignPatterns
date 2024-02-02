package mvcpattern;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Model model;
    Controller controller;
    public View(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        showAvailableActions();
    }
    private void showResult(String action, int damage){
        if (!action.equals("wait")){
            if (damage == 0) System.out.println("Udało się " + action);
            else System.out.println("Nie udało się " + action + " Odniesiono obrażenia: " +  damage);
        }
        showResult();
        System.out.format("Pozostało %dhp (ty), %dhp (wróg). Szansa, że wróg zaatakuje wynosi %d procent.", model.getYourHp(), model.getEnemyHp(), model.getAttackChance());
        System.out.println();
        controller.inputCommand();
    }
    private void showAvailableActions(){
        System.out.println("Wpisz 'hit' jeśli chcesz zaatakować lub 'block' jeśli chcesz przygotować obronę.");
    }
    private void showResult(){
        int result = model.getScore();
        if (result != 0){
            if (result == 1) System.out.println("Zwycięstwo!");
            if (result == -1) System.out.println("Przegrana.");
            if (result == -2) System.out.println("Remis.");
            System.exit(0);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        showResult(model.getCurrentAction(), model.getCurentDamage());
    }
}
