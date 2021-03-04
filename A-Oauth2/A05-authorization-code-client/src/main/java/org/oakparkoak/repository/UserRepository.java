package org.oakparkoak.repository;

import java.util.Optional;

import org.oakparkoak.model.ClientUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @package: org.oakparkoak.repository
 * @author: Captain
 * @time: 2/9/2021 4:56 PM
 */
@Repository
public interface UserRepository extends CrudRepository<ClientUser, Integer> {
    /**
     * Find user by username
     *
     * @param name username
     * @return user
     */
    Optional<ClientUser> findByUsername(String name);
}
