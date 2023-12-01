package io.confluent.klsql.function.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNumberFromHex {
	private NumberFromHex instance = new NumberFromHex();

	@Test
	public void testNumberFromHex() {
		Long expected = 7650410619L;
		assertEquals(expected, instance.bigIntFromHex("1C800007B"));
	}
}
