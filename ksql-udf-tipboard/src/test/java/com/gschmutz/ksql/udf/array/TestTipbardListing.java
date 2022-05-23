package com.gschmutz.ksql.udf.array;

import com.gschmutz.ksql.udf.array.array.TipboardListing;
import com.gschmutz.ksql.udf.array.array.TipboardPieChart;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTipbardListing {
    @Test
    public void test() {
        TipboardListing tpl = new TipboardListing();
        List<String> l = new ArrayList<>();
        l.add("This is the first line");
        l.add("This is the second line");
        String data = tpl.tipboardListing(l);
        System.out.println(data);
    }
}
