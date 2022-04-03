/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.receivesenddata;

import java.time.LocalDateTime;

/**
 *
 * @author raymondchung
 */
public class MarketData {
    String symbol;
    Double price;
    LocalDateTime updateTime;
    
    public MarketData(String symbol,double price, LocalDateTime updateTime){
        this.symbol = symbol;
        this.price = price;
        this.updateTime = updateTime;
    }
    
    public MarketData(){
     
    }
    
}
