package io.confluent.klsql.function.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHexFromNumber {
	private HexFromNumber instance = new HexFromNumber();

	@Test 
	public void testHexFromInteger() {
		assertEquals("7b", instance.hexFromInteger(123));
	}

	@Test
	public void testHexFromBigInt() {
		assertEquals("7b", instance.hexFromBigInt(123L));
	}

}
