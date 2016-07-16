package movealarm.kmitl.net.user.repository;

import movealarm.kmitl.net.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Created by oat on 7/16/16.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select * from User where :colName :operator :value")
    User[] where(@Param("colName")String colName, @Param("operator")String operator, @Param("value")String value);

    @Query("select * from User where :colName :operator :value :extraCondition")
    User[] where(@Param("colName")String colName, @Param("operator")String operator, @Param("value")String value, @Param("extraCondition")String extraCondition);


    HashMap<String, Object> insert(String tableName, String columnNamesSet, String values);

    public void insertMultiple(String tableName, String columnNamesSet, String[] valuesSet);
}
