package ru.vsu.cs.common;

public enum PaymentMethod {
    DIFFERENTIATED_PAYMENT{
        public DifferentiatedCredit action() {return new DifferentiatedCredit();}
    },
    ANNUITY_PAYMENT{
        public AnnuityCredit action() {return new AnnuityCredit();}
    };
    public abstract CommonCredit action();
}

