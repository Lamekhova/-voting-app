package com.example.sweater.service;

import com.example.sweater.model.User;
import com.example.sweater.model.Vote;
import com.example.sweater.repository.CrudVoteRepository;
import com.example.sweater.to.VoteTO;
import com.example.sweater.util.exception.LateToChangeVote;
import com.example.sweater.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public Vote getById(Integer voteId) throws NotFoundException {
        return crudVoteRepository.findById(voteId).orElse(null);
    }

    public List<VoteTO> getAllByUser(User user) {
        return crudVoteRepository.findAllByUser(user).stream()
                .map(vote -> new VoteTO(
                        LocalDateTime.of(vote.getDate(), vote.getTime()),
                        vote.getRestaurant().getId(),
                        vote.getRestaurant().getName()))
                .collect(Collectors.toList());
    }
}
