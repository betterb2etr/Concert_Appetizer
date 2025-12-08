package com.groove.concert_appetizer.domain.playlist.service;

import java.util.ArrayList;
import java.util.List;

public class HitRateCalculator {

    public static class Result {
        private final List<Integer> matchedPositions; // 1-based
        private final int matchedCount;
        private final int totalSongs;
        private final int hitRate;  // 0~100

        public Result(List<Integer> matchedPositions, int matchedCount, int totalSongs, int hitRate) {
            this.matchedPositions = matchedPositions;
            this.matchedCount = matchedCount;
            this.totalSongs = totalSongs;
            this.hitRate = hitRate;
        }

        public List<Integer> getMatchedPositions() {
            return matchedPositions;
        }

        public int getMatchedCount() {
            return matchedCount;
        }

        public int getTotalSongs() {
            return totalSongs;
        }

        public int getHitRate() {
            return hitRate;
        }
    }

    public Result calculate(List<String> actual, List<String> predicted) {
        int total = actual.size();
        List<Integer> matchedPositions = new ArrayList<>();

        int limit = Math.min(actual.size(), predicted.size());
        for (int i = 0; i < limit; i++) {
            String a = normalize(actual.get(i));
            String p = normalize(predicted.get(i));
            if (a.equals(p)) {
                matchedPositions.add(i + 1); // 1-based index
            }
        }

        int matchedCount = matchedPositions.size();
        int hitRate = (int) Math.round((matchedCount * 100.0) / total);

        return new Result(matchedPositions, matchedCount, total, hitRate);
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}
