package io.clue2solve.com.RestClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Optional;

@SpringBootApplication
@RestController
public class RestClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}
	@RequestMapping( value="/targetUri/{target}" , method = RequestMethod.GET)
	public String hello(HttpServletRequest request , @PathVariable Optional<String> target) {

		final String externalUri = "http://dispheaders.cfapps.io/"; //Change this to the URL for the App Running external to the VNet
		final String internalDifferentVNetUri = "http://10.0.0.5:8080"; //Change this to the URL for the App Running external to the VNet
//		final String internalDifferentVNetUri = "http://c2sjump.westus.cloudapp.azure.com:8080"; //Change this to the URL for the App Running external to the VNet
		final String internalSameVNetUri = "http://samevnet.cfapps.pas.cf.az.clue2solve.com/"; //Change this to the URL for the App Running external to the VNet

		String targetServerUri = externalUri;
		if(target.isPresent()) {

			if (target.get().equalsIgnoreCase("external")) targetServerUri = externalUri;
			else if (target.get().equalsIgnoreCase("diff-vnet")) targetServerUri = internalDifferentVNetUri;
			else if (target.get().equalsIgnoreCase("internal")) targetServerUri = internalSameVNetUri;
		}
		System.out.println("Selected Target is : " + target.toString());
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(targetServerUri, String.class);

		System.out.println(result);
		return "<html><h2>" +
				"Calling the remote Server  : "  +  targetServerUri.toString() +
				"	....  " +
				"<br>Server Response : " + result +
				"</h2></html>";
	}

}

