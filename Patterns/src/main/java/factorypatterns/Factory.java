package factorypatterns;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static void main(String[] args) {

        Summoner druid = new Druid();
        Summoner wizard = new Wizard();

        druid.summon();
        wizard.summon();

    }

    interface Summoner{
        void summon();
    }

    static class Wizard implements Summoner{

        @Override
        public void summon() {
            System.out.print("(Czarodziej) ");
            WizardSummonFactory factory = new WizardSummonFactory();
            factory.summonAllies();
        }
    }

    static class Druid implements Summoner{

        @Override
        public void summon() {
            System.out.print("(Druid) ");
            DruidSummonFactory factory = new DruidSummonFactory();
            factory.summonAllies();
        }
    }


    static abstract class SummonFactory{  // abstract factory interface
        List<SummonedAlly> summons = new ArrayList<>();

        void summonAllies(){
            createSummons();
            System.out.println("Przywołano:");
            for (SummonedAlly summon : summons) {
                summon.introduceSelf();
            }
        }
        abstract void createSummons(); //factory method
    }

    static class WizardSummonFactory extends SummonFactory{
        @Override
        void createSummons() {
            summons.add(new FireElemental());
        }
    }

    static class DruidSummonFactory extends SummonFactory{
        @Override
        void createSummons() {
            summons.add(new DireWolf());
            summons.add(new DireWolf());
            summons.add(new DireWolf());
        }
    }

    interface SummonedAlly{
        void introduceSelf();
    }

    static class FireElemental implements SummonedAlly{
        @Override
        public void introduceSelf() {
            System.out.println("- Żywiołak ognia");
        }
    }
    static class DireWolf implements SummonedAlly{
        @Override
        public void introduceSelf() {
            System.out.println("- Ogromny wilk.");
        }
    }
}
