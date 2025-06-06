# ABC_Berkut - Spring Boot + Telegram Bot

Тестовое задание: REST API для приёма сообщений и отправки их в Telegram-бота

---

## Описание

Проект реализует простой сервис, где пользователь может:

- Зарегистрироваться и авторизоваться через REST API
- Сгенерировать персональный токен для привязки Telegram-бота
- Привязать Telegram-бота, отправив токен боту в Telegram
- Через API отправлять сообщения, которые автоматически дублируются в Telegram

---

## Технологии

- Java 17
- Spring Boot 3.5
- Spring Security + JWT
- PostgreSQL
- Telegram Bot API (через [org.telegram.telegrambots](https://github.com/rubenlagus/TelegramBots))
- Gradle

## Регистрация юзера

POST /api/auth/register
{
  "username": "testuser",
  "password": "123456",
  "name": "Тест"
}

## Логин

POST /api/auth/login
{
"username": "testuser",
"password": "123456"
}

## Сгенерить телеграм токен

POST /api/telegram/token
( Authorization: Bearer <ваш_JWT>)

## Привязать токен

Найдите бота в телеграме
Отправьте ему вами сгенерированный токен

## ТЕСТИМ

POST /api/messages
( Authorization: Bearer <ваш_JWT>)
{
"text": "Текст вашего сообщения"
}

## Получить историю сообщений
GET /api/messages
(заголовок Authorization: Bearer <ваш_JWT>)

