package com.csipon.erp.service.matcher.api;

import com.csipon.erp.exception.UserEntryAlreadyExist;
import com.csipon.erp.models.UserEntry;

public interface UserEntryMatcher {

    UserEntry matchDateWithDaysOfWeek(UserEntry userEntry);

    void checkUserEntryNotExist(UserEntry userEntry) throws UserEntryAlreadyExist;
}
