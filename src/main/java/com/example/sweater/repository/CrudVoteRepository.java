package com.example.sweater.repository;

import com.example.sweater.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findAllByUserId(Integer userId);

//    @EntityGraph(Vote.GRAPH_WITH_USER_AND_RESTAURANT)
//    @Query("SELECT v FROM Vote v WHERE v.id=?1")
//    Vote getById(Integer id);
}
