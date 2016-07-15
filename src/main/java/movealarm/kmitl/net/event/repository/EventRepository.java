package movealarm.kmitl.net.event.repository;

import movealarm.kmitl.net.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oat on 7/16/16.
 */
public interface EventRepository extends JpaRepository<Event, Integer> {
}
