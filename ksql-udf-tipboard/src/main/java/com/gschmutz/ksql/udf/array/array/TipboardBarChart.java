package com.gschmutz.ksql.udf.array.array;


import com.grack.nanojson.JsonWriter;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.List;

/**
 * Implementation of the 'lag' table function. This table function takes an array of values and
 * explodes it into zero or more rows, one for each value in the array.
 */
@UdfDescription(
        name = "TIPBOARD_BAR_CHART",
        description =
                "Formats the data of the tipboard bar_chart tile"
)
public class TipboardBarChart {
    @Udf
    public <T extends Comparable<? super T>> String tipboardBarChart(
            @UdfParameter(description = "The title", value = "title") String title,
            @UdfParameter(description = "The subtitle", value = "subtitle") String subtitle,
            @UdfParameter(description = "The ticks", value = "ticks") final List<T> ticks,
            @UdfParameter(description = "the series", value = "series") final List<Long> series) {

        // data = {
        //    "title": "<title>",
        //    "subtitle": "<subtitle>",
        //    "ticks": ["<label1>", "<label2>", "<label3>" ...],
        //    "series_list": [[<val1>, <val2>, <val3>, ...],
        //                    [<val1>, <val2>, <val3>, ...]]
        //}

        String json = JsonWriter.string()
                .object()
                    .value("title", title)
                    .value("subtitle", subtitle)
                    .value("ticks", ticks)
                .end().done();

        return json;
    }
}
