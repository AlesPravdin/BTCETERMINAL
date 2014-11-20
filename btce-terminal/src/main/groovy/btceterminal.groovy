#!/usr/bin/env groovy
@Grab('org.slf4j:slf4j-api:1.7.6')
@Grab('ch.qos.logback:logback-core:1.1.1')
@Grab('ch.qos.logback:logback-classic:1.1.1')
@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7.1')
import by.groovycoin.btce.terminal.TradeTerminal
import by.groovycoin.btce.terminal.TradeTerminalManager

/**
 * Created by Ales Pravdin on 10/7/14.
 */

System.setProperty("javax.net.ssl.trustStore", "/System/Library/Java/Support/CoreDeploy.bundle/Contents/Home/lib/security/cacerts")

println "hello world"
TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("test1")
tt.init()
//TradeTerminal tt = new TradeTerminal("XRHUAED1-KTKK61XT-P3UEUP7F-0L34SAYV-UT9CR1GO", "66899aa402ac73d824c07580af8a7c63102bfe9e7c7665a70a5a749ae73f85a7")

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

def ltccnhOrders = tt.getActiveOrders('ltc_cnh')
println "ActiveOrders: ${ltccnhOrders}"

//ltccnhOrders.each{key, val -> println "${tt.cancelOrder(key)}"}

def usdcnhOrders = tt.getActiveOrders('usd_cnh')
println "ActiveOrders: ${usdcnhOrders}"
//usdcnhOrders.each{key, val -> println "${tt.cancelOrder(key)}"}

//println "Trade: ${tt.placeOrder('usd_cnh', TradeIntention.SELL, "6.75", "3")}"


println "Stop"
//println "${authenticatedHTTPRequest('requestRawInfo', null)}"