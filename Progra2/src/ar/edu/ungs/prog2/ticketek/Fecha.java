package ar.edu.ungs.prog2.ticketek;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fecha {
    private String fechaStr;
    private static final DateTimeFormatter FORMATO_CORTO = DateTimeFormatter.ofPattern("dd/MM/yy");

    public Fecha(String fechaStr) {
        this.fechaStr = fechaStr;
    }

    private LocalDate getFechaLocalDate() {
        try {
            return LocalDate.parse(fechaStr, FORMATO_CORTO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yy");
        }
    }

//    public boolean esAnteriorA(Fecha otra) {
//        return getFechaLocalDate().isBefore(otra.getFechaLocalDate());
//    }
//
//    public boolean esAnteriorA(String otraFechaStr) {
//        return esAnteriorA(new Fecha(otraFechaStr));
//    }
//
//    public boolean esPosteriorA(Fecha otra) {
//        return getFechaLocalDate().isAfter(otra.getFechaLocalDate());
//    }
//
//    public boolean esPosteriorA(String otraFechaStr) {
//        return esPosteriorA(new Fecha(otraFechaStr));
//    }

    public boolean esIgualA(Fecha otra) {
        return getFechaLocalDate().isEqual(otra.getFechaLocalDate());
    }

    public boolean esIgualA(String otraFechaStr) {
        return esIgualA(new Fecha(otraFechaStr));
    }
    
    public boolean yaPaso() {
        return getFechaLocalDate().isBefore(LocalDate.now());
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public String toString() {
        return fechaStr;
    }

    public static boolean esFormatoValido(String fecha) {
        try {
            LocalDate.parse(fecha, FORMATO_CORTO);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fecha fecha1 = (Fecha) o;
        return fechaStr.equals(fecha1.fechaStr);
    }

    @Override
    public int hashCode() {
        return fechaStr.hashCode();
    }
}

