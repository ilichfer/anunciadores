package com.anunciadores.util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
