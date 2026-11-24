# MyOrdemServicoServicos

Este projeto é a implementação de classes limítrofes, que vão conversar com as requisições http da camada de apresentação e solicitar serviços ao [`MyOrdemServicoServicosImpl`](../MyOrdemServicoServicosImpl/).

## [CORS Filter](./src/java/unioeste/geral/ordemservico/config/CorsFilter.java)

CORS (Cross-Origin Resource Sharing) é um componente de software que serve como um porteiro. Ele decide se o servidor deve ou não permitir que páginas web acessem os recursos.

Nesse trabalho ele permite tudo para demonstrar a implementação dos serviços.

## Requisições

Sempre que tiver uma classe limítrofe, será adicionado um prefixo `Servlet` para indicar que o é.

### POST

Abaixo, segue as transações envolvendo cadastro no banco.

- [CadastrarClienteServlet](./src/java/unioeste/geral/ordemservico/api/cliente/CadastrarClienteServlet.java): cadastro de um cliente com todos seus dados cadastrais. Recebe um objeto `Cliente` serializado como parâmetro.
- [CadastrarOSProdutoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/CadastrarOSProdutoServlet.java): cadastro de um produto a uma OS, apesar de parecer ser mais simples (necessitar de um update), na verdade aqui acontece uma inserção na tabela associativa `OSProduto`.
- [CadastrarOSTipoServicoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/CadastrarOSTipoServicoServlet.java): cadastro de um tipo de serviço a uma OS, mesma situação do `OSProduto`. E em ambos `Produto` e `TipoServico` permanecem inalterados no banco, altera apenas a associativa.
- [CadastrarOrdemServicoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/CadastrarOrdemServicoServlet.java): cadastro de uma ordem de serviço, aqui é muito parecido com o `Cliente`, recebe como classe serializada e cadastra o objeto, no entanto, nem todos os dados são preenchidos no cadastro, apenas os mais imediatos, como o status da OS, data de emissão e a descrição da OS.
- [CadastrarProdutoServlet](./src/java/unioeste/geral/ordemservico/api/produto/CadastrarProdutoServlet.java): aqui é feito o cadastro de produtos. Recebe um objeto `Produto` serializado como parâmetro e retorna o código gerado pelo banco.
- [CadastrarTipoEquipamentoServlet](./src/java/unioeste/geral/ordemservico/api/produto/CadastrarProdutoServlet.java): cadastro de tipo de equipamento no banco, aqui são recebidos o nome do tipo de equipamento para o cadastro e enviado o ID gerado.
- [CadastrarTipoServicoServlet](./src/java/unioeste/geral/ordemservico/api/tiposervico/CadastrarTipoServicoServlet.java): da mesma forma do cadastro de produtos, para cadastrar um tipo de serviço, é recebido um objeto `TipoServico` serializado e inserido no banco, retornando o código gerado.

### PUT

Abaixo, segue as transações envolvendo atualizações no banco.

- [AtualizarStatusOSServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/AtualizarStatusOSServlet.java): atualiza o status da OS conforme o ID requisitado.
- [AtualizarTecnicoOSServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/AtualizarTecnicoOSServlet.java): atualiza o técnico responsável pela OS, conforme o CPF requisitado.

### GET 

Abaixo, segue as consultas.

- [ObterClientePorCPFServlet](./src/java/unioeste/geral/ordemservico/api/cliente/ObterClientePorCPFServlet.java): retorna objeto `Cliente` serializado com base no CPF fornecido.
- [PesquisarClienteServlet](./src/java/unioeste/geral/ordemservico/api/cliente/PesquisarClienteServlet.java): retorna uma lista de `Cliente` serializados com base no padrão de texto fornecido.
- [ObterListaOrdemServicoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/ObterListaOrdemServicoServlet.java): retorna uma lista com todas as ordens de serviço no banco.
- [ObterOrdemServicoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/ObterOrdemServicoServlet.java): retorna um objeto `OrdemServico` serializado com base no número da OS fornecido.
- [ObterListaProdutoServlet](./src/java/unioeste/geral/ordemservico/api/produto/ObterListaProdutoServlet.java): retorna uma lista dos produtos cadastrados no banco, com ID, nome e valor referência.
- [ObterListaTipoEquipamentoServlet](./src/java/unioeste/geral/ordemservico/api/tipoequipamento/ObterListaTipoEquipamentoServlet.java): retorna uma lista de tipos de equipamento, com ID e nome do tipo de equipamento.
- [ObterTipoEquipamentoServlet](./src/java/unioeste/geral/ordemservico/api/tipoequipamento/ObterTipoEquipamentoServlet.java): retorna um objeto `TipoEquipamento` serializado com ID e nome do tipo de equipamento,
- [ObterListaTipoServicoServlet](./src/java/unioeste/geral/ordemservico/api/tiposervico/ObterListaTipoServicoServlet.java): retorna uma lista de tipos de serviços cadastrados no banco, com ID, nome e valor de referência.

### DELETE 

Abaixo, segue as transações envolvendo remoções no banco.

- [RemoverOrdemServicoServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/RemoverOrdemServicoServlet.java): remove uma ordem de serviço do banco. Retorna verdadeiro caso tenha sido removido.
- [RemoverProdutoOSServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/RemoverProdutoOSServlet.java): remove um produto da ordem de serviço, o dado é removido da tabela associativa, e não da tabela produto.
- [RemoverTipoServicoOSServlet](./src/java/unioeste/geral/ordemservico/api/ordemservico/RemoverTipoServicoOSServlet.java): remove um tipo de serviço da ordem de serviço, assim como no produto, o dado é removido da tabela associativa apenas. Em ambos os casos, é retornado verdadeiro caso tenha sido removido com sucesso.
