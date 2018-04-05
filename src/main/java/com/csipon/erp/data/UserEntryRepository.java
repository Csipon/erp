package com.csipon.erp.data;

import com.csipon.erp.models.UserEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserEntryRepository extends MongoRepository<UserEntry, String> {
    List<UserEntry> findByUserLastName(String lastName);

    UserEntry findByUserLoginAndFromAndTo(String login, LocalDate from, LocalDate to);

}
