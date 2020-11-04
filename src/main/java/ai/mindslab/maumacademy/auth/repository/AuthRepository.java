package ai.mindslab.maumacademy.auth.repository;

import ai.mindslab.maumacademy.auth.domain.Auth;
import ai.mindslab.maumacademy.auth.vo.TokenVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    @Query("Select a from Auth a where a.email=?1")
    TokenVo getAuth(String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT into Auth(email, access_token, access_expire_time, refresh_token, refresh_expire_time) VALUES (:#{#paramToken.email}, :#{#paramToken.access_token}," +
            ":#{#paramToken.access_expire_time},:#{#paramToken.refresh_token}," +
            ":#{#paramToken.refresh_expire_time}) ", nativeQuery = true)
    int insertAuth (@Param("paramToken")TokenVo token);

    @Transactional
    @Modifying
    @Query("delete from Auth a where a.email=?1")
    int deleteAuth(String email);


}
