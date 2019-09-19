/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.ufjf.dcc078.Services.MailService;
import org.junit.Test;

/**
 *
 * @author Gabriel Maia
 */
public class MailTest {
    @Test
    public void SendMail(){
        MailService.getInstance().send("allanmaralr@gmail.com", "A test", "A test message!");
    }
}
