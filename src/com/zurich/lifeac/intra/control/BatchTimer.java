package com.zurich.lifeac.intra.control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.zuich.life.utility.*;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Louie
 */
public class BatchTimer {
    private final static String LocDir="D:/Life/400/";
    private final static String RemoteDir="AS4";

    
    public static void main(String[] args){
		Timer timer= new Timer(); //�إߤ@��Timer����
		MyTask copy= new MyTask("copy"); //�i�H�t�Ф@�����O�~��TimerTask
                
                Date dd=new Date();
                System.out.println("Start"+dd);
                
                timer.schedule(copy, 5000,3000);    //�����}�l  
                System.out.println("Copy Start After 5 sec");   
                try {
                    sleep(15000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BatchTimer.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                Date df=new Date();
                System.out.println("End"+dd);
                
                
                timer.cancel();
//		TimerTask showtime= new TimerTask(){//�]�i�H�ΰΦW���O���覡�A
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//					System.out.println(new Date()); //��X�ɶ�
//			}	
//		};
//		timer.schedule(showtime, 1000, 5000);//�@���}�l�A����C�L����A����
		//timer.schedule(xxx, Date);�ĤG�ӰѼƬO�i�H���w�ɶ��A����ɭԶ}�l����C
        
    }
    
    
    
    
    
    
}
