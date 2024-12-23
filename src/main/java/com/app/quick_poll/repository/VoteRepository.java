package com.app.quick_poll.repository;

import com.app.quick_poll.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {



//    @Query(value="select v.* from option_table o, Vote v where o.id = ? 1 and v.id = o.id", nativeQuery = true)
//    public Iterable<Vote> findByPoll(Long id);

    @Query(value = "SELECT v.* " +
            "FROM option_table o, vote v " +
            "WHERE o.poll_id = ?1 " +
            "AND v.option_id = o.id", nativeQuery = true)
    public Iterable<Vote> findByPoll(Long id);


//    @Query(value = "SELECT * FROM vote v where o.id = ?1", nativeQuery = true)
//    public Iterable<Vote> findByPoll(Long id);

}
