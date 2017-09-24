package com.ecard.android.cardemulation;

import java.util.ArrayList;

import java.util.List;

import android.app.Application;
import android.app.Activity;

public class ExitManager extends Application {

	private List<Activity> activities = new ArrayList<Activity>();  
	private static ExitManager instance;
	
	private ExitManager(){
		
	}
	public synchronized static ExitManager getInstance(){
		if(null==instance){
			instance=new ExitManager();
		}
		return instance;
	}
    public void addActivity(Activity activity) {  
        activities.add(activity);  
    }  
  
    public void exit() { 
        try { 
            for (Activity activity : activities) { 
                if (activity != null) 
                    activity.finish(); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally { 
            System.exit(0); 
        } 
    } 
    public void onLowMemory() { 
        super.onLowMemory();     
        System.gc(); 
    } 
}  
