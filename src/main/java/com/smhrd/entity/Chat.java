package com.smhrd.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Chat {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(insertable = false, updatable = false)
   private Long idx;
   
   @Column(length = 1000)
   private String message;
   
   @Column(columnDefinition = "datetime default now()", insertable = false, updatable = false)
   private Date indate;
   
   @ManyToOne
   private Member email;
   
   public String toString() {
      return "Chat 객체";
   }
   

}
