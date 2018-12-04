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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.sweater.util.ValidationUtil.checkNotFoundObjectWithId;

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
        return checkNotFoundObjectWithId(crudVoteRepository.findById(voteId).orElse(null), voteId);
    }

    public List<VoteTO> getAllByUser(User user) {
        Assert.notNull(user, "user must not be null");
        return crudVoteRepository.findAllByUser(user).stream()
                .map(vote -> new VoteTO(
                        LocalDateTime.of(vote.getDate(), vote.getTime()),
                        vote.getRestaurant().getId(),
                        vote.getRestaurant().getName()))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getVoteResultByDate(LocalDate date){
        Map<String, Integer> restaurantNameToNumberOfVotesMap = new HashMap<>();
        List<Vote> votesByDate = crudVoteRepository.getAllByDate(date);
        for (Vote vote : votesByDate) {
            restaurantNameToNumberOfVotesMap.merge(vote.getRestaurant().getName(), 1, (a,b) -> a + b);
        }
        return restaurantNameToNumberOfVotesMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
