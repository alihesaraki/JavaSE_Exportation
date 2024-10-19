package exportation.model.bl;

import exportation.controller.exception.NoCountryFoundException;
import lombok.Getter;
import exportation.model.da.CountryDa;
import exportation.model.entity.Country;
import exportation.model.tools.CRUD;

import java.util.List;

public class CountryBl implements CRUD<Country> {
    @Getter
    private static CountryBl countryBl = new CountryBl();

    private CountryBl() {
    }

    //save
    @Override
    public Country save(Country country) throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            countryDa.save(country);
            return country;
        }
    }

    //edit
    @Override
    public Country edit(Country country) throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            if (countryDa.findById(country.getId()) != null) {
                countryDa.edit(country);
                return country;
            } else {
                throw new NoCountryFoundException();
            }
        }
    }

    //remove
    @Override
    public Country remove(int id) throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            Country country = countryDa.findById(id);
            if (country != null) {
                countryDa.remove(id);
                return country;
            } else {
                throw new NoCountryFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Country> findAll() throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            List<Country> countryList = countryDa.findAll();
            if (!countryList.isEmpty()) {
                return countryList;
            } else {
                throw new NoCountryFoundException();
            }
        }
    }

    //findById
    @Override
    public Country findById(int id) throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            Country country = countryDa.findById(id);
            if (country != null) {
                return country;
            } else {
                throw new NoCountryFoundException();
            }
        }
    }

    public List<Country> findByName(String name) throws Exception {
        try (CountryDa countryDa = new CountryDa()) {
            List<Country> countryList = countryDa.findByName(name);
            if (!countryList.isEmpty()) {
                return countryList;
            } else {
                throw new NoCountryFoundException();
            }
        }
    }
}
