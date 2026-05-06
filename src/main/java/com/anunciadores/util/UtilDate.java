package com.anunciadores.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UtilDate {

    public String cargarfechaActualBogotaString() throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        String fechaActualStr = nowInBogota.format(formatter);
        return  fechaActualStr;
    }

    public Date cargarfechaActualBogotaDate() throws ParseException {
        Date actualDate ;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        String fechaActualStr = nowInBogota.format(formatter);
        actualDate = sdf.parse(fechaActualStr);
        return  actualDate;
    }

    public String convertDateToString(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = sdf.format(fecha);
        return fechaString;

    }

    public Date convertStringToDate(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = sdf.parse(fecha);
        return fechaDate;

    }

    public String convertDateToStringWithFormat(Date fecha,String  format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String fechaString = sdf.format(fecha);
        return fechaString;

    }

    public String cargarFechaBogotaConParametro(String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        ZonedDateTime nowInBogota = ZonedDateTime.now(ZoneId.of("America/Bogota"));
        String fechaActualStr = nowInBogota.format(formatter);
        return  fechaActualStr;
    }

    public Date convertLocaldateToDate(LocalDate local){
    LocalDate localDate = local;

    // Conversión
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("LocalDate: " + localDate);
        System.out.println("Date: " + date);
        return date;
}


}
