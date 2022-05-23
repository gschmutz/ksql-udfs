package com.gschmutz.ksql.udf.array.array;


import com.grack.nanojson.JsonArray;
import com.grack.nanojson.JsonBuilder;
import com.grack.nanojson.JsonWriter;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the 'lag' table function. This table function takes an array of values and
 * explodes it into zero or more rows, one for each value in the array.
 */
@UdfDescription(
        name = "TIPBOARD_LISTING",
        description =
                "Formats the data of the tipboard listing tile"
)
public class TipboardListing {
    @Udf
    public <T extends Comparable<? super T>> String tipboardListing(
            @UdfParameter(description = "The array", value = "input") final List<T> input
            ) {
        // data = {"items": ["<entry1>", "<entry2>", ..., "<entry7>"]}

        String json = JsonWriter.string()
                .object().value("items", input).end().done();

        return json;
    }

}
