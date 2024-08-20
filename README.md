## Loja Calhas Rio Grande

Este é um sistema de controle de Ordem de Serviço (OS) desenvolvido em Java. O projeto permite gerenciar as ordens de serviço, registrar clientes e emitir relatórios de OS, Serviços e Clientes.

## Funcionalidades

- **Emitir OS:** Criação de Ordem de Serviço.
- **Consultar OS:** Permite buscar ordens de serviço existentes.
- **Atualizar OS:** Atualiza informações de ordens de serviço existentes.
- **Excluir OS:** Remove ordens de serviço do sistema.
- **Pesquisar Cliente:** Busca clientes cadastrados para associá-los a uma ordem de serviço.
- **Tabela de Clientes:** Exibe uma lista de clientes com informações como nome, telefone, endereço e CPF.
- **Impressão:** Permite a impressão de OS, relatório de clientes e serviços em PDF.

## Descrição da Interface e Funcionalidades

Este sistema possui uma interface gráfica com os seguintes menus e funcionalidades:

### Menu de Cadastro
- **Cadastro de Clientes**: Permite adicionar, editar e excluir clientes. Inclui campos para inserir informações como nome, telefone, endereço e CPF.
- **Cadastro de Ordem de Serviço (OS)**: Permite criar e gerenciar ordens de serviço associadas aos clientes. Inclui opções para adicionar, atualizar e excluir ordens de serviço.
- **Cadastro de Serviços**: Permite registrar e gerenciar serviços oferecidos. Inclui campos para descrever e definir preços dos serviços.

### Funcionalidades dos Menus
- **CRUD**: Cada menu possui operações básicas de CRUD (Criar, Ler, Atualizar e Excluir) para gerenciar os dados.
- **Botões de Impressão**: Em cada menu (Clientes, OS e Usuários), há botões para imprimir relatórios em PDF, facilitando a geração de documentos para consulta e arquivamento.
- **Pesquisar**: Funcionalidade para buscar clientes e ordens de serviço existentes com base em critérios específicos.

### Navegação
- **Tela Principal**: Apresenta uma visão geral e acesso aos principais menus do sistema.
- **Menus de Navegação**: Os menus são acessíveis através de botões na tela principal, permitindo fácil acesso às funcionalidades de cadastro e relatórios.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **JDK:** 21
- **Gerenciador de Dependências:** Maven
- **Banco de Dados:** MySQL
- **Bibliotecas:** 
  - [JasperReports](https://community.jaspersoft.com/project/jasperreports-library) para geração de relatórios.

## Pré-requisitos

Antes de executar o projeto, certifique-se de que você tem os seguintes pré-requisitos instalados:

- **JDK 21:** [Download JDK 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- **Maven:** [Download Maven](https://maven.apache.org/download.cgi)
- **MySQL:** [Download MySQL](https://dev.mysql.com/downloads/mysql/)

## Dependências do Projeto

Este projeto utiliza as seguintes dependências:

- **JasperReports** (versão 7.0.0): Para geração de relatórios.
- **MySQL Connector** (versão 9.0.0): Para conexão com o banco de dados MySQL.
- **XML Graphics Commons** (versão 2.9): Necessário para suporte a gráficos e manipulação de documentos PDF.
- **AbsoluteLayout** (versão RELEASE220): Para gerenciamento de layout na interface gráfica.

## Configuração

1. **Banco de Dados:** Certifique-se de que o MySQL está instalado e configurado corretamente.
2. **Dependências:** As dependências estão listadas no `pom.xml`. Utilize o Maven para gerenciá-las.
3. **Compilação:** Para compilar o projeto, execute:
   ```bash
   mvn clean install

   ## Créditos e Licença dos Ícones

Os ícones utilizados neste projeto foram obtidos do Flaticon. Abaixo estão os detalhes da licença e os créditos:

### Licença dos Ícones
Os ícones usados são fornecidos por [Flaticon](https://www.flaticon.com) e estão sujeitos à licença da plataforma.




