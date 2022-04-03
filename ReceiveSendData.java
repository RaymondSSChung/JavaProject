package com.mycompany.receivesenddata;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author raymondchung
 */
public class ReceiveSendData {

    static int noOfTimes = 100;
        
    public static void main(String[] args) throws IOException {
        
        //1a. repeat publishing market data task until either condition is reached
        //Looping more times until stop. No condition Since this is not included in the question, leave this blank
        while(true) {
            MarketDataProcessor newProcessor = new MarketDataProcessor();
            MarketData data = new MarketData();
        
            Timer newTimer = new Timer();
            newTimer.schedule(new TimerTask() {
            int counter = 1;
            @Override
            public void run() {
                try {
                    //2a and 2b. Ensure the files and data are updated before publish the data
                    if (newProcessor.updateFile()){
                        newProcessor.publishAggregatedMarketData(data);
                        if ((counter > noOfTimes) ) {
                            newTimer.cancel();
                        } else {
                            counter++;
                        }
                    } else {
                        //print warning message that the file may not be updated
                        JOptionPane.showMessageDialog(null,"MarketData may not be updated", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReceiveSendData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }, 0,1000);
            
            
        
            //1b call after 1 second. Check all data before calling the publishAggregatedMarketData method
            //If not update, an error message will prompt and stop all action.
            newProcessor.onMessage(data);
            
            //condition for break while loop. Since it is not given in question, leave it here
            //if (1==1) {
            //    break;
            //}
            
        }
        
    }
}
