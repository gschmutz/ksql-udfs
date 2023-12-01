package io.confluent.klsql.function.number;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.math.BigInteger;

@UdfDescription(name = "number", description = "Computes the number from a hex value.")
public class NumberFromHex {

	@Udf(description = "Converts a Hex value into a BigInt value.")
	public Long bigIntFromHex(@UdfParameter(value="hex", description = "the Hex value to convert to a BigInt") String hex) {
		BigInteger value = new BigInteger(hex, 16);
		return value.longValue();
	}
}
