package com.csipon.erp.data;

import com.csipon.erp.models.RoleDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleDetailsRepository extends MongoRepository<RoleDetails, String> {

    List<RoleDetails> findBySalaryBefore(Double salary);

    List<RoleDetails> findBySalaryAfter(Double salary);

    List<RoleDetails> findBySalaryPerHourBefore(Double salaryPerHour);

    List<RoleDetails> findBySalaryPerHourAfter(Double salaryPerHour);

    RoleDetails findByRoleId(String roleId);

    RoleDetails findByRoleRole(String role);
}
