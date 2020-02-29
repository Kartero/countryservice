package tero.countryservice.countries;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tero.countryservice.country.Country;

@RestController
public class CountriesController {

    @GetMapping("/countries")
    public Flux<Country> country() {
        Countries countries = new Countries();
        return countries.getCountries();
    }
}
