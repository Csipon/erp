package com.csipon.erp.validation;

import com.csipon.erp.data.RoleRepository;
import com.csipon.erp.data.UserRepository;
import com.csipon.erp.models.User;
import com.csipon.erp.models.dto.UserCreateDto;
import com.csipon.erp.validation.api.AbstractValidator;
import com.csipon.erp.validation.field.UserDtoField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
@Slf4j
@RequiredArgsConstructor
@PropertySource(value = "classpath:message.properties")
public class UserValidator implements AbstractValidator {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${validation.required}")
    private String userValidationMassage;
    @Value("${validation.user.exist}")
    private String userAlreadyExistMassage;
    @Value("${validation.user.password.length}")
    private String incorrectPasswordLength;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class == aClass;
    }

    @Override
    public void validate(@Nullable Object o, @NonNull Errors errors) {
        UserCreateDto userDto = (UserCreateDto) o;
        if (!roleRepository.findById(userDto.getRoleId()).isPresent()) {
            errors.rejectValue(UserDtoField.ROLE.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.ROLE.getErrorName()));
        }
        checkLogin(userDto.getLogin(), errors);
        checkPassword(userDto.getPassword(), errors);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.LOGIN.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.LOGIN.getErrorName()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.PASSWORD.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.PASSWORD.getErrorName()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.FIRST_NAME.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.FIRST_NAME.getErrorName()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.MIDDLE_NAME.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.MIDDLE_NAME.getErrorName()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.LAST_NAME.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.LAST_NAME.getErrorName()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserDtoField.ROLE.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.ROLE.getErrorName()));
    }

    private void checkPassword(String password, Errors errors) {
        if (validString(password)) {
            if (password.length() < 8) {
                errors.rejectValue(UserDtoField.PASSWORD.getName(), HttpStatus.BAD_REQUEST.toString(), incorrectPasswordLength);
            }
        } else {
            errors.rejectValue(UserDtoField.PASSWORD.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.PASSWORD.getErrorName()));
        }
    }


    public void checkLogin(String login, Errors errors) {
        if (validString(login)) {
            if (userRepository.findUserByLogin(login) != null) {
                errors.rejectValue(UserDtoField.LOGIN.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userAlreadyExistMassage, UserDtoField.LOGIN.getErrorName()));
            }
        } else {
            errors.rejectValue(UserDtoField.LOGIN.getName(), HttpStatus.BAD_REQUEST.toString(), replaceTemplate(userValidationMassage, UserDtoField.LOGIN.getErrorName()));
        }
    }

}
