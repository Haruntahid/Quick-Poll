package com.app.quick_poll.controller;


import com.app.quick_poll.domain.Poll;
import com.app.quick_poll.exception.ResourceNotFoundException;
import com.app.quick_poll.repository.PollRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {


    private PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    //    poll verify
    protected Poll verifyPoll(Long id) throws ResourceNotFoundException {
        Optional<Poll> poll = pollRepository.findById(id);
        if (!poll.isPresent()) {
            throw new ResourceNotFoundException("Poll with id " +
                    id + " is not found");
        }
        return poll.get();
    }


    // for all polls -> get
    @GetMapping("/polls")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    // single polls -> get
    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) throws Exception {
        Optional<Poll> poll = pollRepository.findById(id);
        verifyPoll(id);
        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
    }


    // created a pools -> Post
    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.
                CREATED);
    }

    // update single entity based on id -> put
    @PutMapping("polls/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable long id, @RequestBody Poll poll) {
        verifyPoll(id);
        Poll newPoll = pollRepository.save(poll);
        return new ResponseEntity<>(newPoll, HttpStatus.OK);
    }

    // delete single entity based on id -> delete
    @DeleteMapping("/polls/{id}")
    public ResponseEntity<Poll> deletePoll(@PathVariable long id) {
        verifyPoll(id);
        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
