/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.UASPWS;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Febriyanti Azahra
 */
@RestController
@CrossOrigin
public class MyController {
    UaspwsJpaController dctrl = new UaspwsJpaController();
    
    @PostMapping("/POST")
    public String sendData(HttpEntity<String> kiriman) throws Exception {
        Uaspws datas = new Uaspws();
        String message = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        dctrl.create(datas);
        return message;
    }
    
    @PutMapping("/PUT")
    public String editData(HttpEntity<String> kiriman) throws Exception {
        Uaspws datas = new Uaspws();
        String message = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        dctrl.edit(datas);
        return message;
    }
    
    @DeleteMapping("/DELETE")
    public String deleteData(HttpEntity<String> kiriman) throws Exception {
        Uaspws datas = new Uaspws();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        dctrl.destroy(datas.getId());
        return "id:"+datas.getId()+" deleted";
    }
    
    @GetMapping("/GET")
    public List<Uaspws> getTabel(){
        List<Uaspws> list = new ArrayList<>();
        try {
            list = dctrl.findUaspwsEntities();
        }
        catch (Exception e){}
        return list;
    }
}
