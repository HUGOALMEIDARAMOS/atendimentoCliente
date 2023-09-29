package com.cielo.ada.Cliente.repositories;

import com.cielo.ada.Cliente.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> , JpaSpecificationExecutor<UserModel> {

    boolean existsByCnpj(String cnpj);
    boolean existsByCpf(String cpf);
}
