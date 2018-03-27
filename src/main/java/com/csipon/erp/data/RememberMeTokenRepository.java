package com.csipon.erp.data;

import com.csipon.erp.models.RememberMeToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public interface RememberMeTokenRepository extends PersistentTokenRepository, MongoRepository<RememberMeToken, String> {

    RememberMeToken findBySeries(String seriesId);

    default void createNewToken(PersistentRememberMeToken token) {
        save(convertToRememberMeToken(token));
    }

    default void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken token = findBySeries(series);
        token.setTokenValue(tokenValue);
        token.setDate(lastUsed);
        save(token);
    }

    default PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return convertToPersistentRememberMeToken(findBySeries(seriesId));
    }

    default void removeUserTokens(String username) {
        deleteById(username);
    }

    default PersistentRememberMeToken convertToPersistentRememberMeToken(RememberMeToken token) {
        return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
    }

    default RememberMeToken convertToRememberMeToken(PersistentRememberMeToken token) {
        return new RememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
    }

}
