/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DAO.*;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jonat
 */
@WebService(serviceName = "ServicioDAO")
public class ServicioDAO {
    DAOroles daor;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "listRoles")
    public List<Roles> listRoles() {
        //TODO write your implementation code here:
        return daor.getRoles();
    }
    
    @WebMethod(operationName = "createRole")
    public int createRole(Roles r) {
        //TODO write your implementation code here:
        return daor.createRoles(r);
    }
    
    @WebMethod(operationName = "updateRole")
    public int updateRole(Roles r) {
        //TODO write your implementation code here:
        return daor.updateRoles(r);
    }
}
