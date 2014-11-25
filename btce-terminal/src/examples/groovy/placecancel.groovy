#!/usr/bin/env groovy
import by.groovycoin.btce.terminal.btceapi.trade.TradeIntention

/**
 * Created by Ales Pravdin on 10/7/14.
 */
@Grab('org.slf4j:slf4j-api:1.7.7')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
@Grab('by.groovycoin:btce-terminal:0.9.54g')

import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminal
import by.groovycoin.btce.terminal.btceapi.trade.TradeTerminalManager

//For more information read readme.txt
System.setProperty("javax.net.ssl.trustStore", "${System.getProperty("user.home")}/.groovycoin/btce/btcetruststore")

TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("TradeTerminal")

println tt.getFunds()
//[btc:0.10413593, cnh:13.0339136, eur:0, ftc:0, gbp:0, ltc:0, nmc:0, nvc:0, ppc:0, rur:0, trc:0, usd:26.77249309, xpm:0]


Map<String, Map<String, ?>> activeOrdersBeforeTrade = tt.requestActiveOrders()
println "active orders before trade: ${activeOrdersBeforeTrade}"
//active orders before trade: null

Map<String, ?> placedOrder = tt.placeOrder("usd_cnh", TradeIntention.BUY, "5", "1")
println "order id: ${placedOrder?.'order_id'}"
//order id: 466XXX858

println "btce response: ${placedOrder}"
//btce response: [funds:[btc:0.10413593, cnh:8.0339136, eur:0, ftc:0, gbp:0, ltc:0, nmc:0, nvc:0, ppc:0, rur:0, trc:0, usd:26.77249309, xpm:0], order_id:466XXX858, received:0, remains:1]

//emulate some long activity on market
Thread.sleep(5000)

Map<String, Map<String, ?>> activeOrdersAfterTrade = tt.requestActiveOrders()
println "active orders after trade: ${activeOrdersAfterTrade}"
//active orders after trade: [466XXX858:[amount:1, pair:usd_cnh, rate:5, status:0, timestamp_created:1417XXXX99, type:buy]]

Map<String, ?> placeOrderInfo = activeOrdersAfterTrade[placedOrder.'order_id' as String]
println "After some time of market activity order state is: ${placeOrderInfo}"
//After some time of market activity order state is: [amount:1, pair:usd_cnh, rate:5, status:0, timestamp_created:1417XXXX99, type:buy]


if(placeOrderInfo == null){
    //Our order is no more on market. That means we were hit. Sell out to make balance neutral after tests.
    Map<String, ?> sellOutOrder = tt.placeOrder("usd_cnh", TradeIntention.SELL, "10", "1")
    println "Order ${placedOrder.'order_id'} was hit. Try to sell out: ${sellOutOrder}"
}
else{
    //Our order is still on market. Just cancel it
    def cancelResult = tt.cancelOrder(placedOrder.'order_id')
    println "Order ${placedOrder.'order_id'} was cancelled: ${cancelResult}"
    //Order 466XXX858 was cancelled: [btc:0.10413593, cnh:13.0339136, eur:0, ftc:0, gbp:0, ltc:0, nmc:0, nvc:0, ppc:0, rur:0, trc:0, usd:26.77249309, xpm:0]
}


println tt.getFunds()
//[btc:0.10413593, cnh:13.0339136, eur:0, ftc:0, gbp:0, ltc:0, nmc:0, nvc:0, ppc:0, rur:0, trc:0, usd:26.77249309, xpm:0]