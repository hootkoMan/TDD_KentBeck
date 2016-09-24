public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money frank(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
            && currency().equals(money.currency());
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    protected String currency() {
        return currency;
    }

    Expression plus(Money added) {
        return new Sum(this, added);
    }

    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    @Override
    public String toString() {
        return "Money{" +
            "amount=" + amount +
            ", currency='" + currency + '\'' +
            '}';
    }
}
