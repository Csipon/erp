package com.csipon.erp;


import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.User;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.models.Week;
import com.csipon.erp.service.api.UserEntryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntryTest {





    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void checkUser(){
        System.out.println(userRepository.findUserByLogin("pasha"));
    }


    @Test
    public void createUserEntry(){
        Comparator<Week> weekComparator = Comparator.comparingInt(Week::getOrder);
        Map<Week, Integer> workWeek = new TreeMap<>(weekComparator);
        workWeek.put(Week.SUNDAY, 0);
        workWeek.put(Week.MONDAY, 0);
        workWeek.put(Week.TUESDAY, 1);
        workWeek.put(Week.WEDNESDAY, 1);
        workWeek.put(Week.THURSDAY, 1);
        workWeek.put(Week.FRIDAY, 8);
        workWeek.put(Week.SATURDAY, 0);
        User pasha = userRepository.findUserByLogin("pasha");
        UserEntry entry = UserEntry.builder()
                .from(LocalDate.now())
                .to(LocalDate.now().plusDays(7))
                .lastDateeModified(LocalDateTime.now())
                .user(pasha)
                .workWeek(workWeek)
                .build();

        UserEntry userEntry = userEntryService.createEntry(entry);
        System.out.println(userEntry);
    }


    @Test
    public void findUserEntryByUserLastName(){
        User pasha = userRepository.findUserByLogin("pasha");
        System.out.println(userEntryService.getByUserLastName(pasha.getLastName()));
    }


    @Test
    public void updateUserEntry(){
        User pasha = userRepository.findUserByLogin("pasha");
        List<UserEntry> userEntries = userEntryService.getByUserLastName(pasha.getLastName());
        UserEntry userEntry = userEntries.isEmpty() ? null : userEntries.get(0);
        if (userEntry != null){
            userEntry.getWorkWeek().put(Week.FRIDAY, 7);
            userEntry.getWorkWeek().put(Week.WEDNESDAY, 6);
            userEntryService.updateEntry(userEntry);
        }
    }
}
