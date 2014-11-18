package by.pl.groovycoin.btce.terminal
/**
 * Created by Ales Pravdin on 10/23/14.
 */
interface MethodCallerProvider {
    MethodCaller getCaller()

    String getName()
}