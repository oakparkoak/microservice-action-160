package org.oakparkoak.repository;

import java.util.Optional;

import org.oakparkoak.model.ClientUserE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @package: org.oakparkoak.repository
 * @author: Captain
 * @time: 2/9/2021 4:56 PM
 */
@Repository
public interface UserERepository extends CrudRepository<ClientUserE, Integer> {

    Optional<ClientUserE> findByUsername(String name);
}
