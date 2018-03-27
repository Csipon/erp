package com.csipon.erp.data;

import com.csipon.erp.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>{
}
