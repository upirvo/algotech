package pl.upir.algotechtest.repository;

import com.upir.algotechtest.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Upir/Kula on 2017-09-17.
 * Project: AlgotechTestApp
 */


public interface UserRepository extends JpaRepository<User, Long> {


/**
     * Fetch Lazy Notification by ID
     *
     * @param id
     * @return
     */


    @EntityGraph(value = "notificationWithDictionariesAndCustomerAndDoc", type = EntityGraphType.FETCH)
    User findById(Long id);


    @Query("SELECT n FROM User n WHERE n.pesel = :pesel")
    User findByPesel(@Param("pesel") String number);

    //List<User> findAll();


   /* @EntityGraph(value = "notificationWithDictionaries", type = EntityGraphType.FETCH)
    @Query("Select n from Notification n WHERE "
            + "(n.notificationNumber = :notificationNumber OR :notificationNumber = '') AND"
            + "((n.policeCaseNumber = :caseNumber OR n.prosecutorsCaseNumber1 = :caseNumber OR n.prosecutorsCaseNumber2 = :caseNumber OR n.courtCaseNumber = :caseNumber) OR :caseNumber = '') AND"
            + "(n.responsiblePerson = :responsiblePerson OR :responsiblePerson = '') AND"
            + "(n.notes LIKE %:notes% OR :notes is null) AND"
            + "(n.inspectionDate >= :controlDateFrom OR :controlDateFrom is NULL) AND"
            + "(n.inspectionDate <= :controlDateTo OR :controlDateTo is NULL) AND"
            + "(n.notificationDate >= :notiDateFrom OR :notiDateFrom is NULL) AND"
            + "(n.notificationDate <= :notiDateTo OR :notiDateTo is NULL) AND"
            + "(n.conductionStage = :conductionStage OR :conductionStage is NULL) AND"
            + "(n.notificationType = :notificationType OR :notificationType is NULL) AND"
            + "(n.isConfidential = :isConfidential OR :isConfidential is NULL) AND"
            + "(n.isClosed = :isClosed OR :isClosed is NULL)")
    Page<Notification> findAllNoti(@Param("notificationNumber") String notificationNumber, @Param("caseNumber") String caseNumber, @Param("responsiblePerson") String responsiblePerson, @Param("notes") String notes, @Param("controlDateFrom") DateTime controlDateFrom, @Param("controlDateTo") DateTime controlDateTo, @Param("notiDateFrom") DateTime notiDateFrom, @Param("notiDateTo") DateTime notiDateTo, @Param("conductionStage") ConductionStage conductionStage, @Param("notificationType") NotificationType notificationType, @Param("isConfidential") Boolean isConfidential, @Param("isClosed") Boolean isClosed, Pageable pageable);
*/

   /* @EntityGraph(value="notificationWithDictionariesAndCustomerDictionaryValues", type=EntityGraphType.FETCH)
    @Query("SELECT distinct n FROM Notification n WHERE "
            + "(n.notificationDate >= :notiDateFrom OR :notiDateFrom is NULL) AND "
            + "(n.notificationDate <= :notiDateTo OR :notiDateTo is NULL) AND "
            + "(n.isExternalReporting = :isExternalReporting) AND "
            + "(n.conductionStage IN (:conductionStage) OR n.conductionStage is NULL) AND "
            + "(n.notificationType IN (:notificationType))"
    )
    List<Notification> findNotiForExcelGenerator(@Param("notiDateFrom")DateTime dateFrom, @Param("notiDateTo")DateTime dateTo, @Param("isExternalReporting") Boolean isExternalReporting, @Param("conductionStage") List<ConductionStage> conductionStage,@Param("notificationType") List<NotificationType> notificationType);
*/

}

