
import com.zuich.life.utility.PropertiesTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
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
                
		FTPClient ftpClient = new FTPClient();
        
                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);   
                
                int count=0;
   
                        FTPFile[] subFiles = ftpClient.listFiles("/");
                        
                        if (subFiles != null && subFiles.length > 0) {
                            for (FTPFile aFile : subFiles) {      

                                    System.out.println("/"+aFile.getName());
                                    
                            }
                        }else{
                            count++;
                            System.out.println(count+" Try:"+" No file");
                        }
                


             System.out.println("Master, I'v finished the job");
    }    
}
