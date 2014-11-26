#!/usr/bin/env groovy
import by.groovycoin.btce.terminal.btceapi.trade.TradeIntention

/**
 * Created by Ales Pravdin on 10/7/14.
 */
@Grab('org.slf4j:slf4j-api:1.7.7')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
@Grab('by.groovycoin:btce-terminal:0.9.5g')

import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminal
import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminalManager

//For more information read readme.txt
System.setProperty("javax.net.ssl.trustStore", "${System.getProperty("user.home")}/.groovycoin/btce/btcetruststore")

TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("TradeTerminal")

println tt.getFunds()
//[btc:0.10413593, cnh:13.0339136, eur:0, ftc:0, gbp:0, ltc:0, nmc:0, nvc:0, ppc:0, rur:0, trc:0, usd:26.77249309, xpm:0]


Map<String, ?> placedOrder1 = tt.placeOrder("usd_cnh", TradeIntention.BUY, "5", "1")
if (placedOrder1 != null) {
    println "order id: ${placedOrder1?.'order_id'}"
    //order id: 466738791

}
    //null means error. Handle it!
else{
    String tradeRawResult = tt.getTradeLastRawResult()
    println tradeRawResult
}

Map<String, ?> placedOrder2 = tt.placeOrder("usd_cnh", TradeIntention.BUY, "5", "1")
if (placedOrder2 != null) {
    println "order id: ${placedOrder2?.'order_id'}"
    //order id: 466738793
}
//null means error. Handle it!
else{
    String tradeRawResult = tt.getTradeLastRawResult()
    println tradeRawResult
}

Map<String, ?> placedOrder3 = tt.placeOrder("usd_cnh", TradeIntention.BUY, "5", "1")
if (placedOrder3 != null) {
    println "order id: ${placedOrder3?.'order_id'}"
}
//null means error. Handle it!
else{
    String tradeRawResult = tt.getTradeLastRawResult()
    println tradeRawResult
    //[{"success":0,"error":"It is not enough CNH for purchase"}]
}