package com.cielo.ada.Cliente.models;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FilaAtendimento {

    private Queue<UserModel> fila = new LinkedList<>();
    private List<UserModel> atendimentosConcluidos = new ArrayList<>();

    public void adicionarCliente(UUID userId, String razaoSocial, String nomePessoaFisica) {
        UserModel cliente = new UserModel();
        cliente.setUserId(userId);
        cliente.setRazao_social(razaoSocial);
        cliente.setNome_pessoa_fisica(nomePessoaFisica);
        fila.add(cliente);
    }

    public UserModel proximoCliente() {
        UserModel clienteAtendido =  fila.poll();
        if (clienteAtendido != null) {
            atendimentosConcluidos.add(clienteAtendido);
        }
        return clienteAtendido;
    }

    public List<UserModel> listarClientesNaFila() {
        List<UserModel> clientesNaFila = new ArrayList<>();

        for (UserModel cliente : fila) {
            if (cliente.getUserId() != null && cliente.getRazao_social() != null && cliente.getNome_pessoa_fisica() != null) {
                UserModel clienteFiltrado = new UserModel();
                clienteFiltrado.setUserId(cliente.getUserId());
                clienteFiltrado.setRazao_social(cliente.getRazao_social());
                clienteFiltrado.setNome_pessoa_fisica(cliente.getNome_pessoa_fisica());
                clientesNaFila.add(clienteFiltrado);
            }
        }
        System.out.println(clientesNaFila);
        return clientesNaFila;
    }

    public List<UserModel> getAtendimentosConcluidos() {
        return atendimentosConcluidos;
    }
}
