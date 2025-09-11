package haras.ui;

import com.opencsv.exceptions.CsvValidationException;

public class ConsoleApp {
    public static void main(String[] args) throws CsvValidationException, NumberFormatException {
        SistemaHaras sistema = new SistemaHaras();
        sistema.iniciar();
    }
}