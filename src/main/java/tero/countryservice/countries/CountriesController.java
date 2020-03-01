package tero.countryservice.countries;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private Countries countries;

    public CountriesController(Countries countries) {
        this.countries = countries;
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
