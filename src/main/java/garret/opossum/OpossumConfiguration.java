package garret.opossum;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;

public class OpossumConfiguration extends Configuration {
	  @NotEmpty
	  private String applicationName = "opossum";

	  public String getApplicationName() {
	    return applicationName;
	  }
}