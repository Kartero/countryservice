package tero.countryservice.countries;

import tero.countryservice.country.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountriesProvider {

    private List<Country> countries;

    public CountriesProvider() {
        this.countries = new ArrayList<>();
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

    public List<Map<String, Object>> getDataModelList() {
        List<Map<String, Object>> countryList = new ArrayList<>();
        for (Country country: countries) {
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("name", country.getName());
            dataModel.put("country_code", country.getCountryCode());

            countryList.add(dataModel);
        }

        return countryList;
    }

    public Map<String, Object> getDataModelEntity() {
        Country country = countries.get(0);
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("name", country.getName());
        dataModel.put("country_code", country.getCountryCode());
        dataModel.put("capital", country.getCapital());
        dataModel.put("population", Integer.toString(country.getPopulation()));
        dataModel.put("flag_file_url", country.getFlagFileUrl());

        return dataModel;
    }
}
