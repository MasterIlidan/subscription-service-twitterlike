package com.masterilidan.subscriptionservicetwitterlike.controller;

import com.masterilidan.subscriptionservicetwitterlike.dto.SubscriptionDto;
import com.masterilidan.subscriptionservicetwitterlike.entity.Subscription;
import com.masterilidan.subscriptionservicetwitterlike.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
@Slf4j
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping("/subscription")
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody SubscriptionDto subscriptionDto) {
        log.debug("subscribe: {}", subscriptionDto);
        //TODO: проверка существования пользователя в User Management

        if (isExistsByPublisherIdAndFollowerId(subscriptionDto)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Subscription subscription = new Subscription();
        subscription.setPublisherId(subscriptionDto.getPublisherId());
        subscription.setFollowerId(subscriptionDto.getFollowerId());
        subscription.setCreatedAt(Timestamp.from(Instant.now()));
        subscriptionRepository.save(subscription);
        log.debug("subscribe complete: {}", subscriptionDto);
        return new ResponseEntity<>(subscriptionDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/subscription")
    public HttpStatus unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
        log.debug("unsubscribe userId={}", subscriptionDto);
        if (!isExistsByPublisherIdAndFollowerId(subscriptionDto)) {
            return HttpStatus.NOT_FOUND;
        }

        subscriptionRepository.deleteByPublisherIdAndFollowerId(
                subscriptionDto.getPublisherId(), subscriptionDto.getFollowerId());

        return HttpStatus.OK;
    }

    @GetMapping("/subscription/follower{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable long userId) {
        log.debug("getUserSubscriptions userId={}", userId);
        List<Subscription> allByFollowerId = subscriptionRepository.findAllByFollowerId(userId);
        return new ResponseEntity<>(allByFollowerId, HttpStatus.OK);
    }

    @GetMapping("/subscription/publisher{userId}")
    public ResponseEntity<List<Subscription>> getUserFollowers(@PathVariable long userId) {
        List<Subscription> allByFollowerId = subscriptionRepository.findAllByPublisherId(userId);
        return new ResponseEntity<>(allByFollowerId, HttpStatus.OK);
    }


    private boolean isExistsByPublisherIdAndFollowerId(SubscriptionDto subscriptionDto) {
        return subscriptionRepository.existsByPublisherIdAndFollowerId(
                subscriptionDto.getPublisherId(), subscriptionDto.getFollowerId());
    }
}
