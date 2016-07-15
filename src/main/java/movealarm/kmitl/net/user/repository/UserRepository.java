package movealarm.kmitl.net.user.repository;

import movealarm.kmitl.net.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by oat on 7/16/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("selec * from User where ?1 ?2 ?3")
    User[] where(String colName, String operator, String value);

    @Query("selec * from User where ?1 ?2 ?3 ?4")
    User[] where(String colName, String operator, String value, String extraCondition);
}
