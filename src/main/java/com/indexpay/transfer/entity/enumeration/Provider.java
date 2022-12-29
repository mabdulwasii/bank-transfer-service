package com.indexpay.transfer.entity.enumeration;

import com.indexpay.transfer.exception.InvalidEnumException;
import org.springframework.util.StringUtils;

public enum Provider {
    PAYSTACK,
    FLUTTERWAVE;

    public static Provider ensureProviderIsValid(String provider) throws InvalidEnumException {
        if (!StringUtils.hasText(provider)) {
            return Provider.PAYSTACK;
        }
        return Provider.valueOf(provider.toUpperCase());
    }
}