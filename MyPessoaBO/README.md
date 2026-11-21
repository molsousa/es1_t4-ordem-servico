# MyPessoaBO

Classes de negócio para servir as classes genéricas `Pessoa`, `PessoaFisica` e `PessoaJuridica`.

## [Pessoa](./src/unioeste/geral/pessoa/bo/Pessoa.java)

Importante notar que conforme o diagrama de classes, `Fone` e `Email` são uma lista de atributos. Como são uma composição, devem ficar na classe Pessoa.

`EnderecoEspecifico` é uma composição de 1:1, então fica como atributo dentro de Pessoa da mesma maneira.

```java
public class Pessoa implements Serializable {

    public final static long serialVersionUID = 1;

    private String nome;
    private String nomeSocial;
    private List<Fone> fones;
    private List<Email> emails;
    private EnderecoEspecifico enderecoEspecifico;
...
}
```

Segue abaixo as classes contidas em `Fone`.

```ASCII
Fone
 ├──DDI 
 └──DDD
```

## [PessoaFisica](./src/unioeste/geral/pessoa/bo/PessoaFisica.java)

Implementação da classe de negócio de `PessoaFisica`, essa que herda da classe `Pessoa`, não sendo necessário reescrever os mesmos atributos, apenas `cpf`.

```java
public class PessoaFisica extends Pessoa implements Serializable {

    private String cpf;
...
}
```

## [PessoaJuridica](./src/unioeste/geral/pessoa/bo/PessoaFisica.java)

O mesmo ocorre com `PessoaJuridica`, a diferença fica em ter um `cnpj`.

```java
public class PessoaJuridica extends Pessoa implements Serializable {

    private String cnpj;
...
}
```

## [EnderecoEspecifico](./src/unioeste/geral/pessoa/bo/EnderecoEspecifico.java)

A classe contém número de endereço e complemento, coisa que não havia na implementação do `Endereco`.

Segue abaixo as classes contidas em `EnderecoEspecifico`.

```ASCII
EnderecoEspecifico
 └──Endereco
     ├──Bairro
     ├──Cidade
     │   └──UnidadeFederativa
     └──Logradouro
         └──TipoLogradouro
```
