package com.gschmutz.ksql.udf.array;


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
        name = "ARRAY_LAG",
        description =
                "Explodes an array. This function outputs one value for each element of the array."
)
public class ArrayLag {
    @Udf
    public <T extends Comparable<? super T>> T  arrayLag(
            @UdfParameter(description = "The array", value = "input") final List<T> input,
            @UdfParameter(description = "The offset", value = "offset") Integer offset) {
        if (input == null) {
            return null;
        }
        return input.get(input.size() - offset - 1);
    }
}
