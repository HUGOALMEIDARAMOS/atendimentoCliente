package com.cielo.ada.Cliente.specifications;

import com.cielo.ada.Cliente.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {
    @Or({
        @Spec(path = "clienteType", params = "buscar" ,spec = Equal.class),
        @Spec(path = "cnpj", params = "buscar" , spec = Equal.class),
        @Spec(path = "razao_social",  params = "buscar" , spec = Equal.class),
        @Spec(path = "mcc", params = "buscar" , spec = Equal.class),
        @Spec(path = "cpf_contato", params = "buscar" , spec = Equal.class),
        @Spec(path = "nome_contato", params = "buscar" , spec = Like.class),
        @Spec(path = "email", params = "buscar" , spec = Like.class),
        @Spec(path = "cpf",  params = "buscar" ,spec = Equal.class),
        @Spec(path = "nome_pessoa_fisica",  params = "buscar" ,spec = Like.class)
    })
    public interface ClienteSpec extends Specification<UserModel>{}
}
