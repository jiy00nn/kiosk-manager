/**
 * @packageName     : dto
 * @filewName       : UserDto.java
 * @author          : Jiyoon Bak
 * @date            : 2021.05.03
 * @description     : MVC Patterns에서 사용하는 Model
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.05.03        JiYoon Bak     최초 생성
 */
package dto;

import java.util.UUID;

/**
 * User의 정보를 담고 있는 DTO class이다.
 */
public class UserDto {
    // ERD User table의 필드를 참고하여 선언
    private UUID user_number;
    private String id;
    private String password;
    private String name;
    
    public UserDto() {}
    
    // 모든 필드의 값을 생성자의 인자로 전달 받는 생성자 generate
    public UserDto(UUID user_number, String id, String password, String name){
        super();
        this.user_number = user_number;
        this.id = id;
        this.password = password;
        this.name = name;
    }
    
    public UUID getUserNumber(){
        return user_number;
    }
    
    public void setUserNumber(UUID user_number){
        this.user_number = user_number;
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
