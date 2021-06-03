/**
 * @packageName     : dto
 * @filewName       : CheckoutBookDto.java
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
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * CheckoutBook의 정보를 담고 있는 DTO class이다.
 */
public class CheckoutBookDto {
    private UUID user_id;
    private UUID book_id;
    private String book_title;
    private Date rental_date;
    private Date return_date;
    
    public CheckoutBookDto() {}
    
    public CheckoutBookDto(UUID user_id, UUID book_id, String book_title, Date rental_date, Date return_date) {
        super();
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_title = book_title;
        this.rental_date = rental_date;
        this.return_date = return_date;
    }
    
    public UUID getUserId(){
        return user_id;
    }
    
    public void setUserId(UUID user_id){
        this.user_id = user_id;
    }
    
    public UUID getBookId(){
        return book_id;
    }
    
    public void setBookId(UUID book_id){
        this.book_id = book_id;
    }
    
    public String getBookTitle() {
        return book_title;
    }
    
    public void setBookTitle(String book_title) {
        this.book_title = book_title;
    }
    
    public Date getRentalDate(){
        return rental_date;
    }
    
    public String getRentalStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.rental_date);
    }
    
    public void setRentalDate(Date date) {
        this.rental_date = date;
    }
    
    public Date getReturnDate() {
        return return_date;
    }
    
    public String getReturnStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.return_date);
    }
    
    public void setReturnDate(Date date) {
        this.return_date = date;
    }
}
