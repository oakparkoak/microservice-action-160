package org.oakparkoak.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 2/9/2021 4:57 PM
 */
@Entity
@Table(name = "CLIENT_USER")
@Data
public class ClientUserE {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String accessToken;

    private Date accessTokenValidity;

    private String refreshToken;

    @Transient
    private List<EntryE> entries = new ArrayList<>();
}
