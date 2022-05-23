package com.gschmutz.ksql.udf.array;

import com.gschmutz.ksql.udf.array.array.TipboardJustValue;
import com.gschmutz.ksql.udf.array.array.TipboardListing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTipbardJustValue {
    @Test
    public void test() {
        TipboardJustValue tpjv = new TipboardJustValue();

        String data = tpjv.tipboardJustValue("title", "description", "value");
        System.out.println(data);
    }
}
