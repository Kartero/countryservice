package tero.countryservice.countries;

import java.util.List;
import java.util.Map;

public class CountryList {
    private List<Map<String, Object>> countries;

    public CountryList(List<Map<String, Object>> countries) {
        this.countries = countries;
    }

    public List<Map<String, Object>> getCountries() {
        return countries;
    }

    public void setCountries(List<Map<String, Object>> countries) {
        this.countries = countries;
    }
}
