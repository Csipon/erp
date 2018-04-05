package com.csipon.erp;


import com.csipon.erp.data.UserRepository;
import com.csipon.erp.exception.UserEntryAlreadyExist;
import com.csipon.erp.models.User;
import com.csipon.erp.models.UserEntry;
import com.csipon.erp.models.Week;
import com.csipon.erp.models.dto.UserEntryCreateDto;
import com.csipon.erp.service.api.UserEntryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserEntryTest {


    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void checkUser() {
        System.out.println(userRepository.findUserByLogin("pasha"));
    }


    @Test
//    @Test(expected = UserEntryAlreadyExist.class) // check if entry already exist
    public void createUserEntry() throws UserEntryAlreadyExist {
        User pasha = userRepository.findUserByLogin("pasha");
        UserEntryCreateDto userEntryCreateDto = UserEntryCreateDto
                .builder()
                .userId(pasha.getId())
                .monday(2)
                .saturday(2)
                .friday(2)
                .sunday(4)
                .wednesday(4)
                .tuesday(3)
                .thursday(1)
                .build();

        UserEntry userEntry = userEntryService.create(userEntryCreateDto);
        log.debug("USER ENTRY = {}", userEntry);
    }


    @Test
    public void findUserEntryByUserLastName() {
        User pasha = userRepository.findUserByLogin("pasha");

        List<UserEntry> byUserLastName = userEntryService.getByUserLastName(pasha.getLastName());
        if (!byUserLastName.isEmpty()) {
            Optional<UserEntry> first = byUserLastName.stream().findFirst();
            if (first.isPresent()) {
                UserEntry userEntry = first.get();
                System.out.println(userEntry.getFrom());
                System.out.println(userEntry.getFrom().getDayOfWeek().getValue());
                System.out.println(userEntry.getFrom().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));
            }
        }
    }


    @Test
    public void updateUserEntry() {
        User pasha = userRepository.findUserByLogin("pasha");
        List<UserEntry> userEntries = userEntryService.getByUserLastName(pasha.getLastName());
        UserEntry userEntry = userEntries.isEmpty() ? null : userEntries.get(0);
        if (userEntry != null) {
            userEntry.getWorkWeek().put(Week.FRIDAY, 7);
            userEntry.getWorkWeek().put(Week.WEDNESDAY, 6);
            userEntryService.update(userEntry);
        }
    }
}
