package tero.countryservice.countries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import tero.countryservice.country.Country;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesTest {

    @Autowired
    private Countries countries;

    @MockBean
    private RestCountriesApi restCountriesApi;

    Flux<Country> countryStream;
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
        countryStream = Flux.just(country1, country2);

        Mockito.when(restCountriesApi.requestApi(Mockito.any(CountriesProvider.class), Mockito.anyString())).thenReturn(countryStream);
    }

    @Test
    public void testGetCountry_format() {
        Map<String, Object> result = countries.getCountry("test");
        Map<String, Object> expected = new HashMap<>();
        expected.put("name", "Finland");
        expected.put("country_code", "FI");
        expected.put("capital", "Helsinki");
        expected.put("population", "5500000");
        expected.put("flag_file_url", "http://kuvat.com/fin.jpg");

        assertEquals(expected, result);
    }
}
