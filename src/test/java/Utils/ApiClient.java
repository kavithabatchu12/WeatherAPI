package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    public static RequestSpecification getRequestSpec() {
        String baseUri = ConfigManager.getBaseUri();

        // --- DEBUG LINE: Let's print the URI to see what it is ---
        System.out.println("Using Base URI: '" + baseUri + "'");

        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addQueryParam("appid", ConfigManager.getApiKey())
                .build();
    }
}