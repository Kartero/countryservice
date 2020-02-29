package tero.countryservice.countries;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tero.countryservice.country.Country;

@RestController
public class CountriesController {

    private Countries countries;

    public CountriesController() {
        this.countries = new Countries();
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CountriesProvider listAll() {
        //Countries countries = new Countries();
        //CountriesProvider countriesProvider = new CountriesProvider();
        //countriesProvider.subscribe(countries.getCountries());
        return countries.getCountriesProvider();
    }

    @RequestMapping(value = "/countries/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Mono<Country[]> view(@PathVariable("name") String name) {
        return countries.getCountry(name);
    }
}
