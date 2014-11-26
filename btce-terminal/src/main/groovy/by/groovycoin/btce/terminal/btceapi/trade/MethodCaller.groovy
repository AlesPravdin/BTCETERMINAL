package by.groovycoin.btce.terminal.btceapi.trade

/**
 * Created by Ales Pravdin on 10/15/14.
 */
public interface MethodCaller {
    String call(String data);

    String call(String data, Map params);
}