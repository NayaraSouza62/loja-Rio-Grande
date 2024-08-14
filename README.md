## Loja Calhas Rio Grande

Este é um sistema de controle de Ordem de Serviço (OS) desenvolvido em Java. O projeto permite gerenciar as ordens de serviço, registrar clientes e emitir relatórios de OS, Serviços e Clientes.

## Funcionalidades

- **Emitir OS:** Criação de Ordem de serviço.
- **Consultar OS:** Permite buscar ordens de serviço existentes.
- **Atualizar OS:** Atualiza informações de ordens de serviço existentes.
- **Excluir OS:** Remove ordens de serviço do sistema.
- **Pesquisar Cliente:** Busca clientes cadastrados para associá-los a uma ordem de serviço.
- **Tabela de Clientes:** Exibe uma lista de clientes com informações como nome, telefone, endereço e CPF.
- **Impressão:** Permite a impressão de OS, relatório de clientes e serviços em PDF.

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

1. **Banco de Dados**: Certifique-se de que o MySQL está instalado e configurado corretamente.
2. **Dependências**: As dependências estão listadas no `pom.xml`. Utilize o Maven para gerenciá-las.
3. **Compilação**: Para compilar o projeto, execute:

## Estrutura do Banco de Dados

A estrutura do banco de dados está documentada no arquivo [estrutura_banco.md](estrutura_banco.md).

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seuusuario/seurepositorio.git


