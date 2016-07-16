package movealarm.kmitl.net.user.repository;

import movealarm.kmitl.net.user.entity.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oat on 7/16/16.
 */
@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Integer> {
}
