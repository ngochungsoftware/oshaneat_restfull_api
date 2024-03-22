package com.cybersoft.oshaneat.entity;

import com.cybersoft.oshaneat.entity.keys.KeyOrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity(name = "order_item")

public class OrderItem {

    @EmbeddedId
    KeyOrderItem keyOrderItem;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false,updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "food_id", insertable = false,updatable = false)
    private Food food;

    @Column(name = "order_item")
    private Date orderItem;
}
