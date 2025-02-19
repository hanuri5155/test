package com.se_b4.catchtable.repository;

import com.se_b4.catchtable.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// User 엔티티 타입의 JPA 를 상속받은 인터페이스입니다.
// 이 인터페이스를 통해 DB 에 접근할 수 있습니다.
// 아래 findByUsername 이라는 메소드는 username 이라는 String 타입의 ID 값으로 User 엔티티를 가져옵니다.
// 이러한 메소드를 JPA 쿼리 메소드라고 합니다.
// 메소드 이름을 통해 JPA 가 자동으로 쿼리문을 작성해준다.
// 참고 : https://cornswrold.tistory.com/336
@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {
    // username으로 회원 정보 조회, select * from user_table where username=? 이과 같은 쿼리문 수행
    Optional<UserEntity> findByUsername(String username);
}
