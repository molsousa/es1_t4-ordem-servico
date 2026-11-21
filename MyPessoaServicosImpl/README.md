# MyPessoaServicosImpl

## Observação

A classe `Pessoa` não representa uma tabela no banco de dados, muito menos `PessoaFisica` ou `PessoaJuridica`. 

Sendo nem tudo deve ser acessado imediatamente no banco de dados. As entidades possíveis de serem acessadas no banco são `Email` e `Fone` se seguidos um padrão de desenvolvimento. Por exemplo:

Seguindo o [diagrama de classes](../README.md). Tanto `Tecnico` quanto `Cliente` possuem `fones` e `emails`. Logo é possível criar buscas enviando uma string com o complemento do nome da tabela:

```java
// Na classe ColCliente
...

colFone.cadastrarFones(conn, fone, idCliente, "Cliente");

...
```

```java
// Classe para cadastro de fone no ColFone

public int cadastrarFones(Connection conn, Fone fone, int id, String nomeTabela) 
                            throws PessoaException, SQLException {
...
}
```

```java
// Classe DAO para acesso a fones

public int cadastrarFone(Connection conn, Fone fone, int id, String nomeTabela) 
                            throws SQLException, PessoaException {
        
        String SQL = "INSERT INTO \"Fone"+nomeTabela+"\" (nro_fone,nro_ddi,nro_ddd,id_"+nomeTabela+") "
                + "VALUES (?,?,?,?) RETURNING id_fone_"+nomeTabela;
...
}
```

Não é o ideal, porém funciona bem com o PostgreSQL, tendo em vista que o mesmo atualiza o texto para minúsculo automaticamente.

## Validações

### CPF

Validação de CPF inclui:

- verificar o número de dígitos por padrão
- a formatação

Isso tudo em `ColPessoaFisica`.

### DDI 

Validação de DDI é feita:

- adicionando um `0` a frente do número caso seja digitado com dois dígitos 
- inválido caso tenha um número diferente de dois ou três dígitos.

### DDD 

Validação de DDD, assim como em DDI é feita:

- adicionando um `0` a frente do número caso seja digitado com dois dígitos
- inválido caso tenha um número diferente de dois ou três dígitos.

### Fone

Validação de Fone consiste em:

- verificar o número de dígitos
- formatação para ter um padrão como: XXXXX-XXXX ou XXXX-XXXX.

### Email 

Validação de Email consiste em:

- verificar se o padrão está correto para um email, como: joao@email.com.

#### Exceção

Para cada validação, é lançada uma exceção em caso de erro, verificar [`exception/`](./src/unioeste/geral/pessoa/exception/).
