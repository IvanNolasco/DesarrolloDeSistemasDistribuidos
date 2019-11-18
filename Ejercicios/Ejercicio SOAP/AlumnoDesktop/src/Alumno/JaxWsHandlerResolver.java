/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
 
public class JaxWsHandlerResolver implements HandlerResolver {
 
    @SuppressWarnings("rawtypes")
    @Override
    public List<Handler> getHandlerChain(PortInfo arg0) {
        List<Handler> hchain = new ArrayList<Handler>();
        hchain.add(new JaxWsLoggingHandler());
        return hchain;
    }
 
}
