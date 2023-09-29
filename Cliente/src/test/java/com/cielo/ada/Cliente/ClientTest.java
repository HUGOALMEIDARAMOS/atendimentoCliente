package com.cielo.ada.Cliente;

import com.cielo.ada.Cliente.controllers.Usercontroller;
import com.cielo.ada.Cliente.dtos.UserDto;
import com.cielo.ada.Cliente.enums.ClienteType;
import com.cielo.ada.Cliente.models.UserModel;
import com.cielo.ada.Cliente.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientTest {

    @InjectMocks
    private Usercontroller userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void deveCriarObjetoUserController() throws  Exception{
        Usercontroller userController = new Usercontroller();
    }

    @Test
    public void deveListarClientesComPaginacao() {

        List<UserModel> userModels = new ArrayList<>();
        userModels.add(new UserModel());
        userModels.add(new UserModel());
        Page<UserModel> userModelPage = new PageImpl<>(userModels);

        when(userService.findAll(any(), any(Pageable.class))).thenReturn(userModelPage);

        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("userId")));

        ResponseEntity<Page<UserModel>> response = userController.getAllClientes(null, pageable);

        verify(userService, times(1)).findAll(any(), any(Pageable.class));

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertEquals(userModelPage, response.getBody());
    }

    @Test
    public void devecadastrarClientePessoaJuridica(){
        when(userService.verificaCnpj(anyString())).thenReturn(false);
        UserDto userDto = new UserDto();
        userDto.setCnpj("12352545859");
        userDto.setClienteType(ClienteType.valueOf("JURIDICA"));
        userDto.setRazao_social("Comercio Menezes");
        userDto.setMcc("1520");
        userDto.setCpf_contato("23985458854");
        userDto.setNome_contato("Ana Caroline");
        userDto.setEmail("hugo.undb@gmail.com");
        userDto.setCpf("");
        userDto.setNome_pessoa_fisica("");

        ResponseEntity<Object> response = userController.registerCliente(userDto);

        verify(userService, times(1)).verificaCnpj(anyString());
        verify(userService, times(1)).save(any(UserModel.class));

        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cliente Cadastrado com sucesso", response.getBody());

    }

    @Test
    public void devecadastrarClientePessoaFisica(){
       when(userService.verificaCpf(anyString())).thenReturn(false);
        UserDto userDto = new UserDto();
        userDto.setCnpj("");
        userDto.setClienteType(ClienteType.valueOf("FISICA"));
        userDto.setRazao_social("");
        userDto.setMcc("1010");
        userDto.setCpf_contato("");
        userDto.setNome_contato("");
        userDto.setEmail("hugo.almeida@gmail.com");
        userDto.setCpf("25698845658");
        userDto.setNome_pessoa_fisica("Hugo Leonardo");

        ResponseEntity<Object> response = userController.registerCliente(userDto);


        verify(userService, times(1)).verificaCpf(anyString());
        verify(userService, times(1)).save(any(UserModel.class));

        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cliente Cadastrado com sucesso", response.getBody());

    }

    @Test
    public void deveNaoSalvarClienteDuplicado(){

        when(userService.verificaCpf(anyString())).thenReturn(true);

        UserDto userDto = new UserDto();
        userDto.setCpf("12352545895");

        ResponseEntity<Object> response = userController.registerCliente(userDto);

        verify(userService, times(1)).verificaCpf(anyString());
        verify(userService, never()).save(any(UserModel.class));

        assertSame(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Cliente Já se encontra na base de dados", response.getBody());
    }

    @Test
    public void deveNaoSalvarEMpresaDuplicada(){
        when(userService.verificaCnpj(anyString())).thenReturn(true);

        UserDto userDto = new UserDto();
        userDto.setCnpj("12525458545854");

        ResponseEntity<Object> response = userController.registerCliente(userDto);

        verify(userService, times(1)).verificaCnpj(anyString());
        verify(userService, never()).save(any(UserModel.class));

        assertSame(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Empresa Já se encontra na base de dados", response.getBody());
    }

    @Test
    public void  deveAlterarCliente() {
        UUID clienteId = UUID.randomUUID();
        UserDto userDto = new UserDto();

        UserModel existingUserModel = new UserModel();
        when(userService.findById(clienteId)).thenReturn(Optional.of(existingUserModel));

        ResponseEntity<Object> response = userController.updateCliente(clienteId, userDto);

        verify(userService, times(1)).findById(clienteId);
        verify(userService, times(1)).save(existingUserModel);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente Alterado com sucesso", response.getBody());
    }


    @Test
    public void naoDeveAlterarCliente() {
        UUID clienteId = UUID.randomUUID();
        UserDto userDto = new UserDto();

        when(userService.findById(clienteId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userController.updateCliente(clienteId, userDto);

        verify(userService, times(1)).findById(clienteId);
        verify(userService, never()).save(any(UserModel.class));

        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente não encontrado", response.getBody());
    }

    @Test
    public void deveDeletarCliente() {
        UUID clienteId = UUID.randomUUID();

        UserModel existingUserModel = new UserModel();
        when(userService.findById(clienteId)).thenReturn(Optional.of(existingUserModel));

        ResponseEntity<Object> response = userController.deleteCliente(clienteId);

        verify(userService, times(1)).findById(clienteId);
        verify(userService, times(1)).delete(existingUserModel);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente Deletado com sucesso", response.getBody());
    }

    @Test
    public void naoDeveDeletarCliente() {
        UUID clienteId = UUID.randomUUID();

        when(userService.findById(clienteId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userController.deleteCliente(clienteId);

        verify(userService, times(1)).findById(clienteId);
        verify(userService, never()).delete(any(UserModel.class));

        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente não encontrado", response.getBody());
    }

    @Test
    public void deveListarDadosClienteIndividual() {
        UUID clienteId = UUID.randomUUID();

        UserModel existingUserModel = new UserModel();
        when(userService.findById(clienteId)).thenReturn(Optional.of(existingUserModel));

        ResponseEntity<Object> response = userController.getOneCliente(clienteId);

        verify(userService, times(1)).findById(clienteId);

        assertSame(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingUserModel, response.getBody());
    }

    @Test
    public void naoDeveListarDadosClienteIndividual() {
        UUID clienteId = UUID.randomUUID();

        UserModel existingUserModel = new UserModel();
        when(userService.findById(clienteId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = userController.getOneCliente(clienteId);

        verify(userService, times(1)).findById(clienteId);

        assertSame(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente não encontrado", response.getBody());
    }





}
