package com.csipon.erp.service;

import com.csipon.erp.data.UserEntryRepository;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.service.api.UserEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEntryServiceImpl implements UserEntryService {
    private final UserEntryRepository userEntryRepository;


    @Override
    public UserEntry createEntry(UserEntry userEntry) {
        return userEntryRepository.save(userEntry);
    }

    @Override
    public UserEntry updateEntry(UserEntry userEntry) {
        userEntry.setLastDateeModified(LocalDateTime.now());
        return userEntryRepository.save(userEntry);
    }

    @Override
    public List<UserEntry> getByUserLastName(String lastName) {
        return userEntryRepository.findByUserLastName(lastName);
    }
}
