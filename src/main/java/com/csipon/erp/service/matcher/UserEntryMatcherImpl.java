package com.csipon.erp.service.matcher;

import com.csipon.erp.data.UserEntryRepository;
import com.csipon.erp.exception.UserEntryAlreadyExist;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.service.matcher.api.UserEntryMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserEntryMatcherImpl implements UserEntryMatcher {
    private final UserEntryRepository userEntryRepository;

    @Override
    public UserEntry matchDateWithDaysOfWeek(UserEntry userEntry) {
        LocalDate startWeekDate = findStartWeekDateByDate(LocalDate.now());
        userEntry.setFrom(startWeekDate);
        userEntry.setTo(LocalDate.from(startWeekDate).plusDays(6));
        return userEntry;
    }

    @Override
    public void checkUserEntryNotExist(UserEntry needToCheck) throws UserEntryAlreadyExist {
        UserEntry userEntry = userEntryRepository.findByUserLoginAndFromAndTo(needToCheck.getUser().getLogin(), needToCheck.getFrom(), needToCheck.getTo());
        if(userEntry != null){
            throw new UserEntryAlreadyExist("User entry for this week already exist");
        }
    }

    private LocalDate findStartWeekDateByDate(LocalDate currentDate) {
        LocalDate result = LocalDate.from(currentDate);
        while (true) {
            if (result.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US).equalsIgnoreCase(DayOfWeek.MONDAY.toString())) {
                return result;
            } else {
                result =  LocalDate.from(result.minusDays(1));
            }
        }
    }



}
