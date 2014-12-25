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

Map<String, Map<String, ?>> activeOrdersBeforeTrade = tt.requestActiveOrders()
println "active orders before trade: ${activeOrdersBeforeTrade}"

Map<String, ?> placedOrder = tt.placeOrder("ltc_btc", TradeIntention.BUY, "0.00922", "1.6861734")
println "order id: ${placedOrder?.'order_id'}"
println "btce response: ${placedOrder}"


Map<String, Map<String, ?>> activeOrdersAfterTrade = tt.requestActiveOrders()
println "active orders after trade: ${activeOrdersAfterTrade}"


println tt.getFunds()