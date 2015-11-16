package org.openoj.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public class RunTimer
{
	 public static void main(String[] args) { 
	        RunTimer tTask=new RunTimer();
	        tTask.timeOut();
	    }
	    
	    public void timeOut(){
	        final Timer timer = new Timer();
	        TimerTask tt=new TimerTask() { 
	            @Override
	            public void run() {
	                System.out.println("到点啦！");
	                timer.cancel();
	            }
	        };
	        timer.schedule(tt, 3000);
	    }
}
