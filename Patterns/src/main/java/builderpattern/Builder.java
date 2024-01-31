package builderpattern;

public class Builder {
    public static void main(String[] args) {

        Orc orc = new Orc.CustomOrcBuilder().build();
        System.out.println(orc.toString());
        orc.enemyAttack();

        System.out.println("-------------");

        Orc orcArcher = new Orc.CustomOrcBuilder()
                .setRank("ork łucznik")
                .setStrength(25)
                .setAttackSpeed(0.7)
                .setAttack((strength, attackSpeed) -> {System.out.println("Strzela z łuku za " + (int) (1 + strength * attackSpeed * Math.random()) + " obrażeń.");})
                .build();
        System.out.println(orcArcher.toString());
        orcArcher.enemyAttack();

        System.out.println("-------------");

        Orc orcChieftain = new Orc.OrcChieftainBuilder().build();
        System.out.println(orcChieftain.toString());
        orcChieftain.enemyAttack();

    }

    interface Attack{
        void attack(int strength, double attackSpeed);
    }

    static class NormalAttack implements Attack{

        @Override
        public void attack(int strength, double attackSpeed) {
            int damage = (int) (strength * attackSpeed * Math.random()) + 1;
            System.out.println("Atakuje za " + damage + " punktów obrażeń.");
        }
    }

    static class SpecialAttack implements Attack{

        @Override
        public void attack(int strength, double attackSpeed) {
            int damage = (int) (strength * attackSpeed * Math.random()) + 10;
            System.out.println("Używa ataku specjalnego za " + damage + " punktów obrażeń.");
        }
    }
}
