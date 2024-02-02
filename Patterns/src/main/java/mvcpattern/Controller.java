package mvcpattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(model, this);
    }
    public void inputCommand() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            model.processCommand(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Observable o, Object arg) {
    }
    public View getView() {
        return view;
    }
}
