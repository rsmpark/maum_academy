package ai.mindslab.maumacademy.auth.repository;

import ai.mindslab.maumacademy.auth.domain.User;
import ai.mindslab.maumacademy.auth.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query( "select u from User u where u.email=?1" )
    UserVo getUserByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User(email, name, phone) VALUES (:#{#paramToken.email},:#{#paramToken.name},:#{#paramToken.phone})", nativeQuery = true)
    UserVo insertUser(@Param("paramToken")UserVo user);
}
