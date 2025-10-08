package com.masterilidan.subscriptionservicetwitterlike.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionDto {
    private long id;
    private long publisherId;
    private long followerId;
    private Timestamp createdAt;
}
