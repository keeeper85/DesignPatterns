package mvcpattern;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = controller.getView();

        model.addObserver(controller);
        model.addObserver(view);

        controller.inputCommand();
    }
}
