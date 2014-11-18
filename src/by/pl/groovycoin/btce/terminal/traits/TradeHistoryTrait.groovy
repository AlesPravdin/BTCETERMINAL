package by.pl.groovycoin.btce.terminal.traits

import by.pl.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/16/14.
 */
trait TradeHistoryTrait implements MethodCallerProvider {

    private final HistoryTraitDelegate historyTrait = new HistoryTraitDelegate(this, "TradeHistory")

    def requestRawTradeHistory() { historyTrait.requestRaw() }

    def requestRawTradeHistory(Map params) { historyTrait.requestRaw(params) }

    def getRawTradeHistory() { historyTrait.getRaw() }

    def requestTradeHistory() { historyTrait.request() }

    def requestTradeHistory(Map params) { historyTrait.request(params) }

    def getTradeHistory() { historyTrait.getLast() }

    def getTradeHistory(Map params) { historyTrait.getLast(params) }

    def requestTradeHistory(int count) { historyTrait.request(["count":count]) }

    def getTradeHistory(int count) { historyTrait.getLast(["count":count]) }

    def requestTradeHistoryFromStartIndex(int count) {
        historyTrait.requestHistoryFromStartIndex(count)
    }

    def getTradeHistoryFromStartIndex(int count) {
        historyTrait.getHistoryFromStartIndex(count)
    }

    def requestLastTradeHistory(int count) {
        historyTrait.requestLastHistory(count)
    }

    def getLastTradeHistory(int count) {
        historyTrait.getLastHistory(count)
    }

    def requestLastTradeHistoryFromStartIndex(int count) {
        historyTrait.requestLastHistoryFromStartIndex(count)
    }

    def getLastTradeHistoryFromStartIndex(int count) {
        historyTrait.getLastHistoryFromStartIndex(count)
    }

    def requestTradeHistoryForPeriod(long since, long end) {
        historyTrait.requestHistoryForPeriod(since, end)
    }

    def getTradeHistoryForPeriod(long since, long end) {
        historyTrait.getHistoryForPeriod(since, end)
    }

    def requestTradeHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.requestHistoryForIdsRange(from_id, end_id)
    }

    def getTradeHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.getHistoryForIdsRange(from_id, end_id)
    }

    def requestLastTradeHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.requestLastHistoryForIdsRange(from_id, end_id)
    }

    def getLastTradeHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.getLastHistoryForIdsRange(from_id, end_id)
    }

    def requestPairTradeHistoryFromStartIndex(String pair, int count) {
        historyTrait.request(["pair":pair, "from": count, "order": "ASC"])
    }

    def getPairTradeHistoryFromStartIndex(String pair, int count) {
        historyTrait.getLast(["pair":pair, "from": count, "order": "ASC"])
    }

    def requestPairLastTradeHistory(String pair, int count) {
        historyTrait.request(["pair":pair, "count": count, "order": "DESC"])
    }

    def getPairLastTradeHistory(String pair, int count) {
        historyTrait.getLast(["pair":pair, "count": count, "order": "DESC"])
    }

    def requestPairLastTradeHistoryFromStartIndex(String pair, int count) {
        historyTrait.request(["pair":pair, "from": count, "order": "DESC"])
    }

    def getPairLastTradeHistoryFromStartIndex(String pair, int count) {
        historyTrait.getLast(["pair":pair, "from": count, "order": "DESC"])
    }

    def requestPairTradeHistoryForPeriod(String pair, long since, long end) {
        historyTrait.request(["pair":pair, "since": since, "end": end])
    }

    def getPairTradeHistoryForPeriod(String pair, long since, long end) {
        historyTrait.getLast(["pair":pair, "since": since, "end": end])
    }

    def requestPairTradeHistoryForIdsRange(String pair, long from_id, long end_id) {
        historyTrait.request(["pair":pair, "from_id": from_id, "end_id": end_id, "order": "ASC"])
    }

    def getPairTradeHistoryForIdsRange(String pair, long from_id, long end_id) {
        historyTrait.getLast(["pair":pair, "from_id": from_id, "end_id": end_id, "order": "ASC"])
    }

    def requestPairLastTradeHistoryForIdsRange(String pair, long from_id, long end_id) {
        historyTrait.request(["pair":pair, "from_id": from_id, "end_id": end_id, "order": "DESC"])
    }

    def getPairLastTradeHistoryForIdsRange(String pair, long from_id, long end_id) {
        historyTrait.getLast(["pair":pair, "from_id": from_id, "end_id": end_id, "order": "DESC"])
    }
}