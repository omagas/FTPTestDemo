/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zuich.life.utility;

import java.util.*;

/**
 *
 * @author web.ap
 */
public class MyTask  extends TimerTask{
	private String jobname; //可以根據傳入的字串決定做什麼工作
        int count=0;
	public MyTask(String jobname){
		this.jobname=jobname;
	}
        
	@Override
	public void run() {
            
		// TODO Auto-generated method stub
//		if(jobname.equals("xxx")){
//			dosomething();
		//		}
		
		if(jobname.equals("copy")){ 
                    System.out.println("Start to run:"+new Date());
                    
                    count++;   

                    System.out.println(count);
		}
	}
}
