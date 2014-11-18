package by.pl.groovycoin.btce.terminal.traits

import by.pl.groovycoin.btce.terminal.MethodCallerProvider

/**
 * Created by Ales Pravdin on 10/16/14.
 */
trait TransHistoryTrait implements MethodCallerProvider {

    private final HistoryTraitDelegate historyTrait = new HistoryTraitDelegate(this, "TransHistory")

    def requestRawTransHistory() { historyTrait.requestRaw() }

    def requestRawTransHistory(Map params) { historyTrait.requestRaw(params) }

    def getRawTransHistory() { historyTrait.getRaw() }

    def requestTransHistory() { historyTrait.request() }

    def requestTransHistory(Map params) { historyTrait.request(params) }

    def getTransHistory() { historyTrait.getLast() }

    def getTransHistory(Map params) { historyTrait.getLast(params) }

    def requestTransHistory(int count) { historyTrait.request(["count":count]) }

    def getTransHistory(int count) { historyTrait.getLast(["count":count]) }

    def requestTransHistoryFromStartIndex(int count) {
        historyTrait.requestHistoryFromStartIndex(count)
    }

    def getTransHistoryFromStartIndex(int count) {
        historyTrait.getHistoryFromStartIndex(count)
    }

    def requestLastTransHistory(int count) {
        historyTrait.requestLastHistory(count)
    }

    def getLastTransHistory(int count) {
        historyTrait.getLastHistory(count)
    }

    def requestLastTransHistoryFromStartIndex(int count) {
        historyTrait.requestLastHistoryFromStartIndex(count)
    }

    def getLastTransHistoryFromStartIndex(int count) {
        historyTrait.getLastHistoryFromStartIndex(count)
    }

    def requestTransHistoryForPeriod(long since, long end) {
        historyTrait.requestHistoryForPeriod(since, end)
    }

    def getTransHistoryForPeriod(long since, long end) {
        historyTrait.getHistoryForPeriod(since, end)
    }

    def requestTransHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.requestHistoryForIdsRange(from_id, end_id)
    }

    def getTransHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.getHistoryForIdsRange(from_id, end_id)
    }

    def requestLastTransHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.requestLastHistoryForIdsRange(from_id, end_id)
    }

    def getLastTransHistoryForIdsRange(long from_id, long end_id) {
        historyTrait.getLastHistoryForIdsRange(from_id, end_id)
    }
}