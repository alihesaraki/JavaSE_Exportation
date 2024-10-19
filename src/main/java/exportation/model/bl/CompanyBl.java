package exportation.model.bl;

import exportation.controller.exception.NoCompanyFoundException;
import lombok.Getter;
import exportation.model.da.CompanyDa;
import exportation.model.entity.Company;
import exportation.model.tools.CRUD;

import java.util.List;

public class CompanyBl implements CRUD<Company> {
    @Getter
    private static CompanyBl companyBl = new CompanyBl();

    private CompanyBl() {
    }

    //save
    @Override
    public Company save(Company company) throws Exception {
        try (CompanyDa companyDa = new CompanyDa()) {
            companyDa.save(company);
            return company;
        }
    }

    //edit
    @Override
    public Company edit(Company company) throws Exception {
        try (CompanyDa companyDa = new CompanyDa()) {
            if (companyDa.findById(company.getId()) != null) {
                companyDa.edit(company);
                return company;
            } else {
                throw new NoCompanyFoundException();
            }
        }
    }

    //remove
    @Override
    public Company remove(int id) throws Exception {
        try (CompanyDa companyDa = new CompanyDa()) {
            Company company = companyDa.findById(id);
            if (company != null) {
                companyDa.remove(id);
                return company;
            } else {
                throw new NoCompanyFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Company> findAll() throws Exception {
        try (CompanyDa companyDa = new CompanyDa()) {
            List<Company> perosnList = companyDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoCompanyFoundException();
            }
        }
    }

    //findById
    @Override
    public Company findById(int id) throws Exception {
        try (CompanyDa companyDa = new CompanyDa()) {
            Company company = companyDa.findById(id);
            if (company != null) {
                return company;
            } else {
                throw new NoCompanyFoundException();
            }
        }
    }
}
