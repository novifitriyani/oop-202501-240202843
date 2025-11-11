package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Receiptable, Validatable {
    private String akunBank;
    private boolean valid; // simulasi validasi
    private static final double BIAYA_TRANSFER = 3500;

    public TransferBank(String invoiceNo, double total, String akunBank, boolean valid) {
        super(invoiceNo, total);
        this.akunBank = akunBank;
        this.valid = valid;
    }

    @Override
    public double biaya() { return BIAYA_TRANSFER; }

    @Override
    public boolean validasi() { return valid; }

    @Override
    public boolean prosesPembayaran() { return validasi(); }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE %s | TOTAL+BIAYA: %.2f | TRANSFER BANK: %s | STATUS: %s",
            invoiceNo, totalBayar(), akunBank, prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
