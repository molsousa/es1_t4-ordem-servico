# Implementação de camada de negócios para Ordem de Serviço.

**Disciplina:** Engenharia de Software I.

**Professor:** Roberto Gil Brasil.

**Estudantes:** [Julia Gabrielly](https://github.com/yuchenkkao), [Marcos Sousa](https://github.com/molsousa).

## Sumário

- [Implementação](#implementação)
- [Os projetos](#os-projetos)
    - [MyInfraAPI](#myinfraapi)
    - [MyPessoaBO](#mypessoabo)
    - [MyPessoaServicosImpl](#mypessoaservicosimpl)
    - [MyOrdemServicoBO](#myordemservicobo)
    - [MyOrdemServicoServicosImpl](#myordemservicoservicosimpl)
    - [MyOrdemServicoServicos](#myordemservicoservicos)
- [Fluxo de dados](#fluxo-de-dados)
- [Dependências](#dependências)
- [Compilação](#compilação)
- [Referencias](#referencias)

## Descrição

Este repositório contém seis projetos em Java para a Implementação da camada de negócios do estudo de caso Ordem de Serviço para a disciplina de Engenharia de Software I.

## Implementação 

Os códigos-fontes foram escritos todos em Java 8. Cada um dos projetos compõem uma parte única de funcionamento do sistema, no entanto, ainda faltam outros dois projetos para o seu funcionamento, esses que estão disponíveis na [implementação de endereço](https://github.com/molsousa/es1_t3-endereco).

## Os projetos

### MyInfraAPI

Esse projeto é um dos que não estão diretamente não é ligado aos demais projetos do estudo de caso, a infra faz apenas a conexão com o servidor, ela pode ser utilizada em diferentes projetos, segue abaixo a estrutura das pastas:

```ASCII 
└── unioeste/
    └── apoio/
        └── bd/
            └── ConnectionFactory.java
```

### MyPessoaBO

Esse projeto é um dos projetos genéricos que podem ser adicionados a quaisquer outros estudos de caso. O `MyPessoaBO` contém as classes de `PessoaFisica` e `PessoaJuridica` que herdam de `Pessoa` e podem ser utilizadas em qualquer estudo de caso para representar um usuário cadastrado no banco. Segue abaixo um trecho de um diagrama de classes qualquer demonstrando as composições e associações.

<img src="./img/Diagrama-Classe-Pessoa.png" alt="Diagrama de classes de negócio de pessoa para implementação." style="background-color: white; padding: 10px; border-radius: 5px;" />

Importante notar que, caso um objeto `Pessoa` seja apagado, seus `fones` e `emails` e `enderecoEspecifico` são apagados juntos, pois são uma composição com `Pessoa`, dependem da classe pra existir. O mesmo não ocorre (e não deve ocorrer) com `ddi` e `ddd`.

Segue abaixo a estrutura de pacotes desse projeto:

```ASCII 
└── unioeste/
    └── geral/
        └── pessoa/
            └── bo/
                ├── DDD.java
                ├── DDI.java
                ├── Email.java
                ├── EnderecoEspecifico.java
                ├── Fone.java
                ├── Pessoa.java
                ├── PessoaFisica.java
                └── PessoaJuridica.java
```

### MyPessoaServicosImpl

Esse projeto é o último projeto genérico desse repositório, onde contém a implementação de serviços, validações e acessos ao banco envolvendo diretamente a classe `Pessoa`. Aqui são feitas as validações de `cpf`, `fones`, `emails` e etc. 

Como não temos classes de estudo de caso, os serviços se limitam a acessar `Fone` e `Email`. Não está incluso `EnderecoEspecifico` como um serviço já que endereço possui um projeto somente para ele.

Segue abaixo a estrutura de pacotes desse projeto:

```ASCII
└── unioeste/
    └── geral/
        └── pessoa/
            ├── col/
            │   ├── ColDDD.java
            │   ├── ColDDI.java
            │   ├── ColEmail.java
            │   ├── ColFone.java
            │   ├── ColPessoa.java
            │   └── ColPessoaFisica.java
            ├── dao/
            │   ├── EmailDAO.java
            │   └── FoneDAO.java
            ├── exception/
            │   ├── CPFException.java
            │   ├── DDDException.java
            │   ├── DDIException.java
            │   ├── EmailException.java
            │   ├── FoneException.java
            │   ├── NomeException.java
            │   └── PessoaException.java
            └── manager/
                └── UCPessoaGeralServicos.java
```

### MyOrdemServicoBO

Aqui são feitas as classes de negócio do estudo de caso, daqui pra frente os próximos projetos são os únicos que podem sofrer fortes alterações dependendo da necessidade do cliente, segue abaixo o diagrama de classes completo, incluindo classes genéricas e estudo de caso:

<img src="./img/Diagrama-Classe-OS.png" alt="Diagrama de classes de negócio do estudo de caso para implementação." style="background-color: white; padding: 10px; border-radius: 5px;" />

Como se percebe no diagrama de classes, nem todo usuário precisa ser modelado, apenas os que manterão dados na persistência. O estudo de caso inclui `Atendente` como um dos usuários do sistema. Porém não é necessário estar no sistema.

Segue abaixo a estrutura de pacotes:

```ASCII
└── unioeste/
    └── geral/
        └── ordemservico/
            └── bo/
                ├── Cliente.java
                ├── OSProduto.java
                ├── OSTipoServico.java
                ├── OrdemServico.java
                ├── Produto.java
                ├── StatusOS.java
                ├── Tecnico.java
                ├── TipoEquipamento.java
                └── TipoServico.java
```

São inclusas classes que advém de relacionamentos N:N, essas fazem parte do sistema principalmente em termos de histórico que será abordado no próximo tópicos.

### MyOrdemServicoServicosImpl 

Aqui são feitas as regras de negócio envolvendo o estudo de caso, aqui são implementados serviços, regras de negócio e validações e acessos ao banco, também são implementados exceções personalizadas ao estudo de caso. 

Segue abaixo a estrutura de pacotes:

```ASCII
└── unioeste/
    └── geral/
        └── ordemservico/
            ├── col/
            │   ├── ColCliente.java
            │   ├── ColOSProduto.java
            │   ├── ColOSTipoServico.java
            │   ├── ColOrdemServico.java
            │   ├── ColProduto.java
            │   ├── ColStatusOS.java
            │   ├── ColTecnico.java
            │   ├── ColTipoEquipamento.java
            │   └── ColTipoServico.java
            ├── dao/
            │   ├── ClienteDAO.java
            │   ├── OSProdutoDAO.java
            │   ├── OSTipoServicoDAO.java
            │   ├── OrdemServicoDAO.java
            │   ├── ProdutoDAO.java
            │   ├── StatusOSDAO.java
            │   ├── TecnicoDAO.java
            │   ├── TipoEquipamentoDAO.java
            │   └── TipoServicoDAO.java
            ├── exception/
            │   ├── OrdemServicoException.java
            │   ├── OrdemServicoInvalidoException.java
            │   ├── ProdutoException.java
            │   ├── TipoEquipamentoException.java
            │   └── TipoServicoException.java
            └── manager/
                ├── UCClienteGeralServicos.java
                ├── UCOrdemServicoGeralServicos.java
                ├── UCProdutoGeralServicos.java
                ├── UCTipoEquipamentoGeralServicos.java
                └── UCTipoServicoGeralServicos.java
```

Para cada caso de uso, é criado um `UCNomeServicos` em `manager/`. Mais abaixo nesse documento será explicado o fluxo de dados nos projetos.

### MyOrdemServicoServicos

Aqui são atendidas as requisições `http` da camada de apresentação. Cada um dos casos de uso tem seu próprio pacote dedicado. Sâo feitas operações de `Create`, `READ`, `UPDATE` e `DELETE`, o `CRUD`. Para esse estudo de caso, fora focado mais no caso de uso `Ordem de Serviço`, dado que é o mais importante.

Além disso, temos a classe `CorsFilter` que auxilia em enviar dados a camada de apresentação, filtrando cada uma das chamadas `http` feitas.

Segue abaixo a estrutura de pacotes:

```ASCII
└── unioeste/
    └── geral/
        └── ordemservico/
            ├── api/
            │   ├── cliente/
            │   │   ├── CadastrarClienteServlet.java
            │   │   ├── ObterClientePorCPFServlet.java
            │   │   └── PesquisarClienteServlet.java
            │   ├── ordemservico/
            │   │   ├── AtualizarTecnicoOSServlet.java
            │   │   ├── CadastrarOSProdutoServlet.java
            │   │   ├── CadastrarOSTipoServicoServlet.java
            │   │   ├── CadastrarOrdemServicoServlet.java
            │   │   ├── ObterOrdemServicoServlet.java
            │   │   ├── RemoverOrdemServicoServlet.java
            │   │   ├── RemoverProdutoOSServlet.java
            │   │   └── RemoverTipoServicoOSServlet.java
            │   ├── produto/
            │   │   ├── CadastrarProdutoServlet.java
            │   │   └── ObterListaProdutoServlet.java
            │   ├── tipoequipamento/
            │   │   ├── CadastrarTipoEquipamentoServlet.java
            │   │   ├── ObterListaTipoEquipamentoServlet.java
            │   │   └── ObterTipoEquipamentoServlet.java
            │   └── tiposervico/
            │       ├── CadastrarTipoServicoServlet.java
            │       └── ObterListaTipoServicoServlet.java
            └── config/
                └── CorsFilter.java
```

## Fluxo de dados

O fluxo de dados na camada de negócio pode ser representada conforme a imagem abaixo: 

![Fluxo de dados OS](./img/Ordem-Servico-Backend.drawio.png)

Nesse caso, o diagrama foi feito em cima do estudo de caso, mas pode valer para qualquer um outro.

Para esse trabalho, fora utilizado o `Glassfish` como servidor de aplicação e de dados. `PostgreSQL` como o SGDB responsável pela comunicação com o banco de dados.

Quando a camada de apresentação faz uma solicitação `POST` ao back, o fluxo de dado ocorre da seguinte forma: 

1. O servidor de aplicação é o responsável por ligar a camada de apresentação à camada de negócios. O mesmo servidor é usado para realizar o *Connection Pool*, que aqui está configurado com um número baixo de usuários.

2. A `Infra` é utilizada para abrir conexão com o banco acessando o banco via `Glassfish`.

3. As classes `BO` transitam em todos os pacotes, podem fazer parte da camada de apresentação se for o caso. São as classes mais flexíveis do projeto.

4. `Servlet` recebe a requisição e envia ao `Manager` o objeto a ser inserido no BD.

5. `Manager` abre conexão com o banco e acessa a classe `Col` responsável pelo cadastro.

6. `Col` valida todas as informações necessárias, aplica regras de negócios, como de formatação de dados ou espaços desnecessários por exemplo e chama a classe `DAO`.

7. Na classe `DAO` é feita a inserção no banco de dados e obrigatoriamente é retornado um valor de confirmação da inserção, seja ele booleano ou outro dado qualquer (como ID).

8. Caso o dado seja inserido, o `DAO` retorna sucesso ao `Col`, esse que retorna sucesso ao `Manager` que aplica o `commit` na transação. Caso houvesse qualquer erro durante a transação, o `Manager` aplicaria o `rollback` e jogaria a exceção pro `Servlet`, que em caso de sucesso, retorna o valor para a camada de apresentação em formato de texto.

Obs.: As exceções personalizadas podem ser aplicadas tanto no `Col` quanto no `DAO`. Por exemplo, Caso um ID seja inválido, o `Col` pode retornar uma exceção com *ID_INVALIDO* para o `Manager`. Caso o DAO perceba que um dado não pode ser cadastrado por algum erro que não envolva sintaxe SQL, ele pode acabar por retornar uma exceção personalizada também.

## Dependências

Para o devido funcionamento da camada de apresentação é necessário gerar um executável `.jar` e incluir como biblioteca nos seguintes projetos:

- `MyPessoaBO` precisa de `MyEnderecoBO`, ver [implementação de endereço](https://github.com/molsousa/es1_t3-endereco).

- `MyPessoaServicosImpl` precisa de `MyPessoaBO` e `MyInfraAPI`.

- `MyOrdemServicoBO` precisa de `MyPessoaBO`.

- `MyOrdemServicoServicosImpl` precisa de `MyInfraAPI`, `MyOrdemServicoBO`, `MyPessoaServicosImpl`, `MyPessoaBO`, `MyEnderecoServicosImpl` e `MyEnderecoBO`.

- `MyOrdemServicoServicos` precisa de `MyOrdemServicoServicosImpl`, `MyOrdemServicoBO`, `MyPessoaServicosImpl`, `MyPessoaBO`, `MyEnderecoServicosImpl`, `MyEnderecoBO`, `jackson-databind-2.13.4.jar`, `jackson-core-2.13.4.jar` e `jackson-annotations-2.13.4.jar`.

## Compilação

Para a compilação é preciso limpar e executar todos os projetos e colocar o servidor no ar pelo MyEnderecoServicos, após isso, pode ser feita qualquer requisição na camada de apresentação.

## Referencias

POSTGRESQL. PostgreSQL: The World's Most Advanced Open Source Relational Database. [S. l.]: PostgreSQL Global Development Group, [2026?]. Disponível em: https://www.postgresql.org/. Acesso em: 21 nov. 2026.

GITHUB. FasterXML/jackson-annotations: Core annotations (annotations that only depend on jackson-core) for Jackson data processor. [S. l.]: GitHub, [2026?]. Disponível em: https://github.com/FasterXML/jackson-annotations. Acesso em: 21 nov. 2026.

ECLIPSE FOUNDATION. Eclipse GlassFish. [S. l.]: Eclipse Foundation, [2026?]. Disponível em: https://glassfish.org/. Acesso em: 21 nov. 2026.

JGRAPH LTD. Security-first diagramming for teams. [S. l.]: draw.io, [2026]. Disponível em: https://www.drawio.com. Acesso em: 21 nov. 2026.

ICON-ICONS. Icon-Icons. [S. l.], [s. d.]. Disponível em: https://icon-icons.com/. Acesso em: 15 nov. 2026.

ICONSDB. IconsDB. [S. l.], [s. d.]. Disponível em: https://www.iconsdb.com/. Acesso em: 15 nov. 2026.
