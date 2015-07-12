package eu.over9000.redtrack.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import eu.over9000.redtrack.persistence.Configuration;

/**
 * Created by Jan on 12.07.2015.
 */
public class RestRequestWrapper {
	private final Client client;

	public RestRequestWrapper() {
		this.client = initClient();
	}
	
	private Client initClient() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JSR310Module());
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		
		final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.setMapper(mapper);
		
		final ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(HttpAuthenticationFeature.basicBuilder().nonPreemptive().credentials(Configuration.getValue("username"), Configuration.getValue("password")).build());
		clientConfig.register(provider);
		
		return ClientBuilder.newClient(clientConfig);
	}
	
	public <returnType> returnType performGet(final Class<returnType> returnType, final String resource) throws RestException {
		final WebTarget webTarget = buildTarget(resource);

		final Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).get();

		if (response.getStatus() != 200) {
			throw new RestException(response.getStatus(), response.getStatusInfo(), response.readEntity(String.class));
		}

		return response.readEntity(returnType);
	}

	public <returnType> returnType performPost(final Class<returnType> returnType, final String resource, final Object toPost) throws RestException {
		final WebTarget webTarget = buildTarget(resource);

		final Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(toPost));

		if (response.getStatus() != 201) {
			throw new RestException(response.getStatus(), response.getStatusInfo(), response.readEntity(String.class));
		}

		return response.readEntity(returnType);
	}

	private WebTarget buildTarget(final String resource) {
		final String targetURL = Configuration.getValue("base_url") + resource;
		System.out.println("TARGET=" + targetURL);
		return client.target(targetURL);
	}
}
