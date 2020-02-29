package tero.countryservice.countries;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tero.countryservice.country.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesProvider {

    private List<Country> countries;

    public CountriesProvider() {
        this.countries = new ArrayList<>();
    }

    public CountriesProvider(Flux<Country> countries) {
        this.countries = new ArrayList<>();
        countries.subscribeOn(Schedulers.parallel()).subscribe(this.countries::add);
    }

    public void subscribe(Flux<Country> countries) {
        countries.subscribeOn(Schedulers.parallel()).subscribe(this.countries::add);
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }
}
