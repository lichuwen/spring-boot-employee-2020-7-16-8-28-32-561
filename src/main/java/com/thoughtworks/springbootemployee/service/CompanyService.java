package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.Enum.ResultEnum;
import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::toCompanyDto).collect(Collectors.toList());
    }

    public CompanyResponse getCertainCompany(Integer companyId) throws GlobalException {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            return companyMapper.toCompanyDto(company.get());
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    public List<EmployeeResponse> getEmployeesInCompany(Integer companyId) throws GlobalException {
        Optional<Company> byId = companyRepository.findById(companyId);
        if (byId.isPresent()) {
            return byId.get().getEmployees().stream().map(employeeMapper::toEmployeeDto).collect(Collectors.toList());
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    //todo
    public Page<CompanyResponse> getCompaniesByPage(Integer page, Integer pageSize) {
        Page<Company> all = companyRepository.findAll(PageRequest.of(page - 1, pageSize));
        return new PageImpl<>(all.getContent().stream().map(companyMapper::toCompanyDto).collect(Collectors.toList()));
    }


    public CompanyResponse addNewCompany(CompanyRequest company) {
        return companyMapper.toCompanyDto(companyRepository.save(companyMapper.toCompany(company)));
    }

    public CompanyResponse updateCompany(Integer companyId, CompanyRequest company) throws GlobalException {
        Optional<Company> byId = companyRepository.findById(companyId);
        if (byId.isPresent()) {
            Company oldCompany = byId.get();
            BeanUtils.copyProperties(company, oldCompany);
            return companyMapper.toCompanyDto(companyRepository.save(oldCompany));
        } else {
            throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
        }
    }

    public Boolean deleteCompany(Integer companyId) throws GlobalException {
        Optional<Company> optional = companyRepository.findById(companyId);
        if (optional.isPresent()) {
            companyRepository.deleteById(companyId);
            return true;
        }
        throw new GlobalException(ResultEnum.DATA_NOT_FOUND.getMsg());
    }
}
