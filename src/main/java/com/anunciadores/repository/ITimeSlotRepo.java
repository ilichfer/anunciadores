package com.anunciadores.repository;

import com.anunciadores.model.Servicio;
import com.anunciadores.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITimeSlotRepo extends JpaRepository<TimeSlot, Integer>{

}
