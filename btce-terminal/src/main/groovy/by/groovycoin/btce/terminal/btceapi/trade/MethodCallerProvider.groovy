package by.groovycoin.btce.terminal.btceapi.trade
/**
 * Created by Ales Pravdin on 10/23/14.
 */
interface MethodCallerProvider {
    MethodCaller getCaller()

    String getName()
}