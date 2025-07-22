package com.sec.bestreviewer.util;

import java.util.List;

public class OptionParser {
    private boolean isPrintOn = false;

    public OptionParser(List<String> options) {
        if (!options.isEmpty()) {
            isPrintOn = "-p".equals(options.get(0));
        }
    }

    public boolean isPrintOn() {
        return isPrintOn;
    }
}
