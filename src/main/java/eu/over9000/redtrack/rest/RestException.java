package eu.over9000.redtrack.rest;

import javax.ws.rs.core.Response;

/**
 * Exception for REST based errors.
 */
public class RestException extends Throwable {
	public RestException(final int status, final Response.StatusType statusInfo, final String body) {
		super(status + " " + statusInfo + ": " + body);
	}
}
