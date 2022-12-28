package com.indexpay.transfer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "bank", indexes = {
        @Index(name = "idx_provider", columnList = "provider")
})
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "bankName", nullable = false)
    private String bankName;
    @Column(name = "longCode")
    private String longCode;
    @Column(name = "provider", nullable = false)
    private String provider;
}
