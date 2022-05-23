package com.gschmutz.ksql.udf.array;

import com.grack.nanojson.JsonArray;
import com.gschmutz.ksql.udf.array.array.TipboardPieChart;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestTipbardPieChart {
    @Test
    public void test() {
        TipboardPieChart tpc = new TipboardPieChart();
        Map<String,Long> m = new HashMap<>();
        m.put("key1",30L);
        m.put("key2", 40L);
        String data = tpc.tipboardPieChart("title", m);
        System.out.println(data);
    }
}
