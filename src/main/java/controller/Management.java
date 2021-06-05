/**
 * @packageName     : controller
 * @filewName       : Management.java
 * @author          : Jiyoon Bak
 * @date            : 2021.05.20
 * @description     : Controll Manager Menu
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.20       JiYoon Bak      최초 생성
 * 2021.05.21       JiYoon Bak      메뉴 선택 기능 추가
 * 2021.05.25       Jongho An       Strategy Pattern 적용 
 */
package controller;

import dto.ManagerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Management {
    private Logger logger;
    private final ManagerDto data;
    private final view.Management view;
    private ControllerInterface management;
    
    public Management() {
        SignIn signIn = SignIn.getInstance();
        data = signIn.getData();
        view = new view.Management(this);
        logger = LoggerFactory.getLogger(Management.class.getName());
    }
    
    public void setManagement(ControllerInterface management) {
        this.management = management;
    }
    
    public void start() {
        if(this.management == null) {
            logger.info(data.getName() + " : "+
                    "Access the Admin menu.");
            view.setVisible(true);
        } else {
            view.setVisible(false);
            management.start();
        }
    };
    
}
