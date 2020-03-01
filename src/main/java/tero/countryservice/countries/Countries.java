package tero.countryservice.countries;

import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import tero.countryservice.country.Country;

import java.util.Map;

@Component
public class Countries {

    private RestCountriesApi restCountriesApi;

    public Countries(RestCountriesApi restCountriesApi) {
        this.restCountriesApi = restCountriesApi;
    }

    public String getFieldParams(String[] fields) {
        return "all?fields=" + String.join(";", fields);
    }

    public String getNameParam(String name) {
        return "name/" + name;
    }

    public CountryList getCountriesProvider() {
        String[] fields = {"name", "alpha2Code"};
        String params = getFieldParams(fields);
        CountriesProvider countriesProvider = new CountriesProvider();

        Flux<Country> countries = restCountriesApi.requestApi(countriesProvider, params);
        Disposable subscribe = subscribe(countries, countriesProvider);
        waitSubscription(subscribe);

        return new CountryList(countriesProvider.getDataModelList());
    }

    public Map<String, Object> getCountry(String name) {
        String params = getNameParam(name);
        CountriesProvider countriesProvider = new CountriesProvider();

        Flux<Country> countries = restCountriesApi.requestApi(countriesProvider, params);
        Disposable subscribe = subscribe(countries, countriesProvider);
        waitSubscription(subscribe);

        return countriesProvider.getDataModelEntity();
    }

    private Disposable subscribe(Flux<Country> countries, CountriesProvider countriesProvider) {
        return countries
                .subscribeOn(Schedulers.parallel())
                .subscribe(countriesProvider::addCountry);
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
