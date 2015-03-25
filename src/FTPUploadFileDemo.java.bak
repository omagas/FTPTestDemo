import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.zuich.life.utility.*;
import java.util.Properties;

/**
 * A program that demonstrates how to upload files from local computer
 * to a remote FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */
public class FTPUploadFileDemo_bk {

	public static void main(String[] args) throws FileNotFoundException, IOException {
            
                Properties pt=new PropertiesTool().getProperties("conf.properties");
                String aa=pt.getProperty("ftp.server").toString();
           
                
                //System.out.println(aa);
                
		String server = pt.getProperty("ftp.server").toString();
		int port = Integer.parseInt(pt.getProperty("ftp.port"));
		String user = pt.getProperty("ftp.user").toString();
		String pass = pt.getProperty("ftp.pass").toString();

                //System.out.println(server+":"+port+":"+user+":"+pass);
                
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

//			// APPROACH #1: uploads first file using an InputStream
//			File firstLocalFile = new File("D:/Life/400/LNPA20150309_IA.txt");
//
//			String firstRemoteFile = "IA0001.zip";
//			InputStream inputStream = new FileInputStream(firstLocalFile);
//
//			System.out.println("Start uploading first file");
//			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
//			inputStream.close();
//			if (done) {
//				System.out.println("The first file is uploaded successfully.");
//			}

			// APPROACH #2: uploads second file using an OutputStream
			File secondLocalFile = new File("D:/Life/400/LNPA20150309_IA.txt");
			String secondRemoteFile = "batN06/IA0001.txt";
			InputStream inputStream = new FileInputStream(secondLocalFile);

			System.out.println("Start uploading second file");
			OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
                        
                        //ftpClient.deleteFile("MA0001.txt");
	        byte[] bytesIn = new byte[4096];
	        int read = 0;

	        while ((read = inputStream.read(bytesIn)) != -1) {
	        	outputStream.write(bytesIn, 0, read);
	        }
	        inputStream.close();
	        outputStream.close();

	        boolean completed = ftpClient.completePendingCommand();
			if (completed) {
				System.out.println("The second file is uploaded successfully.");
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}