package com.csipon.erp.service.api;

import com.csipon.erp.exception.UserEntryAlreadyExist;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.models.dto.UserEntryCreateDto;

import java.util.List;

public interface UserEntryService {

    UserEntry create(UserEntryCreateDto dto) throws UserEntryAlreadyExist;

    UserEntry update(UserEntry userEntry);

    List<UserEntry> getByUserLastName(String lastName);
}
