package tero.countryservice.country;

public class Country {

    private String name;

    private String countryCode;

    private String capital;

    private int population;

    private String flagFileUrl;

    public Country() {
    }

    public Country(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public Country(String name, String countryCode, String capital, int population, String flagFileUrl) {
        this.name = name;
        this.countryCode = countryCode;
        this.capital = capital;
        this.population = population;
        this.flagFileUrl = flagFileUrl;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getFlagFileUrl() {
        return flagFileUrl;
    }

    public void setFlagFileUrl(String flagFileUrl) {
        this.flagFileUrl = flagFileUrl;
    }

    public void setFlag(String flag) {
        this.setFlagFileUrl(flag);
    }
}
