package com.app.quick_poll.repository;

import com.app.quick_poll.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {

}
