package tero.countryservice.countries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tero.countryservice.countries.CountriesProvider;
import tero.countryservice.country.Country;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesProviderTest {

    @Autowired
    private CountriesProvider countriesProvider;

    Country country1;
    Country country2;

    @Before
    public void setup() {
        country1 =  new Country(
                "Finland",
                "FI",
                "Helsinki",
                5500000,
                "http://kuvat.com/fin.jpg"
        );
        country2 = new Country(
                "Sweden",
                "SE",
                "Stockholm",
                8000000,
                "http://kuvat.com/swe.jpg"
        );

        countriesProvider.addCountry(country1);
        countriesProvider.addCountry(country2);
    }

    @Test
    public void testGetDataModelEntity_first_matches() {
        Map<String, Object> result = countriesProvider.getDataModelEntity();
        assertNotNull(result);
        assertEquals(result.get("name"), country1.getName());
        assertEquals(result.get("country_code"), country1.getCountryCode());
        assertEquals(result.get("capital"), country1.getCapital());
        assertEquals(result.get("population"), Integer.toString(country1.getPopulation()));
        assertEquals(result.get("flag_file_url"), country1.getFlagFileUrl());
    }

    @Test
    public void testGetDataModelList_countries_exist() {
        List<Map<String, Object>> result = countriesProvider.getDataModelList();
        Map<String, Object> c1 = result.get(0);
        Map<String, Object> c2 = result.get(1);
        assertEquals(c1.get("name"), country1.getName());
        assertEquals(c2.get("name"), country2.getName());
        assertEquals(c2.get("country_code"), country2.getCountryCode());
    }
}
