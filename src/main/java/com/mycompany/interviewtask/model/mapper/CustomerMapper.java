package com.mycompany.interviewtask.model.mapper;

import com.mycompany.interviewtask.model.dto.CustomerDto;
import com.mycompany.interviewtask.model.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    List<Customer> toEntityList(List<CustomerDto> customerDtos);

    List<CustomerDto> toDtoList(List<Customer> customers);
}
