/**
 * @packageName     : view
 * @filewName       : TemplateMethodTest.java
 * @author          : Jiyoon Bak
 * @date            : 2021.06.04
 * @description     : Template Method Pattern Test
 * =======================================================
 *      DATE         AUTHOR          NOTE
 * -------------------------------------------------------
 * 2021.06.04       JiYoon Bak      최초 생성
 * 2021.06.04       JiYoon Bak      Template Method Pattern Test 추가 
 */
package view;

import dto.BookDto;
import dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import view.BookManagement;
import view.MemeberManagement;
import javax.swing.table.DefaultTableModel;

public class TemplateMethodTest {
    List<UserDto> user_data;
    List<BookDto> book_data;
    DefaultTableModel model;
    
    public TemplateMethodTest() {
        user_data = new ArrayList();
        book_data = new ArrayList();
        user_data.add(new UserDto(UUID.randomUUID(), "TM_id", "TM_password", "TM_name"));
        book_data.add(new BookDto(UUID.randomUUID(), "TM_title", "TM_genre", "TM_author", "TM_status", 0, new Date()));
    }
    
    /**
     * Test of Template Method Pattern.
     */
    @Test
    public void testTemplateMethodPattern() {
        System.out.println("[ Template Method Pattern Test ]");
        MemeberManagement user = new MemeberManagement(user_data);
        user.showList();
        model = user.getModel();
        for(int i = 0 ; i < model.getRowCount() ; i++){
            assert user_data.get(i).getId() == model.getValueAt(i, 0);
            assert user_data.get(i).getName() == model.getValueAt(i, 1);
            assert user_data.get(i).getPassword() == model.getValueAt(i, 2);
        }
        
        BookManagement book = new BookManagement(book_data);
        book.showList();
        model = book.getModel();
        for(int i = 0 ; i < model.getRowCount() ; i++){
            assert book_data.get(i).getTitle() == model.getValueAt(i, 0);
            assert book_data.get(i).getGenre() == model.getValueAt(i, 1);
            assert book_data.get(i).getAuthor() == model.getValueAt(i, 2);
            assert book_data.get(i).getStatus() == model.getValueAt(i, 3);
            assert book_data.get(i).getCount() == model.getValueAt(i, 4);
        }
    }
}
