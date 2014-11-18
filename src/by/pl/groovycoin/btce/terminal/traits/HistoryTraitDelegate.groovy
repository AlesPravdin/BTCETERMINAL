package by.pl.groovycoin.btce.terminal.traits

import by.pl.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/16/14.
 */
class HistoryTraitDelegate extends TraitBaseDelegate{

    HistoryTraitDelegate(
            final MethodCallerProvider methodCallerProvider, final String methodName) {

        super(methodCallerProvider, methodName)

    }

    def requestHistory(int count) {
        request(["count": count, "order": "ASC"])
    }

    def getHistory(int count) {
        getLast(["count": count, "order": "ASC"])
    }

    def requestHistoryFromStartIndex(int count) {
        request(["from": count, "order": "ASC"])
    }

    def getHistoryFromStartIndex(int count) {
        getLast(["from": count, "order": "ASC"])
    }

    def requestLastHistory(int count) {
        request(["count": count, "order": "DESC"])
    }

    def getLastHistory(int count) {
        getLast(["count": count, "order": "DESC"])
    }

    def requestLastHistoryFromStartIndex(int count) {
        request(["from": count, "order": "DESC"])
    }

    def getLastHistoryFromStartIndex(int count) {
        getLast(["from": count, "order": "DESC"])
    }

    def requestHistoryForPeriod(long since, long end) {
        request(["since": since, "end": end])
    }

    def getHistoryForPeriod(long since, long end) {
        getLast(["since": since, "end": end])
    }

    def requestHistoryForIdsRange(long from_id, long end_id) {
        request(["from_id": from_id, "end_id": end_id, "order": "ASC"])
    }

    def getHistoryForIdsRange(long from_id, long end_id) {
        getLast(["from_id": from_id, "end_id": end_id, "order": "ASC"])
    }

    def requestLastHistoryForIdsRange(long from_id, long end_id) {
        request(["from_id": from_id, "end_id": end_id, "order": "DESC"])
    }

    def getLastHistoryForIdsRange(long from_id, long end_id) {
        getLast(["from_id": from_id, "end_id": end_id, "order": "DESC"])
    }
}