package tero.countryservice.countries;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.scheduler.Schedulers;
import tero.countryservice.country.Country;

import java.util.Map;

public class Countries {

    private static final String endpoint = "https://restcountries.eu/rest/v2/";

    public String getEndpoint() {
        return endpoint;
    }

    public String getFieldParams(String[] fields) {
        return "all?fields=" + String.join(";", fields);
    }

    public String getNameParam(String name) {
        return "name/" + name;
    }

    public CountryList getCountriesProvider() {
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
                .subscribeOn(Schedulers.parallel())
                .subscribe(countriesProvider::addCountry);

         waitSubscription(subscribe);

        return new CountryList(countriesProvider.getDataModelList());
    }

    public Map<String, Object> getCountry(String name) {
        WebClient client = WebClient.builder()
                .baseUrl(getEndpoint())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        CountriesProvider countriesProvider = new CountriesProvider();

        Disposable subscribe = client.get()
                .uri(getNameParam(name))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Country.class)
                .subscribeOn(Schedulers.parallel())
                .subscribe(countriesProvider::addCountry);

        waitSubscription(subscribe);

        return countriesProvider.getDataModelEntity();
    }

    private boolean waitSubscription(Disposable subscribe) {
        int count = 0;
        boolean success = true;
        while (!subscribe.isDisposed() && count < 50 ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                success = false;
                break;
            }

            count++;
        }

        if (count >= 50) {
            success = false;
        }

        return success;
    }
}
