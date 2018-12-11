package com.example.sweater.service;

import com.example.sweater.model.Restaurant;
import com.example.sweater.model.User;
import com.example.sweater.model.Vote;
import com.example.sweater.repository.CrudVoteRepository;
import com.example.sweater.to.VoteTO;
import com.example.sweater.util.exception.LateToVote;
import com.example.sweater.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.sweater.util.ExceptionUtil.checkLateToVote;
import static com.example.sweater.util.ExceptionUtil.checkNotFoundObjectWithId;

@Service
public class VoteService {

    private final CrudVoteRepository crudVoteRepository;

    private final RestaurantService restaurantService;

    @Autowired
    public VoteService(CrudVoteRepository crudVoteRepository, RestaurantService restaurantService) {
        this.crudVoteRepository = crudVoteRepository;
        this.restaurantService = restaurantService;
    }

    public Vote addNew(Integer restaurantId, User user) throws LateToVote {
        Assert.notNull(user, "user must not be null");
        checkLateToVote();
        Restaurant restaurant = restaurantService.getById(restaurantId);
        Vote vote = new Vote(LocalDateTime.now(), user, restaurant);
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

    public Map<String, Integer> getVoteResultByDate(LocalDate date) {
        Map<String, Integer> restaurantNameToNumberOfVotesMap = new HashMap<>();
        List<Vote> votesByDate = crudVoteRepository.getAllByDate(date);
        for (Vote vote : votesByDate) {
            restaurantNameToNumberOfVotesMap.merge(vote.getRestaurant().getName(), 1, (a, b) -> a + b);
        }
        List<Map.Entry<String, Integer>> toSort = new ArrayList<>(restaurantNameToNumberOfVotesMap.entrySet());
        toSort.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : toSort) {
            map.putIfAbsent(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        return map;
    }
}
