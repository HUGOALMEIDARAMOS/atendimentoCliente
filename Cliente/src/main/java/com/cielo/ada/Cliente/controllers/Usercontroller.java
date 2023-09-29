package com.cielo.ada.Cliente.controllers;

import com.cielo.ada.Cliente.dtos.UserDto;

import com.cielo.ada.Cliente.models.FilaAtendimento;
import com.cielo.ada.Cliente.models.UserModel;
import com.cielo.ada.Cliente.services.UserService;
import com.cielo.ada.Cliente.specifications.SpecificationTemplate;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class Usercontroller {

    @Autowired
    UserService userService;

    @Autowired
    FilaAtendimento filaAtendimento;

    public Usercontroller() {
    }


    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllClientes(
            SpecificationTemplate.ClienteSpec spec,
            @PageableDefault(page = 0, size = 2, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<UserModel> userModelPage = userService.findAll(spec, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "clienteId") UUID clienteId){
        Optional<UserModel> userModelOptional = userService.findById(clienteId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
        }
    }

    @DeleteMapping("/{clienteId}")
    public  ResponseEntity<Object> deleteCliente(@PathVariable(value = "clienteId") UUID clienteId){
        Optional<UserModel> userModelOptional = userService.findById(clienteId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }else{
            userService.delete(userModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Cliente Deletado com sucesso");
        }
    }

    @PostMapping("/cadastroCliente")
    public ResponseEntity<Object> registerCliente( @Valid @RequestBody  UserDto userDto){

        if (userDto.getCnpj() == null || userDto.getCnpj().isEmpty() ) {
            System.out.println("CNPJ VAZIO, VALIDACAO DED CPF" + userDto.getCpf());
            if(userService.verificaCpf(userDto.getCpf())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente Já se encontra na base de dados");
            }
        }else{
            if (userService.verificaCnpj(userDto.getCnpj())) {
                System.out.println("CNPJ PREENCHIDO, VALIDACAO DE CNPJ" + userDto.getCnpj());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Empresa Já se encontra na base de dados");
            }
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setDataCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setDataAlteracao(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        filaAtendimento.adicionarCliente(userModel.getUserId(), userModel.getRazao_social(), userModel.getNome_pessoa_fisica());
        return ResponseEntity.status(HttpStatus.CREATED).body("cliente cadastrado com sucesso.");
    }

    @PutMapping("/{clienteId}")
    public  ResponseEntity<Object> updateCliente(@PathVariable(value = "clienteId") UUID clienteId, @Valid @RequestBody  UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(clienteId);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }else{
            var userModel = userModelOptional.get();
            userModel.setCnpj(userDto.getCnpj());
            userModel.setClienteType(userDto.getClienteType());
            userModel.setRazao_social(userDto.getRazao_social());
            userModel.setMcc(userDto.getMcc());
            userModel.setCpf_contato(userDto.getCpf_contato());
            userModel.setNome_contato(userDto.getNome_contato());
            userModel.setEmail(userDto.getEmail());
            userModel.setCpf(userDto.getCpf());
            userModel.setNome_pessoa_fisica(userDto.getNome_pessoa_fisica());
            userModel.setDataAlteracao(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            filaAtendimento.adicionarCliente(userModel.getUserId(), userModel.getRazao_social(), userModel.getNome_pessoa_fisica());
            return ResponseEntity.status(HttpStatus.OK).body("Cliente Alterado com sucesso");
        }
    }


}
