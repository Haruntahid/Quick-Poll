package com.app.quick_poll.controller;


import com.app.quick_poll.domain.Vote;
import com.app.quick_poll.dto.OptionCount;
import com.app.quick_poll.dto.VoteResult;
import com.app.quick_poll.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {


    private VoteRepository voteRepository;

    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")

    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        System.out.println("Received pollId: " + pollId);
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.
                findByPoll(pollId);
        // Algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
            // Get the OptionCount corresponding to this Option
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<VoteResult>(voteResult,
                HttpStatus.OK);
    }

}
