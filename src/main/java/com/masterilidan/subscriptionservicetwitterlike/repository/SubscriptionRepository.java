package com.masterilidan.subscriptionservicetwitterlike.repository;

import com.masterilidan.subscriptionservicetwitterlike.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByFollowerId(long followerId);
    List<Subscription> findAllByPublisherId(long followeeId);
    boolean existsByPublisherIdAndFollowerId(long publisherId, long followerId);
    void deleteByPublisherIdAndFollowerId(long publisherId, long followerId);
}
