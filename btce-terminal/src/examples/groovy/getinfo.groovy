#!/usr/bin/env groovy

/**
 * Created by Ales Pravdin on 10/7/14.
 */

@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
@Grab('by.groovycoin:btce-terminal:0.9.31')

import by.groovycoin.btce.terminal.TradeTerminal
import by.groovycoin.btce.terminal.TradeTerminalManager

System.setProperty("javax.net.ssl.trustStore", "${System.getProperty("user.home")}/.groovycoin/btce/btcetruststore")

TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("TradeTerminal")

println tt.getFunds()