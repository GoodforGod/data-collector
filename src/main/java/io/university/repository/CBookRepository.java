package io.university.repository;

import io.university.model.dao.CBook;
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
