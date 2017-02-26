package com.cgi.prototype.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author aralco
 */
@Entity
public class Account {
    @Id
    private Long id;
    private String name;
}
