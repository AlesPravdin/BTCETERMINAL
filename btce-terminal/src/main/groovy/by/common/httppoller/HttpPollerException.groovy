package by.common.httppoller

import groovy.transform.CompileStatic

/**
 * Created by Ales Pravdin on 12/18/14.
 */
@CompileStatic
class HttpPollerException extends Exception{

    HttpPollerException() {
    }

    HttpPollerException(String var1) {
        super(var1)
    }

    HttpPollerException(String var1, Throwable var2) {
        super(var1, var2)
    }

    HttpPollerException(Throwable var1) {
        super(var1)
    }

    HttpPollerException(String var1, Throwable var2, boolean var3, boolean var4) {
        super(var1, var2, var3, var4)
    }
}
