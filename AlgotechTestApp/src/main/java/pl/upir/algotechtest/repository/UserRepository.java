package pl.upir.algotechtest.repository;

import com.upir.algotechtest.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Upir on 2017-09-17.
 * Project: AlgotechTestApp
 * Repository to DB for business logic
 */


public interface UserRepository extends JpaRepository<User, Long> {



    @EntityGraph(value = "notificationWithDictionariesAndCustomerAndDoc", type = EntityGraphType.FETCH)
    User findById(Long id);


    @Query("SELECT n FROM User n WHERE n.pesel = :pesel")
    User findByPesel(@Param("pesel") String number);


}

