/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zurich.lifeac.intra.control;

import com.zuich.life.utility.PropertiesTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author web.ap
 */
public class DownloadZurichFTP {
    private final static String LocDir="D:/Life";
    //private final static String RemoteDir="SDS";

    
    
    
    // call the utility method
    public  static void Controller(FTPClient ftpClient,String RemoteDir) throws FileNotFoundException, IOException {

//        Properties pt=new PropertiesTool().getProperties("ftpconfig.properties");
//        String server = pt.getProperty("ftp.server").toString();
//        int port = Integer.parseInt(pt.getProperty("ftp.port"));
//        String user = pt.getProperty("ftp.user").toString();
//        String pass = pt.getProperty("ftp.pass").toString();
//        String remoteDirPath = pt.getProperty("ftp.remoteDirPath").toString();


     
//        FTPClient ftpClient = new FTPClient();

        try{
//            ftpClient.connect(server, port);
//            ftpClient.login(user, pass);
//            // use local passive mode to pass firewall
//            ftpClient.enterLocalPassiveMode();
//            System.out.println("Connected");
//            // log out and disconnect from the server
            // directory on the server to be downloaded
            String remoteDirPath = "/"+RemoteDir;

    // directory where the files will be saved
            String saveDirPath = LocDir+remoteDirPath;//D:\Life\400\RESP
        FTPUtil ftpu=new FTPUtil();
        System.out.println(">>>>Prepare to downloadDirectory remoteDirPath"+remoteDirPath);
        System.out.println(">>>>Prepare to downloadDirectory remoteDirPath"+saveDirPath);
        ftpu.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath);//Main download tools.
        
        
        

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("FTP¤U¸üµo¥Í¿ù»~");
        }
    }    
}
