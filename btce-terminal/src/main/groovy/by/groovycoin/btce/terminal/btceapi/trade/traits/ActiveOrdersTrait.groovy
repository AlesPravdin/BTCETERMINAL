package by.groovycoin.btce.terminal.btceapi.trade.traits

import by.groovycoin.btce.terminal.btceapi.trade.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/15/14.
 */
trait ActiveOrdersTrait implements MethodCallerProvider {
    private final TraitBaseDelegate traitDelegate = new TraitBaseDelegate(this, "ActiveOrders")

    String requestRawActiveOrders() { traitDelegate.requestRaw() }

    String requestRawActiveOrders(Map params) { traitDelegate.requestRaw(params) }

    String getRawActiveOrders() { traitDelegate.getRaw() }


    Map<String, Map<String, ?>> requestActiveOrders() { traitDelegate.request() }

    Map<String, Map<String, ?>> requestActiveOrders(Map params) { traitDelegate.request(params) }

    Map<String, Map<String, ?>> getActiveOrders() { traitDelegate.getLast() }

    Map<String, Map<String, ?>> getActiveOrders(Map params) { traitDelegate.getLast(params) }


    Map<String, Map<String, ?>> requestActiveOrders(def symbol) { requestActiveOrders(["pair": symbol.toLowerCase()]) }

    Map<String, Map<String, ?>> getActiveOrders(def symbol) { getActiveOrders(["pair": symbol.toLowerCase()]) }

}

//'[{"success":1,"return":{"funds":{"usd":3.992,"btc":0.00998,"ltc":0.7984,"nmc":0,"rur":22.037368,"eur":0,"nvc":0,"trc":0,"ppc":0,"ftc":0,"xpm":0,"cnh":0,"gbp":0},"rights":{"info":1,"placeOrder":1,"withdraw":0},"transaction_count":0,"open_orders":0,"server_time":1413307113}}:null]'
