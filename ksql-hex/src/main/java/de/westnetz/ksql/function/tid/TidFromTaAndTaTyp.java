package de.westnetz.ksql.function.tid;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

@UdfDescription(name = "tid", description = "Computes the TID value from a TA and a TATYP.")
public class TidFromTaAndTaTyp {

	@Udf(description = "Converts a TA and TATYP into the TID.")
	public Long tid(@UdfParameter(value="integer", description = "the TA") Integer ta,
								 @UdfParameter(value="integer", description = "the TATYP") Integer tatyp) {
		String hexTa = Integer.toHexString(ta);
		String hexTaTyp = Integer.toHexString(tatyp);

		String hexTaTypPadded = StringUtils.leftPad(hexTa, 6, "0");

		BigInteger value = new BigInteger(hexTaTyp + hexTaTypPadded,16);
		return value.longValue();
	}

}
