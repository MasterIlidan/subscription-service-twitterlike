package com.masterilidan.subscriptionservicetwitterlike.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubscriptionDto {
    @ToString.Include
    private long id;
    @ToString.Include
    private long publisherId;
    @ToString.Include
    private long followerId;
    @ToString.Include
    private Timestamp createdAt;
}
