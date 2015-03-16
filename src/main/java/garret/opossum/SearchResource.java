package garret.opossum;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.HttpClient;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@Path("search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {
	
	@GET
	public List<OpossumStatus> getStatuses(@QueryParam("term") String term){
		
		return SearchHelper.search(term);
	   
	}
}
