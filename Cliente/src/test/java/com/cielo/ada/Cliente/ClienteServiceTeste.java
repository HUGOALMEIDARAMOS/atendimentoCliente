package com.cielo.ada.Cliente;

import com.cielo.ada.Cliente.enums.ClienteType;
import com.cielo.ada.Cliente.models.UserModel;
import com.cielo.ada.Cliente.services.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ClienteServiceTeste {

    private UserService userService;
    @Test
    public void registerCliente(){
        UserModel userModel = new UserModel();
        userModel.setCnpj("12565658959585");
        userModel.setClienteType(ClienteType.valueOf("JURIDICA"));
        userModel.setRazao_social("teste de teste");
        userModel.setMcc("1430");
        userModel.setCpf_contato("63525658952");
        userModel.setNome_contato("hugo almeida ramos");
        userModel.setEmail("hugo.undb10@gmail.com");
        userModel.setCpf("");
        userModel.setNome_pessoa_fisica("");
        List<UserModel> user = userService.save(userModel);
    }
}
