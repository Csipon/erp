package com.csipon.erp.models.mappers;


import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.models.Week;
import com.csipon.erp.models.dto.UserEntryCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
public class UserEntryMapper {
    private final UserRepository userRepository;

    public UserEntry mapTo(UserEntryCreateDto dto) {
        return UserEntry
                .builder()
                .user(userRepository.findById(dto.getUserId()).get())
                .workWeek(mapToWorkWeek(dto))
                .build();
    }


    private Map<Week, Integer> mapToWorkWeek(UserEntryCreateDto dto) {
        Map<Week, Integer> workWeek = new TreeMap<>(Comparator.comparingInt(Week::getOrder));
        workWeek.put(Week.MONDAY, dto.getMonday());
        workWeek.put(Week.TUESDAY, dto.getTuesday());
        workWeek.put(Week.WEDNESDAY, dto.getWednesday());
        workWeek.put(Week.THURSDAY, dto.getThursday());
        workWeek.put(Week.FRIDAY, dto.getFriday());
        workWeek.put(Week.SATURDAY, dto.getSaturday());
        workWeek.put(Week.SUNDAY, dto.getSunday());
        return workWeek;
    }
}
