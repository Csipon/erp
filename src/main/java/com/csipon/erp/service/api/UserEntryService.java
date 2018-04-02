package com.csipon.erp.service.api;

import com.csipon.erp.models.UserEntry;

import java.util.List;

public interface UserEntryService {
    UserEntry createEntry(UserEntry userEntry);

    UserEntry updateEntry(UserEntry userEntry);

    List<UserEntry> getByUserLastName(String lastName);
}
