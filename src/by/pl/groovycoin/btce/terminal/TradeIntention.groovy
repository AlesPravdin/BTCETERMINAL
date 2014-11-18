package by.pl.groovycoin.btce.terminal

/**
 * Created by Ales Pravdin on 10/26/14.
 */
public enum TradeIntention {
    BUY("buy"),
    SELL("sell")

    private final String strValue

    TradeIntention(final String buy) {

        this.strValue = buy
    }

    String getStrValue() {
        return strValue
    }
}