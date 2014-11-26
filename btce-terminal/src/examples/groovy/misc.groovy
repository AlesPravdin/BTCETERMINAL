#!/usr/bin/env groovy

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
println tt.requestRawInfo()
println tt.requestFundsInfo()
println tt.requestFundsInfo("BTC")
println tt.requestRightsInfo()
println tt.requestServerTime()
println tt.requestTransactionCount()
println tt.requestOpenOrders()
println tt.funds

//println "transHistory: ${tt.transHistory}"
println "transHistory 3: ${tt.requestTransHistory()}"
println "transHistory 1: ${tt.getLastTransHistory(1)}"
println "transHistory range: ${tt.getTransHistoryForIdsRange(853711327, 853717851)}"
//println "transHistory range: ${tt.getLastTransHistoryForIdsRange(853717851, 853711327)}"
println "transHistory period: ${tt.getTransHistoryForPeriod(System.currentTimeMillis() / 1000 - 60 * 60 * 24 * 30 as long, System.currentTimeMillis() / 1000 as long)}"

println "tradeHistory 3: ${tt.requestTradeHistory()}"
println "tradeHistory 1: ${tt.getLastTradeHistory(2)}"
println "tradeHistory usd_rur: ${tt.getPairLastTradeHistory("usd_rur", 2)}"
//println "tradeHistory range: ${tt.getTradeHistoryForIdsRange(390533035, 390537099)}"
//println "tradeHistory range: ${tt.getLastTradeHistoryForIdsRange(390553947, 420295789)}"
println "tradeHistory period: ${tt.getTradeHistoryForPeriod(System.currentTimeMillis() / 1000 - 60 * 60 * 24 * 30 as long, System.currentTimeMillis() / 1000 as long)}"


println "ActiveOrders: ${tt.getActiveOrders()}"

println "ActiveOrders: ${tt.getActiveOrders('ltc_cnh')}"

//ltccnhOrders.each{key, val -> println "${tt.cancelOrder(key)}"}

println "ActiveOrders: ${tt.getActiveOrders('usd_cnh')}"