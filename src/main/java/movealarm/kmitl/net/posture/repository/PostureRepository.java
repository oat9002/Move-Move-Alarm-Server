package movealarm.kmitl.net.posture.repository;

import movealarm.kmitl.net.posture.entity.Posture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oat on 7/16/16.
 */
public interface PostureRepository extends JpaRepository<Posture, Integer> {
}
