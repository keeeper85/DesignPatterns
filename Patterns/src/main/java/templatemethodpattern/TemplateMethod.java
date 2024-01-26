package templatemethodpattern;

public class TemplateMethod {

    public static void main(String[] args) {

        Cleric cleric = new Cleric();
        BattlePreparation withVampires = new BattleWithVampires();
        BattlePreparation withUmberHulks = new BattleWithUmberHulks();
        BattlePreparation withMages = new BattleWithMages();

        cleric.macro(withVampires);
        System.out.println("-----------");
        cleric.macro(withUmberHulks);
        System.out.println("-----------");
        cleric.macro(withMages);

    }

    static abstract class BattlePreparation{

        public final void prepareForBattle(){
            macroName();
            summon();
            blessing();
            customDefensiveBuff();
            extraSpell();
        }

        protected abstract void macroName();

        protected void extraSpell() {

        }

        protected void summon(){
            System.out.println("(Kapłan) Rzuca czar: 'Przyzwanie powietrznego sługi'");
        }

        protected abstract void customDefensiveBuff();

        private void blessing() {
            System.out.println("(Kapłan) Rzuca czar: 'Błogosławieństwo'");
        }

    }

    static class BattleWithVampires extends BattlePreparation{

        @Override
        protected void customDefensiveBuff() {
            System.out.println("(Kapłan) Rzuca czar: 'Ochrona przed negatywną energią'");
        }

        @Override
        protected void macroName() {
            System.out.println("(Makro) Walka z Wampirami:");
        }

        @Override
        protected void summon() {
            System.out.println("(Kapłan) Korzysta ze zwoju: 'Przyzwanie zwierząt III'");
        }
    }

    static class BattleWithUmberHulks extends BattlePreparation{

        @Override
        protected void customDefensiveBuff() {
            System.out.println("(Kapłan) Rzuca czar: 'Chaotyczne rozkazy'");
        }

        @Override
        protected void extraSpell() {
            System.out.println("(Kapłan) Rzuca czar: 'Siła jedności'");
        }

        @Override
        protected void macroName() {
            System.out.println("(Makro) Walka z Umbrowymi kolosami:");
        }
    }

    static class BattleWithMages extends BattlePreparation{

        @Override
        protected void customDefensiveBuff() {
            System.out.println("(Kapłan) Rzuca czar: 'Osłona przed śmiercią'");
        }
        @Override
        protected void summon() {
            System.out.println("(Kapłan) Korzysta ze artefaktu: 'Przyzwanie kamiennego golema'");
        }

        @Override
        protected void macroName() {
            System.out.println("(Makro) Walka z magami:");
        }
    }

    static class Cleric{
        public void macro(BattlePreparation battlePreparation){
            battlePreparation.prepareForBattle();
        }
    }


}
