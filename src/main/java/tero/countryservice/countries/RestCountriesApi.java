package tero.countryservice.countries;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tero.countryservice.country.Country;

@Component
public class RestCountriesApi {
    private static final String endpoint = "https://restcountries.eu/rest/v2/";

    public String getEndpoint() {
        return endpoint;
    }

    public Flux<Country> requestApi(CountriesProvider countriesProvider, String params) {
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri(params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class);
    }
}
