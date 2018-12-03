package com.example.sweater.repository;

import com.example.sweater.model.User;
import com.example.sweater.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT DISTINCT v FROM Vote v WHERE v.user=?1 ORDER BY v.time DESC")
    List<Vote> findAllByUser(User user);

//    @EntityGraph(Vote.GRAPH_WITH_USER_AND_RESTAURANT)
//    @Query("SELECT v FROM Vote v WHERE v.id=?1")
//    Vote getById(Integer id);
}
