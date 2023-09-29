package com.cielo.ada.Cliente.services.implemente;

import com.cielo.ada.Cliente.models.UserModel;
import com.cielo.ada.Cliente.repositories.UserRepository;
import com.cielo.ada.Cliente.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID clienteId) {
        return userRepository.findById(clienteId);
    }

    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Override
    public boolean verificaCnpj(String cnpj) {
        return  userRepository.existsByCnpj(cnpj);
    }

    @Override
    public boolean verificaCpf(String cpfPessoaFisica) {
        return userRepository.existsByCpf(cpfPessoaFisica);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return  userRepository.findAll(spec, pageable);
    }


}
