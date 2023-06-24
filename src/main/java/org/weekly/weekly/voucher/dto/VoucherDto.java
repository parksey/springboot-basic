package org.weekly.weekly.voucher.dto;

import org.weekly.weekly.voucher.exception.VoucherException;

import java.time.LocalDate;
import java.util.UUID;

public class VoucherDto {
    private final UUID voucherId;
    private final long amount;

    private final LocalDate registrationDate;
    private final LocalDate expirationDate;

    public VoucherDto(UUID voucherId, long amount, LocalDate registrationDate, LocalDate expirationDate) {
        this.voucherId = voucherId;
        this.amount = amount;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
    }

    public static VoucherDto parseDto(UUID voucherId, String amount, LocalDate registrationDate, String expiration) {
        checkException(voucherId, amount, registrationDate, expiration);
        return new VoucherDto(voucherId
                , Long.parseLong(amount)
                , registrationDate
                , registrationDate.plusMonths(Long.parseLong(expiration)));
    }

    private static void checkException(UUID voucherId, String amount, LocalDate registrationDate, String expirationMonth) {
        VoucherException.notNumberFormat(amount);
        VoucherException.notNumberFormat(expirationMonth);
        VoucherException.expirationError(registrationDate, expirationMonth);
    }
}