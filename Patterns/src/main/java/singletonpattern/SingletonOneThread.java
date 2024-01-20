package singletonpattern;

public class SingletonOneThread {

    public static void main(String[] args) {
        Artifact staff1 = new WoodenStaff();
        Artifact staff2 = new WoodenStaff();
        Artifact stick1 = TheStickOfTruth.getInstance();
        Artifact stick2 = TheStickOfTruth.getInstance();
        staff1.showStats();
        staff2.showStats();
        stick1.showStats();
        stick2.showStats();
    }
    interface Artifact{
        void showStats();
    }
    static class WoodenStaff implements Artifact{

        private String name = "Drewniany kostur";
        int bonusIntelligence, bonusMana, bonusSpellCastSpeed, cost;
        public WoodenStaff() {
            bonusIntelligence = (int) (10 * Math.random());
            bonusMana = (int) (100 * Math.random());
            bonusSpellCastSpeed = (int) (20 * Math.random());
            cost = (int) (200 * Math.random()) + 100;
        }

        @Override
        public void showStats() {
            System.out.println(name +": Inteligencja +" + bonusIntelligence + ", Mana +"
                    + bonusMana + ", szybkość rzucania czarów: +" + bonusSpellCastSpeed + "%. Koszt: " + cost);
        }
    }

    static class TheStickOfTruth implements Artifact{
        private static TheStickOfTruth theStickOfTruth;
        private String name = "Kijek prawdy";
        int bonusIntelligence, bonusMana, bonusSpellCastSpeed, cost;
        private TheStickOfTruth() {
            bonusIntelligence = (int) (20 * Math.random());
            bonusMana = (int) (200 * Math.random());
            bonusSpellCastSpeed = (int) (40 * Math.random());
            cost = (int) (2000 * Math.random()) + 1000;
        }
        @Override
        public void showStats() {
            System.out.println(name +": Inteligencja +" + bonusIntelligence + ", Mana +"
                    + bonusMana + ", szybkość rzucania czarów: +" + bonusSpellCastSpeed + "%. Koszt: " + cost);
        }

        public static TheStickOfTruth getInstance(){
            if (theStickOfTruth == null) theStickOfTruth = new TheStickOfTruth();
            return theStickOfTruth;
        }
    }
}
