package com.gschmutz.ksql.udf.array.array;


import com.grack.nanojson.JsonWriter;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;
import io.confluent.ksql.function.udf.UdfSchemaProvider;
import io.confluent.ksql.function.udtf.UdtfDescription;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the 'lag' table function. This table function takes an array of values and
 * explodes it into zero or more rows, one for each value in the array.
 */
@UdfDescription(
        name = "TIPBOARD_TEXT",
        description =
                "Formats the data of the tipboard text tile"
)
public class TipboardText {
    @Udf
    public String tipboardText(
            @UdfParameter(description = "The content of the text tile", value = "content") String content) {
        if (content == null) {
            return null;
        }

        // data = {"text": "<text_content>"}

        String json = JsonWriter.string()
                .object().value("text",content).end().done();

        return json;
    }
}
