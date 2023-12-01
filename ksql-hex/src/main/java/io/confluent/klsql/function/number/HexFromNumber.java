package io.confluent.klsql.function.number;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.math.BigInteger;

@UdfDescription(name = "hex", description = "Computes the hexadecimal value of a number.")
public class HexFromNumber {

	@Udf(description = "Converts an Integer to a Hexadecimal string value.")
	public String hexFromInteger(@UdfParameter(value="integer", description = "the Integer value to convert to Hex") Integer integer) {
		String hex = Integer.toHexString(integer);
		return hex;
	}

	@Udf(description = "Converts an BigInt to a Hexadecimal string value.")
	public String hexFromBigInt(@UdfParameter(value="bigint", description = "the BigInt (Long) value to convert to Hex") Long bigint) {
		String hex = Long.toHexString(bigint);
		return hex;
	}
}
