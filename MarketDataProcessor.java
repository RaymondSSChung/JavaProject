package com.mycompany.receivesenddata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author raymondchung
 */

public class MarketDataProcessor {

    // 1b Ensure each symbol has only 1 update per second
    // Assume that the data is constantly updated without any problem
    // Receive incoming market data
    public void onMessage(MarketData data) throws FileNotFoundException, IOException {
        boolean fileExist;
        String fileLoc = "C:\\MarketData.xls";
        Path filePath = Paths.get(fileLoc);
        
        DateTimeFormatter dtfSSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"); 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm.ss");
        LocalDateTime LocalTimeN = LocalDateTime.now();
        
        //1B. Ensure one update per second in the file
        File inputFile = new File(fileLoc);
        fileExist = inputFile.exists();
        if(fileExist) {
            BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
            if (attr.lastModifiedTime().toString().equals(dtf.format(LocalTimeN))){
                if (!attr.lastModifiedTime().toString().equals(dtfSSS.format(LocalTimeN))){
                    JOptionPane.showMessageDialog(null, "File not updated once per second","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    //implement a function that will check latest files details
    public boolean updateFile() throws FileNotFoundException,IOException{
        String strInput;
        boolean boolTimeStamp;
        boolean fileExist;

        boolTimeStamp = true;
        File inputFile = new File("C:\\MarketData.xls");

        DateTimeFormatter dtfSSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"); 
        LocalDateTime LocalTimeN = LocalDateTime.now();

        fileExist = inputFile.exists();
        if(fileExist){
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
                while ((strInput = br.readLine())!=null){
                    String[] resultStr = strInput.split(",");
                    if (!resultStr[2].equals(dtfSSS.format(LocalTimeN))) {
                        boolTimeStamp = false;
                        return boolTimeStamp;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "File not found","Error",JOptionPane.ERROR_MESSAGE); 
        }
        return boolTimeStamp;
    }
    
    // Publish aggregated and throttled market data
    public void publishAggregatedMarketData(MarketData data) {
    // Do Nothing, assume implemented. 
    }
}
