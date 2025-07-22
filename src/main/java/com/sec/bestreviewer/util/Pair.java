package com.sec.bestreviewer.util;

import java.util.Objects;

public class Pair<F, S> {
    public final F first;
    public final S second;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pair)) {
            return false;
        }
        final Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(pair.first, first) && Objects.equals(pair.second, second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    @Override
    public String toString() {
        return "Pair{" + String.valueOf(first) + " " + String.valueOf(second) + "}";
    }

    public static <F, S> Pair <F, S> create(F first, S second) {
        return new Pair<>(first, second);
    }
}