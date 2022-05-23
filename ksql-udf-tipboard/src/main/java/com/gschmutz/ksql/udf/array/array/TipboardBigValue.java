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
        name = "TIPBOARD_BIG_VALUE",
        description =
                "Formats the data of the tipboard text tile"
)
public class TipboardBigValue {
    @Udf
    public String tipboardBigValue(
            @UdfParameter(description = "The title", value = "title") String title,
            @UdfParameter(description = "The description", value = "description") String description,
            @UdfParameter(description = "The big value", value = "bigValue") String bigValue,
            @UdfParameter(description = "The upper left label", value = "upperLeftLabel") String upperLeftLabel,
            @UdfParameter(description = "The upper left value", value = "upperLeftValue") String upperLeftValue,
            @UdfParameter(description = "The upper lower label", value = "lowerLeftLabel") String lowerLeftLabel,
            @UdfParameter(description = "The upper lower value", value = "lowerLeftValue") String lowerLeftValue,
            @UdfParameter(description = "The upper left label", value = "upperRightLabel") String upperRightLabel,
            @UdfParameter(description = "The upper left value", value = "upperRightValue") String upperRightValue,
            @UdfParameter(description = "The upper lower label", value = "lowerRightLabel") String lowerRightLabel,
            @UdfParameter(description = "The upper lower value", value = "lowerRightValue") String lowerRightValue
            ) {
        // data = {"text": "<text_content>"}

        String json = JsonWriter.string()
                .object().value("title",title)
                        .value("description", description)
                        .value("big-value", bigValue)
                        .value("upper-left-label", upperLeftLabel)
                        .value("upper-left-value", upperLeftValue)
                        .value("lower-left-label", lowerLeftLabel)
                        .value("lower-left-value", lowerLeftValue)
                        .value("upper-right-label", upperRightLabel)
                        .value("upper-right-value", upperRightValue)
                        .value("lower-right-label", lowerRightLabel)
                        .value("lower-right-value", lowerRightValue)
                .end().done();

        return json;
    }

    @Udf
    public String tipboardBigValue(
            @UdfParameter(description = "The title", value = "title") String title,
            @UdfParameter(description = "The description", value = "description") String description,
            @UdfParameter(description = "The big value", value = "bigValue") String bigValue
    ) {
        // data = {
        //    "title": "<title>",
        //    "description": "<description>",
        //    "big-value": "<value>",
        //    "upper-left-label": "<label>",
        //    "upper-left-value": "<value>",
        //    "lower-left-label": "<label>",
        //    "lower-left-value": "<value>",
        //    "upper-right-label": "<label>",
        //    "upper-right-value": "<value>",
        //    "lower-right-label": "<label>",
        //    "lower-right-value": "<value>"
        //}

        String json = JsonWriter.string()
                .object().value("title",title)
                .value("description", description)
                .value("big-value", bigValue)
                .value("upper-left-label", "")
                .value("upper-left-value", "")
                .value("lower-left-label", "")
                .value("lower-left-value", "")
                .value("upper-right-label", "")
                .value("upper-right-value", "")
                .value("lower-right-label", "")
                .value("lower-right-value", "")
                .end().done();

        return json;
    }
}
