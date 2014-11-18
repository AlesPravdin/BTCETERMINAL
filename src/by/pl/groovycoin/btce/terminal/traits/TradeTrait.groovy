package by.pl.groovycoin.btce.terminal.traits

import by.pl.groovycoin.btce.terminal.TradeIntention
import by.pl.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/15/14.
 */
trait TradeTrait implements MethodCallerProvider {
    private final TraitBaseDelegate traitDelegate = new TraitBaseDelegate(this, "Trade")

    def placeOrder(
            final String symbol,
            final TradeIntention tradeIntention,
            final String rate,
            final String amount) {

        traitDelegate.request([
                "pair"  : symbol,
                "type"  : tradeIntention.strValue,
                "rate"  : rate,
                "amount": amount
        ])
    }

    String getTradeLastRawResult(){
        traitDelegate.getRaw()
    }
}

//'[{"success":1,"return":{"funds":{"usd":3.992,"btc":0.00998,"ltc":0.7984,"nmc":0,"rur":22.037368,"eur":0,"nvc":0,"trc":0,"ppc":0,"ftc":0,"xpm":0,"cnh":0,"gbp":0},"rights":{"info":1,"placeOrder":1,"withdraw":0},"transaction_count":0,"open_orders":0,"server_time":1413307113}}:null]'
