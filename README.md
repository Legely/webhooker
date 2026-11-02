# Telegram Webhook Dispatcher 🔔

Сервис приема и логирования входящих вебхуков от внешних систем с персистентным хранением в PostgreSQL и мгновенной доставкой оповещений в Telegram.

[![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Telegram](https://img.shields.io/badge/Telegram_Bot_SDK-8.0.0-blue?style=flat-square&logo=telegram)](https://github.com/rubenlagus/TelegramBots)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-brightgreen?style=flat-square&logo=swagger)](http://localhost:8080/swagger-ui/index.html)

---

## 💡 Принцип работы
[Внешняя система / Сторонний сервис] │ ▼ (HTTP POST /api/v1/events) [Spring Boot API] ├── 1. Валидация и логирование события ──────► [PostgreSQL] └── 2. Форматирование и отправка алертов ────► [Telegram Bot] ──► [Администратор / Чат]

---

## 🚀 Основной функционал

* **Прием вебхуков:** Обработка входящих JSON-запросов с информацией об отправителе (`sender`) и содержимом (`message`).
* **Аудит и логирование:** Сохранение полной истории полученных событий в реляционной базе данных PostgreSQL с фиксацией времени получения.
* **Умная маршрутизация сообщений:** 
  * Стандартные события доставляются в штатном режиме.
  * Критические/тревожные события обрабатываются по специальному сценарию нотификаций.
* **TelegramBots SDK 8.x:** Интеграция с официальной библиотекой `TelegramBots` на базе `OkHttp` клиента и Long Polling механизма.
* **OpenAPI / Swagger:** Интерактивное тестирование всех эндпоинтов прямо из браузера.

---

## 🛠 Технологический стек

* **Язык:** Java 25
* **Фреймворк:** Spring Boot 4.1.1 (Spring WebMVC, Spring Data JPA, Spring Validation)
* **Интеграция:** TelegramBots Spring Boot Starter 8.0.0 (`telegrambots-client`, `telegrambots-springboot-longpolling-starter`)
* **База данных:** PostgreSQL
* **Документация:** Springdoc OpenAPI UI 3.1.0 (Swagger)
* **Сборщик проекта:** Maven

---

## 📡 Спецификация API

| Метод | Эндпоинт | Описание | Пример тела запроса / ответа |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/events` | Принять и обработать входящий вебхук | `{"sender": "payment-gateway", "message": "Оплата заказа #1042 прошла успешно"}` |
| `GET` | `/api/v1/events` | Получить список всех залогированных событий | Список объектов `WebHookEvent` с id, отправителем, сообщением и датой |

---

## ⚙️ Локальный запуск

### 1. Требования
* JDK 25
* PostgreSQL (порт по умолчанию: `5434`, база: `webhookerdb`)
* Токен Telegram-бота (полученный у [@BotFather](https://t.me/BotFather))

### 2. Конфигурация (`application.properties`)
Создайте или отредактируйте файл `src/main/resources/application.properties`:

```properties
# Подключение к базе данных
spring.datasource.url=jdbc:postgresql://localhost:5434/webhookerdb
spring.datasource.username=postgres
spring.datasource.password=your_db_password

# Настройки Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Настройки Telegram бота
bot.token=YOUR_TELEGRAM_BOT_TOKEN
mytgId=YOUR_TELEGRAM_CHAT_ID
```

### 3. Сборка и старт
```bash
# Сборка проекта
./mvnw clean package
# Запуск приложения
./mvnw spring-boot:run
```
После старта документация Swagger доступна по адресу:  
👉 `http://localhost:8080/swagger-ui/index.html`
