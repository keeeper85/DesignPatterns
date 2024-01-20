package singletonpattern;

public class SingletonTwoThreads {

    public static void main(String[] args) {

        Thread wizard = new Wizard();
        Thread druid = new Druid();

        wizard.start();
        druid.start();

    }

    interface Shopping{
        void buy();
        void showInventory();
    }

    static class Wizard extends Thread implements Shopping{

        Artifact artifact = new WoodenStaff();

        @Override
        public void run() {
            buy();
            showInventory();
        }

        @Override
        public void buy() {
            Artifact artifact = TheStickOfTruth.getInstance();
            if (!artifact.isSold()){
                System.out.println("(Czarodziej) Udało się! Mam kijek prawdy!");
                this.artifact = artifact;
                artifact.setSold();
            }
            else System.out.println("(Czarodziej) Cholera, już ktoś kupił!");
        }

        @Override
        public void showInventory() {
            System.out.println("(Czarodziej) Moja obecna broń to: " + artifact.getName());
        }
    }

    static class Druid extends Thread implements Shopping{

        Artifact artifact = new WoodenStaff();

        @Override
        public void run() {
            buy();
            showInventory();
        }

        @Override
        public void buy() {
            Artifact artifact = TheStickOfTruth.getInstance();
            if (!artifact.isSold()){
                System.out.println("(Druid) Udało się! Mam kijek prawdy!");
                this.artifact = artifact;
                artifact.setSold();
            }
            else System.out.println("(Druid) Cholera, już ktoś kupił!");
        }

        @Override
        public void showInventory() {
            System.out.println("(Druid) Moja obecna broń to: " + artifact.getName());
        }
    }
    interface Artifact{
        boolean isSold();
        void setSold();
        String getName();
    }

    static class TheStickOfTruth implements Artifact {
        private static TheStickOfTruth theStickOfTruth;
        public String name = "Kijek prawdy";
        boolean sold = false;

        private TheStickOfTruth() {
        }

        public static TheStickOfTruth getInstance(){
            if (theStickOfTruth == null) theStickOfTruth = new TheStickOfTruth();
            return theStickOfTruth;
        }

        @Override
        public boolean isSold() {
            return sold;
        }

        @Override
        public void setSold() {
            sold = true;
        }

        @Override
        public String getName() {
            return name;
        }

    }

    static class WoodenStaff implements Artifact{
        public String name = "Drewniany kostur";

        @Override
        public boolean isSold() {
            return false;
        }
        @Override
        public void setSold() {

        }
        @Override
        public String getName() {
            return name;
        }
    }


}
