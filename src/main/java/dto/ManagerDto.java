/**
 * @packageName     : dto
 * @filewName       : ManagerDto.java
 * @author          : Jiyoon Bak
 * @date            : 2021.05.07
 * @description     : MVC Patterns에서 사용하는 Model
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.07        JiYoon Bak     최초 생성
 */
package dto;

import java.util.UUID;

/**
 * Manger의 정보를 담고 있는 DTO class이다.
 */
public class ManagerDto {
    // ERD User table의 필드를 참고하여 선언
    private UUID manager_number;
    private String id;
    private String password;
    private String name;
    
    public ManagerDto() {}
    
    // 모든 필드의 값을 생성자의 인자로 전달 받는 생성자 generate
    public ManagerDto(UUID manager_number, String id, String password, String name){
        super();
        this.manager_number = manager_number;
        this.id = id;
        this.password = password;
        this.name = name;
    }
    
    public UUID getManagerNumber(){
        return manager_number;
    }
    
    public void setManagerNumber(UUID manager_number){
        this.manager_number = manager_number;
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
