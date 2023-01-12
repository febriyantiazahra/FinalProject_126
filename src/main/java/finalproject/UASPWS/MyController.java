/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.UASPWS;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Febriyanti Azahra
 */
@RestController
@CrossOrigin

//Nama : Febriyanti Azahra ABidin
//Nim  : 20200140126
public class MyController {
    UaspwsJpaController dctrl = new UaspwsJpaController();
    
    @RequestMapping(value ="/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Uaspws data = new Uaspws(); //jika ingin banyak data pake List atau ArrayList
        data = mapper.readValue(json_receive, Uaspws.class);
        dctrl.create(data);
        message = data.getName()+" Saved";
        return message;
    }
    
    @RequestMapping(value ="/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Uaspws newdatas = new Uaspws(); //jika ingin banyak data pake List atau ArrayList
        
        newdatas = mapper.readValue(json_receive, Uaspws.class);
        try {dctrl.edit(newdatas);} catch (Exception e){}
        message = newdatas.getName()+" Saved";
        return message;
    }
    
        @RequestMapping(value ="/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        String json_receive = kiriman.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Uaspws newdatas = new Uaspws(); //jika ingin banyak data pake List atau ArrayList     
        newdatas = mapper.readValue(json_receive, Uaspws.class);
        dctrl.destroy(newdatas.getId());
        return "deleted";
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
