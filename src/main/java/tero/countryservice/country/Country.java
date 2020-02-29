package tero.countryservice.country;

public class Country {

    private String name;

    private String countryCode;

    public Country() {
    }

    public Country(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.setCountryCode(alpha2Code);
    }
}
