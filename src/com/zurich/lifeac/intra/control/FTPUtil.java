/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zurich.lifeac.intra.control;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Louie
 */
class FTPUtil {


    public static void downloadDirectory(FTPClient ftpClient, String parentDir,
            String currentDir, String saveDir) throws IOException {//parentDir=remote_edir ,saveDir=loc_dir
        String dirToList = parentDir;
        
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }
        System.out.println("downloadDirectory >dirToList:"+dirToList);
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = parentDir + "/" + currentDir + "/"
                        + currentFileName;
                
                if (currentDir.equals("")) {
                    filePath = parentDir + "/" + currentFileName;
                }
                //System.out.println(filePath);
                String newDirPath = saveDir  + File.separator 
                        + currentDir + File.separator + currentFileName;
                if (currentDir.equals("")) {
                    newDirPath = saveDir  + File.separator
                              + currentFileName;
                }

                if (aFile.isDirectory()) {
                    // create the directory in saveDir
//                    File newDir = new File(newDirPath);
//                    boolean created = newDir.mkdirs();
//                    if (created) {
//                        System.out.println("CREATED the directory: " + newDirPath);
//                    } else {
//                        System.out.println("COULD NOT create the directory: " + newDirPath);
//                    }
//
//                    // download the sub directory
//                    downloadDirectory(ftpClient, dirToList, currentFileName,
//                            saveDir);
                } else {
                    // download the file
                    System.out.println("DOWNLOADED the file: " + filePath);
                    boolean success = downloadSingleFile(ftpClient, filePath,
                            newDirPath);
                    if (success) {
                        
                        
                        //if download success then delete the file.
                        boolean deleted=ftpClient.deleteFile(filePath);
                        if(deleted){
                           System.out.println("delete the file: " + filePath); 
                        }else{
                           System.out.println("COULD NOT delete the file: "
                                + filePath);
                        }
                        
                    } 
                }
            }
        }else{
                        System.out.println("There are no .txt file");            
        }
    }
 
    public static boolean downloadSingleFile(FTPClient ftpClient,
            String remoteFilePath, String savePath) throws IOException {
 
        // code to download a file...
            File downloadFile = new File(savePath);

            File parentDir = downloadFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdir();
            }

            OutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(downloadFile));
            try {
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                System.out.println("downloadSingle FileremoteFilePath:"+remoteFilePath);
                return ftpClient.retrieveFile(remoteFilePath, outputStream);
            } catch (IOException ex) {
                throw ex;
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            } 
    }      
}
