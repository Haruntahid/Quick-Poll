package com.app.quick_poll.repository;

import com.app.quick_poll.domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
