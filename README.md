# Cat Service

Система учета пользователей и их питомцев (котов), разработанная с использованием гексагональной архитектуры на базе Java Spring.

## О проекте

Cat Service представляет собой RESTful API для управления информацией о котах и их владельцах. Система позволяет:
- Создавать, обновлять и удалять профили владельцев
- Регистрировать, обновлять и удалять котов
- Устанавливать отношения владения между владельцами и котами
- Управлять дружескими связями между котами
- Аутентифицировать и авторизовывать пользователей

## Архитектура

Проект построен с использованием гексагональной архитектуры (портов и адаптеров) и разделен на три модуля:

1. **cat-service-application** - отвечает за REST API и авторизацию
2. **cat-service-domain** - содержит бизнес-логику, реализованную на чистой Java
3. **cat-service-infrastructure** - реализует адаптеры репозиториев и обеспечивает транзакционность

## Технологии

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Gradle
- Hexagonal Architecture
- Flyway
- Lombok
- MapStruct

## Инструкция по деплою

### Предварительные требования
- Docker и Docker Compose
- Git

### Шаги для деплоя

1. Клонировать репозиторий:
   ```bash
   git clone <url-репозитория>
   cd cat-service
   ```

2. Запустить сервисы:
   ```bash
   docker-compose up -d
   ```

## API и примеры использования

### Регистрация пользователя
```bash
curl -X POST http://localhost:8080/sign-up -H "Content-Type: application/json" -d '{"username":"user1","password":"password","name":"Owner Name","birthDate":"2000-01-01"}'
```

### Создание кота
```bash
curl -X POST http://localhost:8080/api/v1/cats -H "Content-Type: application/json" -d '{"name":"Fluffy","birthDate":"2020-01-01","color":"WHITE"}' -u user1:password
```

### Получение информации о коте
```bash
curl -X GET http://localhost:8080/api/v1/cats/{id} -u user1:password
```

### Добавление владения котом
```bash
curl -X PATCH http://localhost:8080/api/v1/owners/{ownerId}/make_ownership/{catId} -u user1:password
```

### Создание дружеской связи между котами
```bash
curl -X PATCH http://localhost:8080/api/v1/cats/{catId}/make_friendship/{friendId} -u user1:password
```

## Структура проекта

```
cat-service/
├── cat-service-application/     # REST API, контроллеры, DTO
├── cat-service-domain/          # Бизнес-логика, модели, сервисы
├── cat-service-infrastructure/  # Репозитории, JPA, транзакции
├── docker-compose.yml           # Docker Compose конфигурация
└── Dockerfile                   # Dockerfile для сборки приложения
```

## Безопасность

В приложении реализовано разграничение доступа на основе владения. Пользователи могут видеть и управлять только своими котами. Проверки доступа осуществляются на уровне методов с использованием аннотаций `@PreAuthorize` и `@PostAuthorize`.

