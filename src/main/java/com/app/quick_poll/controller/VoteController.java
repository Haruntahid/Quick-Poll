package com.app.quick_poll.controller;


import com.app.quick_poll.domain.Vote;
import com.app.quick_poll.repository.VoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;

@RestController
public class VoteController {
    @Inject
    private VoteRepository voteRepository;


    @PostMapping("polls/{id}/votes")
    public ResponseEntity<?> createVote(@PathVariable long id, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);
        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders,
                HttpStatus.CREATED);
    }

    @GetMapping("/polls/{id}/votes")
    public Iterable<Vote> getAllVotes(@PathVariable Long id) {
        return voteRepository.findByPoll(id);
    }


}
