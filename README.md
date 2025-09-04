# GestaoHaras

## Executando os testes básicos

Execute a classe `haras.Teste` para realizar testes rápidos das funcionalidades de negócio.

## Persistência em CSV com OpenCSV

O sistema agora suporta salvar e carregar dados em CSV (além da serialização em `.dat`).

### Agora com Maven

O projeto foi convertido para Maven. Não é necessário baixar JARs manualmente.

#### Comandos Maven (Windows/PowerShell)

```powershell
# Compilar
mvn -q clean compile

# Executar a UI de Console
mvn -q exec:java -Dexec.mainClass="haras.ui.ConsoleApp"

# (Opcional) Executar a classe de testes existente
mvn -q exec:java -Dexec.mainClass="haras.Teste"

# Gerar JAR
mvn -q package
```

Se usar VS Code/Eclipse, importe como projeto Maven (há `pom.xml`).

### Arquivos CSV gerados

Os CSVs são gravados em `dados/data/`:
- `animais.csv`
- `clientes.csv`

Na UI de console, use as opções “Exportar CSV” ou “Importar CSV”.

## Interface de Usuário (Console)

Foi adicionada a classe `haras.ui.ConsoleApp` com menu para:
- Cadastrar/listar/excluir Animais e Clientes
- Exportar/Importar CSV de Animais e Clientes

## Próximos Passos (sugestão)

- Replicar CSV para: Serviços, Contratos, Eventos, Atendimentos, Treinamentos e Colaboradores.
- Melhorar mensagens de erro e validações.

## Preparando a Entrega

1. Gere um `.zip` contendo apenas a pasta `src/` com os `.java`.
2. Confirme que binários (`bin/`), dados (`dados/`) e libs não estão dentro do zip.
3. Inclua neste README as instruções acima (dependências e execução).
