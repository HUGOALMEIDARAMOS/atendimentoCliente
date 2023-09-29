package com.cielo.ada.Cliente.models;

import com.cielo.ada.Cliente.enums.ClienteType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="tb_users")
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClienteType clienteType;
    @Column(length = 14)
    private String cnpj;
    @Column(length = 50)
    private String razao_social;
    @Column(nullable = false, length = 4)
    private String mcc;
    @Column(length = 11)
    private String cpf_contato;
    @Column(length = 50)
    private String nome_contato;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 11)
    private String cpf;
    @Column(length = 50, nullable = true)
    private String nome_pessoa_fisica;
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;


}
