# MyOrdemServicoBO

Classes de negócio para estudo de caso Ordem de Serviço.

## [OrdemServico](./src/unioeste/geral/ordemservico/bo/OrdemServico.java)

Implementação da classe `OrdemServico`. 

- `Date` vem da classe `java.sql.Date`.
- `Tecnico` e `Cliente` herdam da classe `PessoaFisica`.
- Conforme planejado no [diagrama de classes](../README.md), `OSProduto` e `OSTipoServico` são composições para `OrdemServico`.

```java
public class OrdemServico implements Serializable {

    public final static long serialVersionUID = 1;

    private int nro;
    private Date dtEmissao;
    private String descProblema;
    private double valorTotalOS;
    private Tecnico tecnico;
    private Cliente cliente;
    private List<OSTipoServico> osTipoServicos;
    private List<OSProduto> osProdutos;
    private TipoEquipamento tipoEquipamento;
    private StatusOS statusOS;
...
}
```

## [OSTipoServico](./src/unioeste/geral/ordemservico/bo/OSTipoServico.java)

Em `OSTipoServico` tem a classe `TipoServico` e o valor cobrado pelo serviço. `OSTipoServico` existe apenas para a classe `OrdemServico`, sendo assim, caso uma OS seja removida, as chaves associadas em `OSTipoServico` devem ser removidas.

```java
public class OSTipoServico implements Serializable {

    public final static long serialVersionUID = 1;

    private TipoServico tipoServico;
    private double valor;
...
}
```

## [OSProduto](./src/unioeste/geral/ordemservico/bo/OSProduto.java)

Possui uma classe `Produto`, quantidade e o valor cobrado pelos produtos usados na OS. Assim como `OSTipoServico`, a classe existe apenas para a classe `OrdemServico`, então os valores são removidos após a remoção de uma OS.

```java
public class OSProduto implements Serializable {

    public final static long serialVersionUID = 1;

    private int qtd;
    private double valor;
    private Produto produto;
...
}
```
