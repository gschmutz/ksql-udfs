package com.gschmutz.ksql.udf.array.array;


import com.grack.nanojson.JsonWriter;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

/**
 * Implementation of the 'lag' table function. This table function takes an array of values and
 * explodes it into zero or more rows, one for each value in the array.
 */
@UdfDescription(
        name = "TIPBOARD_JUST_VALUE",
        description =
                "Formats the data of the tipboard just_value tile"
)
public class TipboardJustValue {
    @Udf
    public String tipboardJustValue(
            @UdfParameter(description = "The title", value = "title") String title,
            @UdfParameter(description = "The description", value = "description") String description,
            @UdfParameter(description = "The value", value = "bigValue") String value) {
        // "data" = {
        //    "title": "<title>",
        //    "description": "<description>",
        //    "just-value": "<value>" }

        String json = JsonWriter.string()
                .object()
                    .value("title", title)
                    .value("description", description)
                    .value("just-value", value)
                .end().done();

        return json;
    }
}
