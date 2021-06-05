/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dto.ManagerDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mkaid
 */
public class CommonFeatures {
    private Logger logger;
    private ManagerDto manager;
    private SignIn signIn;
    public ManagerDto manager_info;
    
    public CommonFeatures() {
        logger = LoggerFactory.getLogger(CommonFeatures.class.getName());
        signIn = SignIn.getInstance();
        manager = signIn.getData();
    }
    
    public Date handleDate(String dateInString) throws ParseException {
        if(dateInString == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateInString);
    }
    
    public void setLogger(String clazz) {
        logger =  LoggerFactory.getLogger(clazz);
    }
    
    public void makeInfoLog(String msg) {
        logger.info(manager.getName() + " : "+ msg);
    }
}
