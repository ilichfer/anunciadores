/*    */ package com.anunciadores.controller;
/*    */ 
/*    */ import com.anunciadores.model.TimeSlot;
/*    */ import com.anunciadores.repository.ITimeSlotRepo;
/*    */ import com.anunciadores.service.interfaces.IReserveHourService;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.JsonMappingException;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.CrossOrigin;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
/*    */ @Controller
/*    */ @RequestMapping
/*    */ public class ReserveHourController
/*    */ {
/*    */   @Autowired
/*    */   private ITimeSlotRepo timeSlotRepository;
/*    */   @Autowired
/*    */   private IReserveHourService reserveHourService;
/*    */   
/*    */   @GetMapping({"/reserveslot"})
/*    */   public String reserveslot(Model model) throws JsonMappingException, JsonProcessingException {
/* 51 */     List<TimeSlot> slots = this.reserveHourService.reserveSlot();
/* 52 */     model.addAttribute("slots", slots);
/* 53 */     return "reserve";
/*    */   }
/*    */ }


/* Location:              C:\Users\Asus VivoBook\.m2\repository\com\anunciadores\anunciadores\0.0.1-SNAPSHOT\ROOT.war!\WEB-INF\classes\com\anunciadores\controller\ReserveHourController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */