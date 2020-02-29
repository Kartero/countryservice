package tero.countryservice.countries;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tero.countryservice.country.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private Countries countries;

    public CountriesController() {
        this.countries = new Countries();
    }

    @GetMapping(value = "")
    public CountryList listAll() {
        return countries.getCountriesProvider();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> view(@PathVariable("name") String name) {
        return countries.getCountry(name);
    }
}
