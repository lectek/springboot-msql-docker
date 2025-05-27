# Embalando

Sistema de automação para lojas de embalagens, desenvolvido em Java (Maven) com foco em controle de pedidos, estoque e cadastro de clientes.

## Funcionalidades

- Cadastro de produtos e clientes
- Cálculo de pedidos e preços
- Monitoramento de temperatura de itens sensíveis (futuro)
- Registro e histórico de entregas
- Controle de estoque e reposição
- Integração com WhatsApp (futuro)
- Página HTML para pedidos online (futuro)

## Tecnologias Utilizadas

- Java 17
- Maven
- HTML5 e CSS3
- Spring Boot (em desenvolvimento)
- MySQL (em desenvolvimento)
- Git e GitHub para versionamento

 
 Estrutura de Pastas
📁 embalando/
├── 📁 dados/ # Dados manuais inseridos no sistema (produtos, clientes, etc.)
├── 📁 src/
│ └── 📁 main/ # Código-fonte principal (Java)
├── 📁 alvo/ # Pasta gerada automaticamente para compilações (pode ser renomeada de 'target')
├── .gitignore # Define arquivos que não devem ser versionados
├── pom.xml # Gerenciador de dependências e build (Maven)


 Etapas do Projeto

| Etapa | Descrição |
|-------|-----------|
| ✅ Fase 1 | Cadastro manual de produtos e preços |
| ✅ Fase 2 | Cálculo automatizado de pedidos |
| ✅ Fase 3 | Registro de entregas |
| ✅ Fase 4 | Controle de estoque |
| 🛠️ Fase 5 | Monitoramento de temperatura |
| 🔄 Fase 6 | Integração com WhatsApp |
| 🖥️ Fase 7 | Página HTML da loja integrada à API |

 Como Executar

> Pré-requisitos:
- JDK 17+
- Maven instalado

```bash
git clone https://github.com/lectek/embalando.git
cd embalando
mvn clean install


