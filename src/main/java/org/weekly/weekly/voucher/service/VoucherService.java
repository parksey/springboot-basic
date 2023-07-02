package org.weekly.weekly.voucher.service;

import org.springframework.stereotype.Service;
import org.weekly.weekly.util.ExceptionMsg;
import org.weekly.weekly.voucher.domain.Voucher;
import org.weekly.weekly.voucher.dto.CreateResponse;
import org.weekly.weekly.voucher.dto.ListResponse;
import org.weekly.weekly.voucher.dto.request.VoucherCreationRequest;
import org.weekly.weekly.voucher.repository.VoucherRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public CreateResponse insertVoucher(VoucherCreationRequest voucherCreationRequest) {
        Voucher voucher = voucherCreationRequest.toVoucher();
        this.voucherRepository.insert(voucher);
        return new CreateResponse(voucher);
    }

    public ListResponse getVouchers() {
        List<Voucher> vouchers = this.voucherRepository.findAll();
        return new ListResponse(vouchers);
    }


}
