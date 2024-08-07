package com.baseapp.it_support_api.repository;

import com.baseapp.it_support_api.model.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository {
//    @Query("""
//select t from Token t inner join t.user u
//where t.user.id = :userId and t.loggedOut = false
//""")
//    List<Token> findAllTokensByUser(Long userId);
//
//    Optional<Token> findByToken(String token);
}
