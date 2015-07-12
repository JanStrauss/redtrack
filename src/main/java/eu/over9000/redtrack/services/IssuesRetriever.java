package eu.over9000.redtrack.services;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import eu.over9000.redtrack.persistence.Configuration;

/**
 * Created by Jan on 12.07.2015.
 */
public class IssuesRetriever extends Service<List<String>> {

	@Override
	protected Task<List<String>> createTask() {
		return new Task<List<String>>() {
			@Override
			protected List<String> call() throws Exception {
				return performServiceTask();
			}
		};
	}

	private static List<String> performServiceTask() throws Exception {

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials(Configuration.getValue("username"), Configuration.getValue("password")).build();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(feature);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webResource = client.target("https://ipvs.informatik.uni-stuttgart.de/redmine/issues.json?assigned_to_id=me");

		Response response = webResource.request(MediaType.APPLICATION_JSON_TYPE).get();

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		String output = response.readEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);

		return null;
	}

	public static void main(String[] args) throws Exception {
		Configuration.load();

		performServiceTask();
	}
}
