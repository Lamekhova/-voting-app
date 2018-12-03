package com.example.sweater.service;

import com.example.sweater.model.Vote;
import com.example.sweater.repository.CrudVoteRepository;
import com.example.sweater.util.exception.LateToChangeVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class VoteService {

    private final CrudVoteRepository crudVoteRepository;

    @Autowired
    public VoteService(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    public Vote addNew(Vote vote) throws LateToChangeVote {
        Assert.notNull(vote, "vote must not be null");
        return crudVoteRepository.save(vote);
    }

    public Vote getById(Integer voteId) {
        return crudVoteRepository.findById(voteId).orElse(null);
    }

    public List<Vote> getAllByUserId(Integer userId) {
        return crudVoteRepository.findAllByUserId(userId);
    }
}
