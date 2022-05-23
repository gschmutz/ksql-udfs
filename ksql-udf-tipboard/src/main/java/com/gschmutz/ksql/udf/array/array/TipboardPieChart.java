package com.gschmutz.ksql.udf.array.array;


import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonWriter;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.Arrays;
import java.util.Map;

/**
 * Implementation of the 'lag' table function. This table function takes an array of values and
 * explodes it into zero or more rows, one for each value in the array.
 */
@UdfDescription(
        name = "TIPBOARD_PIE_CHART",
        description =
                "Formats the data of the tipboard text tile"
)
public class TipboardPieChart {
/*
    @Udf
    public String tipboardPieChart(
            @UdfParameter(description = "the title", value = "title") String title,
            @UdfParameter(description = "the pie data", value = "offset") Map<String,String> pieData) {
        if (pieData == null) {
            return null;
        }

        // data = {
        //    "title": "<optional_title>",
        //    "pie_data": [[identifier1, value1], [identifier2, value2], ...]
        // }

        JsonBuilder<JsonArray> b = JsonArray.builder();
        for (String key : pieData.keySet()) {
            b.array(Arrays.asList(key,Integer.valueOf(pieData.get(key))));
        }
        JsonArray a = b.done();
        String json = JsonWriter.string()
                .object().value("title", title).value("pie_data", a).end().done();

        return json;
    }
*/
    @Udf
    public String tipboardPieChart(
            @UdfParameter(description = "the title", value = "title") String title,
            @UdfParameter(description = "the pie data", value = "offset") Map<String,Long> pieData) {
        if (pieData == null) {
            return null;
        }

        // data = {
        //    "title": "<optional_title>",
        //    "pie_data": [[identifier1, value1], [identifier2, value2], ...]
        // }

        JsonBuilder<JsonArray> b = JsonArray.builder();
        for (String key : pieData.keySet()) {
            b.array(Arrays.asList(key,pieData.get(key)));
        }
        JsonArray a = b.done();
        String json = JsonWriter.string()
                .object().value("title", title).value("pie_data", a).end().done();

        return json;
    }

}
