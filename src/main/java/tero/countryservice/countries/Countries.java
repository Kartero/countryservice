package tero.countryservice.countries;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tero.countryservice.country.Country;

public class Countries {

    private static final String endpoint = "https://restcountries.eu/rest/v2/all";

    public Flux<Country> getCountries() {
        String[] fields = {"name", "alpha2Code"};
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Flux<Country> countries = client.get()
                .uri(getFieldParams(fields))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class)
                .log();

        return countries;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getFieldParams(String[] fields) {
        return "?fields=" + String.join(";", fields);
    }
}
