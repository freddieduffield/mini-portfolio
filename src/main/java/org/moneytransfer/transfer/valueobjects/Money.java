package org.moneytransfer.transfer.valueobjects;

import java.math.BigInteger;
import java.util.Currency;

public record Money(BigInteger amount, Currency currency) {
}
