package application.rest;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("hello")
public class Hello {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws Exception {
      StringBuilder sb = new StringBuilder(); 
      sb.append("Hello, world from ").append(InetAddress.getLocalHost().getHostName()).append(" at ").append(new Date()).append("\n");
      Map<String, String> env = System.getenv(); 
      for (String key : env.keySet()) { 
        sb.append(key).append(": ").append(env.get(key)).append("\n"); 
      } 
      return sb.toString();
    }
}
