package com.csipon.erp.service;

import com.csipon.erp.data.UserEntryRepository;
import com.csipon.erp.exception.UserEntryAlreadyExist;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.models.dto.UserEntryCreateDto;
import com.csipon.erp.models.mappers.UserEntryMapper;
import com.csipon.erp.service.matcher.api.UserEntryMatcher;
import com.csipon.erp.service.api.UserEntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEntryServiceImpl implements UserEntryService {
    private final UserEntryRepository userEntryRepository;
    private final UserEntryMapper userEntryMapper;
    private final UserEntryMatcher userEntryMatcher;


    @Override
    @Transactional
    public UserEntry create(UserEntryCreateDto dto) throws UserEntryAlreadyExist {
        UserEntry userEntry = userEntryMapper.mapTo(dto);
        userEntry = userEntryMatcher.matchDateWithDaysOfWeek(userEntry);
        userEntryMatcher.checkUserEntryNotExist(userEntry);
        userEntry.setLastDateModified(LocalDateTime.now());
        return userEntryRepository.save(userEntry);
    }

    @Override
    @Transactional
    public UserEntry update(UserEntry userEntry) {
        userEntry.setLastDateModified(LocalDateTime.now());
        return userEntryRepository.save(userEntry);
    }

    @Override
    public List<UserEntry> getByUserLastName(String lastName) {
        return userEntryRepository.findByUserLastName(lastName);
    }
}
