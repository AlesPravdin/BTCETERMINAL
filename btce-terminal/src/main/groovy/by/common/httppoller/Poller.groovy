package by.common.httppoller

import groovy.transform.CompileStatic

/**
 * Created by Ales Pravdin on 12/18/14.
 */
@CompileStatic
interface Poller {
    String poll()

    String poll(final String path)
}