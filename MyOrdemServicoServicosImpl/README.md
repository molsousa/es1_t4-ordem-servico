# MyOrdemServicoServicosImpl

Implementação de exceções, regras de negócios, acesso ao banco e gerenciamento de serviços.

## Validações

### OSProduto

As validações incluem:

- Verificar de um ID é válido.
- Verificar se o valor cobrado é válido.

### OSTipoServico 

As validações incluem:

- Verificar de um ID é válido.
- Verificar se o valor cobrado é válido.

### OrdemServico 

As validações incluem: 

- Verificar se o número da OS é válido.
- Verificar se a descrição da OS está em branco.
- Verificar se a data enviada é a data atual.

### Produto 

As validações incluem:

- Verificar se o nome está em branco.
- Verificar se o valor referência é válido.
- Verificar se o ID é válido.

### TipoEquipamento

As validações incluem:

- Verificar se o nome está em branco.
- Verificar se o ID é válido.

### TipoServico

As validações incluem:

- Verificar se o nome está em branco.
- Verificar se o valor referência é válido.
- Verificar se o ID é válido.

## Gerenciamento de Serviços 

Para cada caso de uso é criado um UCGeralServicos. Em cada classe UC, tem a abertura de conexão e o `commit` caso alguma transação ou consulta tenha sido feita com sucesso.

Caso tenha algum erro, é feito `rollback`, evitando que o dado seja inserido no banco.

### [UCClienteGeralServicos](./src/unioeste/geral/ordemservico/manager/UCClienteGeralServicos.java)

Contém classes de transação e consulta para "Manter Cliente".

```java
public Cliente cadastrarCliente(String nome, String cpf, EnderecoEspecifico enderecoEspecifico, 
                List<Fone> fones, List<Email> emails)

public Cliente obterClientePorCPF(String cpf)

public List<Cliente> obterClientePorPadrao(String padrao)
```

### [UCOrdemServicoGeralServicos](./src/unioeste/geral/ordemservico/manager/UCOrdemServicoGeralServicos.java)

Contém classes de transação e consulta para "Manter OS".

```java
public boolean cadastrarOrdemServico(OrdemServico os, int idStatusOS)

public OrdemServico obterOrdemServico(int nroOS)

public boolean atualizarTecnicoOS(int idTecnico, int nroOS)

public boolean atualizarStatusOS(int nroOS, int idStatusOS)

public double cadastrarOSProduto(int nroOS, int idProduto, double valorCobrado, int qtd)

public boolean cadastrarOSTipoServico(int nroOS, int idTipoServico, double valorCobrado)

public boolean removerOrdemServico(int nroOS)

public boolean removerProdutoOS(int idProduto, int nroOS)

public boolean removerTipoServicoOS(int idTipoServico, int nroOS)
```

### [UCProdutoGeralServicos](./src/unioeste/geral/ordemservico/manager/UCProdutoGeralServicos.java)

Contém classes de transação e consulta para "Manter Produto".

```java
public int cadastrarProduto(String nome, double valor)

public List<Produto> listarProdutos()
```

### [UCTipoEquipamentoGeralServicos](./src/unioeste/geral/ordemservico/manager/UCTipoEquipamentoGeralServicos.java)

Contém classes de transação e consulta para "Manter Tipo Equipamento".

```java
public int cadastrarTipoEquipamento(String nomeTipoEquipamento)

public List<TipoEquipamento> listarTipoEquipamento()

public TipoEquipamento obterTipoEquipamento(int codTipoEquipamento)
```

### [UCTipoServicoGeralServicos](./src/unioeste/geral/ordemservico/manager/UCTipoServicoGeralServicos.java)

Contém classes de transação e consulta para "Manter Tipo Serviço".

```java
public int cadastrarTipoServico(String nome, double valor)

public List<TipoServico> listarTipoServicos()
```

Para cada método implementado em cada uma das classes, é criado Servlet em [MyOrdemServicoServicos](../MyOrdemServicoServicos/src/java/unioeste/geral/ordemservico/api/)

#### Exceção

Para cada validação, é lançada uma exceção em caso de erro, verificar [`exception/`](./src/unioeste/geral/ordemservico/exception/).
