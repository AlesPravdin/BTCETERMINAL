#!/usr/bin/env groovy
@Grab('org.slf4j:slf4j-api:1.7.6')
@Grab('ch.qos.logback:logback-core:1.1.1')
@Grab('ch.qos.logback:logback-classic:1.1.1')
@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.7.1')
@Grab('by.groovycoin:btce-terminal:0.9.2')

import by.groovycoin.btce.terminal.TradeTerminal
import by.groovycoin.btce.terminal.TradeTerminalManager

/**
 * Created by Ales Pravdin on 10/7/14.
 */

System.setProperty("javax.net.ssl.trustStore", "/System/Library/Java/Support/CoreDeploy.bundle/Contents/Home/lib/security/cacerts")

TradeTerminalManager tradeTerminalManager = new TradeTerminalManager("../../main/resources/apikeys.json")
TradeTerminal tt = tradeTerminalManager.getTerminal("TradeTerminal")
tt.init()
//TradeTerminal tt = new TradeTerminal("XRHUAED1-KTKK61XT-P3UEUP7F-0L34SAYV-UT9CR1GO", "66899aa402ac73d824c07580af8a7c63102bfe9e7c7665a70a5a749ae73f85a7")

println tt.getFunds()

println "Stop"