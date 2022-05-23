package com.gschmutz.ksql.udf.array;

import com.grack.nanojson.JsonArray;
import org.junit.Test;

import java.util.Arrays;

public class TestTipbard {
    @Test
    public void test() {
        JsonArray a = JsonArray.builder().array(Arrays.asList("1,2,3")).array(Arrays.asList("1,2,3"))
                .done();
        System.out.println(a);
    }
}
