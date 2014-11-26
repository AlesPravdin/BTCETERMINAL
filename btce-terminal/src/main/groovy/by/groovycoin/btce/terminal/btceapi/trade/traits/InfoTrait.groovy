package by.groovycoin.btce.terminal.btceapi.trade.traits

import by.groovycoin.btce.terminal.btceapi.trade.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/15/14.
 */
trait InfoTrait implements MethodCallerProvider {
    private final TraitBaseDelegate traitDelegate = new TraitBaseDelegate(this, "getInfo")

    def requestRawInfo() { traitDelegate.requestRaw() }

    def getRawInfo() { traitDelegate.getRaw() }


    def requestInfo() { traitDelegate.request() }

    def getInfo() { traitDelegate.getLast() }

    def getLastRawInfoParsed() { traitDelegate.getLastRawParsed() }

    def requestFundsInfo() { requestInfo().'funds' }

    def getFunds() {
        def info = getInfo()
        //println "funds ${Thread.currentThread().getName()} >>>>>>>> ${info}"
        info.'funds'
    }

    def requestFundsInfo(def symbol) { requestFundsInfo()."${symbol.toLowerCase()}" }

    def getFund(def symbol) { getFundsInfo()."${symbol.toLowerCase()}" }

    def requestRightsInfo() { requestInfo().'rights' }

    def getRight() { getInfo().'rights' }

    def requestServerTime() { requestInfo().'server_time' }

    def getServerTime() { getInfo().'server_time' }

    def requestTransactionCount() { requestInfo().'transaction_count' }

    def getTransactionCount() { getInfo().'transaction_count' }

    def requestOpenOrders() { requestInfo().'open_orders' }

    def getOpenOrders() { getInfo().'open_orders' }
}

//'[{"success":1,"return":{"funds":{"usd":3.992,"btc":0.00998,"ltc":0.7984,"nmc":0,"rur":22.037368,"eur":0,"nvc":0,"trc":0,"ppc":0,"ftc":0,"xpm":0,"cnh":0,"gbp":0},"rights":{"info":1,"placeOrder":1,"withdraw":0},"transaction_count":0,"open_orders":0,"server_time":1413307113}}:null]'
