package by.groovycoin.btce.terminal.traits

import by.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/15/14.
 */
trait ActiveOrdersTrait implements MethodCallerProvider {
    private final TraitBaseDelegate traitDelegate = new TraitBaseDelegate(this, "ActiveOrders")

    def requestRawActiveOrders() { traitDelegate.requestRaw() }

    def requestRawActiveOrders(Map params) { traitDelegate.requestRaw(params) }

    def getRawActiveOrders() { traitDelegate.getRaw() }


    def requestActiveOrders() { traitDelegate.request() }

    def requestActiveOrders(Map params) { traitDelegate.request(params) }

    def getActiveOrders() { traitDelegate.getLast() }

    def getActiveOrders(Map params) { traitDelegate.getLast(params) }


    def requestActiveOrders(def symbol) { requestActiveOrders(["pair": symbol.toLowerCase()]) }

    def getActiveOrders(def symbol) { getActiveOrders(["pair": symbol.toLowerCase()]) }

}

//'[{"success":1,"return":{"funds":{"usd":3.992,"btc":0.00998,"ltc":0.7984,"nmc":0,"rur":22.037368,"eur":0,"nvc":0,"trc":0,"ppc":0,"ftc":0,"xpm":0,"cnh":0,"gbp":0},"rights":{"info":1,"placeOrder":1,"withdraw":0},"transaction_count":0,"open_orders":0,"server_time":1413307113}}:null]'
