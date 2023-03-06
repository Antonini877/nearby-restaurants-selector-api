package com.api.app.view;

public class RunningAppStatus {

    
    private String environment;
    private String status;

    public RunningAppStatus(String environment, String status){
        
        this.status = status;
        this.environment = environment;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getStatus() {
        return this.status;
    }


}
