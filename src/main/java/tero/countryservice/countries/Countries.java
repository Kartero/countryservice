package tero.countryservice.countries;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tero.countryservice.country.Country;

import java.util.List;

public class Countries {

    private static final String endpoint = "https://restcountries.eu/rest/v2/";

    public Flux<Country> getCountries() {
        String[] fields = {"name", "alpha2Code"};
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        CountriesProvider countriesProvider = new CountriesProvider();

        return client.get()
                .uri(getFieldParams(fields))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class)
                .log();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getFieldParams(String[] fields) {
        return "all?fields=" + String.join(";", fields);
    }

    public String getNameParam(String name) {
        return "name/" + name;
    }

    public CountriesProvider getCountriesProvider() {
        String[] fields = {"name", "alpha2Code"};
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        CountriesProvider countriesProvider = new CountriesProvider();
        Disposable subscribe = client.get()
                .uri(getFieldParams(fields))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class)
                .log()
                .subscribeOn(Schedulers.parallel())
                .subscribe(countriesProvider::addCountry);

        int count = 0;
        while (!subscribe.isDisposed() && count < 50 ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }

            count++;
        }

        return countriesProvider;
    }

    public Mono<Country[]> getCountry(String name) {
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri(getNameParam(name))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Country[].class)
                .log();
    }
}
