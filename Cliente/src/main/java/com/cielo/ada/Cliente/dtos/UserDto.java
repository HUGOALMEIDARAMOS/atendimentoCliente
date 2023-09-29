package com.cielo.ada.Cliente.dtos;

import com.cielo.ada.Cliente.enums.ClienteType;
import com.cielo.ada.Cliente.validation.UseCnpjValidLength;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.logging.log4j.message.Message;

import java.util.UUID;

@Data
public class UserDto {

    private UUID userId;
    private ClienteType clienteType;
    @Nullable
    @UseCnpjValidLength
    private String cnpj;
    @Nullable
    private String razao_social;
    @NotBlank(message = "campo MCC não pode ser vazio")
    @NotNull( message = "Campo mcc não pode ser nulo")
    @Size(min = 4, max = 4, message = "campo mcc deve ter 4 caracteres")
    private String mcc;
    @Nullable
    private String cpf_contato;
    @Nullable
    @Size( max = 50, message = "campo nome_contato deve entre 5 e 50 caracteres")
    private String nome_contato;
    @NotBlank(message = "E-mail não pode ser vazio")
    @NotNull( message = "Campo Email não pode ser nulo")
    @Email(message = "E-mail é obrigatório")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$",  message = "Email invalido")
    private String email;
    @Nullable
    private String cpf;
    @Nullable
    @Size( max = 50, message = "campo nome_pessoa_fisica deve entre 5 e 50 caracteres")
    private String nome_pessoa_fisica;

}
