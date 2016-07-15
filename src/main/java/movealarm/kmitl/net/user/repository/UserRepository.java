package movealarm.kmitl.net.user.repository;

import movealarm.kmitl.net.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oat on 7/16/16.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
