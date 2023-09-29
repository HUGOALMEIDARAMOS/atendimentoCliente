package com.cielo.ada.Cliente.services;

import com.cielo.ada.Cliente.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();
    Optional<UserModel> findById(UUID clienteId);


    void delete(UserModel userModel);

    void save(UserModel userModel);

    boolean verificaCnpj(String cnpj);

    boolean verificaCpf(String cpfPessoaFisica);

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
}
