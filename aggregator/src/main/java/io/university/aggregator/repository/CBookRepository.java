package io.university.aggregator.repository;

import io.university.aggregator.model.dao.mysql.CBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Repository
public interface CBookRepository extends JpaRepository<CBook, String> {

}
