package de.westnetz.ksql.function.tid;

import io.confluent.klsql.function.number.HexFromNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestTidFromTaAndTaTyp {
	private TidFromTaAndTaTyp instance = new TidFromTaAndTaTyp();

	@Test 
	public void testTidFromTaAndTaTyp() {

		assertEquals("7650410619", instance.tid( 123,456).toString());
	}
}
