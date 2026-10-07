# Sistema de Login Seguro (Java Spring Boot, Thymeleaf & MongoDB Atlas)

## Como Executar Localmente

### Pré-requisitos
* Java JDK 21 ou superior
* MongoDB Atlas.

### Passo 1: Configurar a Connection String do MongoDB Atlas
1. Acesse o painel do [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).
2. Obtenha a string de conexão no formato `mongodb+srv://...`.
3. Defina a variável de ambiente no seu sistema ou ajuste diretamente no `application.properties`:

export MONGODB_URI="mongodb+srv://<usuario>:<senha>@<cluster>.mongodb.net/<nome_do_banco>?retryWrites=true&w=majority"

## 2. Passo a Passo de Execução

### Passo 1: Clonar o Repositório
Abra o terminal e execute o comando abaixo para clonar o repositório do GitHub e aceder à pasta do projeto:

git clone [https://github.com/seu-usuario/sistema-de-login-seguro.git](https://github.com/seu-usuario/sistema-de-login-seguro.git)
cd sistema-de-login-seguro