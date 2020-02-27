package tero.countryservice.country;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/country")
    public Country country(@RequestParam(value = "name") String name, @RequestParam(value = "code") String countryCode) {
        return new Country(name, countryCode);
    }
}
