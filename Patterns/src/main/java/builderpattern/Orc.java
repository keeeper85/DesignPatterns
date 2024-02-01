package builderpattern;

public class Orc {
        int strength;
        int hp;
        double attackSpeed;
        String rank;
        Builder.Attack attack;

//        private Orc(int strength, int hp, double attackSpeed, String name, Builder.Attack attack) {
        private Orc(OrcBuilder orcBuilder) {
            this.strength = orcBuilder.strength;
            this.hp = orcBuilder.hp;
            this.attackSpeed = orcBuilder.attackSpeed;
            this.rank = orcBuilder.rank;
            this.attack = orcBuilder.attack;
        }

        public void enemyAttack(){
            attack.attack(strength, attackSpeed);
        }

    @Override
    public String toString() {
        return "Rasa: Ork," +
                " ranga: " + rank +
                ", siła: " + strength +
                ", punkty życia: " + hp +
                ", szybkość ataku: " + attackSpeed;

    }

    static abstract class OrcBuilder{
        private int strength = 10;
        private int hp = 50;
        private double attackSpeed = 1.00;
        private String rank = "zwykły ork";
        private Builder.Attack attack = new Builder.NormalAttack();
        public OrcBuilder setStrength(int strength) {
            this.strength = strength;
            return this;
        }
        public OrcBuilder setHp(int hp) {
            this.hp = hp;
            return this;
        }
        public OrcBuilder setAttackSpeed(double attackSpeed) {
            this.attackSpeed = attackSpeed;
            return this;
        }
        public OrcBuilder setRank(String rank) {
            this.rank = rank;
            return this;
        }
        public OrcBuilder setAttack(Builder.Attack attack) {
            this.attack = attack;
            return this;
        }
        public Orc build(){
            return new Orc(this);
        }
        }

        static class CustomOrcBuilder extends OrcBuilder{
        }

        static class OrcChieftainBuilder extends OrcBuilder{
            public Orc build(){
                super.build();
                setStrength(30);
                setHp(300);
                setAttackSpeed(2.50);
                setRank("Wódz orków");
                setAttack(new Builder.SpecialAttack());
                return new Orc(this);
            }
        }

}
