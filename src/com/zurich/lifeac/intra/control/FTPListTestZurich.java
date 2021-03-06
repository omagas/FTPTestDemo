package com.zurich.lifeac.intra.control;


import com.zuich.life.utility.PropertiesTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;


import java.util.Properties;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author louie.zheng
 */
public class FTPListTestZurich {
protected static FTPListTestZurich instance = null;
private static Logger logger=Logger.getLogger(FTPListTestZurich.class);
    
    private  FTPListTestZurich()  {
        System.out.println(this.getClass().getName());
    }

    public static synchronized FTPListTestZurich getInstance() {
        if (instance == null) {
            instance = new FTPListTestZurich();
        }
        return instance;
    }    

    public static  void main(String[] args) throws FileNotFoundException, IOException, InterruptedException{
        FTPListTestZurich.getInstance();
                
        Properties pt=new PropertiesTool().getProperties("conf.properties");                                         
        String server = pt.getProperty("ftp.zurich.server").toString();
        int port = Integer.parseInt(pt.getProperty("ftp.zurich.port"));
        String user = pt.getProperty("ftp.zurich.user").toString();
        String pass = pt.getProperty("ftp.zurich.pass").toString();

        System.out.println(server+":"+port+":"+user+":"+pass);

        //FTP CONNECT PART 
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(server, port);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);  
        System.out.println("Connected");
        
        
        ArrayList<String> IAdir=new ArrayList();
        IAdir.add("AS4");
        IAdir.add("DIR");
        IAdir.add("SDS");
        int count=0;   
                
        //DOWNLOAD PART ---start---
        DownloadZurichFTP DZftp=new DownloadZurichFTP();
                
        for(String dir:IAdir){
            System.out.println("...........IN..."+dir);  

//                for(int i=0;i<IAdir.size();i++){
//                    System.out.println("....."+IAdir.get(i));
//                }               
                FTPFile[] subFiles = ftpClient.listFiles("/"+dir);

                if (subFiles != null && subFiles.length > 0) {
                    for (FTPFile aFile : subFiles) { //TRACE 底下資料夾和檔案  
                        if(aFile.isFile()){
                            System.out.println("/"+dir+"/"+aFile.getName());
                            DZftp.Controller(ftpClient,dir);
                        }else {
                            System.out.println("Not .txt file: "
                                    + "/"+dir+"/"+aFile.getName());
                        }
                    }
                }else{
                    count++;
                    System.out.println(count+" Try:"+" No file");
                }                
        }               
        //DOWNLOAD PART ---end---
        
        ftpClient.logout();
        ftpClient.disconnect();
        System.out.println("Disconnected");
        System.out.println("Master, I'v finished the job");
    }    
}
