package com.loan.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class Exporter {
    private Exporter() {
    }

    public final static void ExportMortgage(File fileName, MonthlyPayment[] payments) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (MonthlyPayment payment : payments) {
            writer.write(payment.toString());
            writer.newLine();
        }
        writer.close();
    }
}
