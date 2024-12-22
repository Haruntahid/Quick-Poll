package com.app.quick_poll.controller;


import com.app.quick_poll.domain.Vote;
import com.app.quick_poll.dto.VoteResult;
import com.app.quick_poll.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;

    @GetMapping("/computeresult")
    public ResponseEntity<?> getComputerResult(@RequestParam long id) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findAll();

        return new ResponseEntity<VoteResult>(voteResult,
                HttpStatus.OK);
    }

}
