package com.indexpay.transfer.entity;

import com.indexpay.transfer.entity.enumeration.Provider;
import com.indexpay.transfer.utils.annotation.ValidEnumValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "Bank code is mandatory")
    private String code;
    @Column(name = "bankName", nullable = false)
    @NotEmpty(message = "Bank name is mandatory")
    private String bankName;
    @Column(name = "longCode")
    private String longCode;
    @Column(name = "provider", nullable = false)
    @NotEmpty(message = "Provider is mandatory")
    @ValidEnumValue(enumClass = Provider.class)
    private String provider;
}
