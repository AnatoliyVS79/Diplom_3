# Diplom_3

# Дипломная работа (задание 3: веб-приложение)
Необходимо протестировать веб-приложение Stellar Burgers

---

## Проект:
-- **Подключи библиотеки:** JUnit 4, RestAssured, Allure, Selenium, webdrivermanager,

-- **Версия Java:** Amazon JAVA 11

-- **Браузеры:** Yandex, Google Chrome

---

## Реализованные тесты:

-- Регистрация:
-- Успешную регистрацию.
-- Ошибку для некорректного пароля. Минимальный пароль — шесть символов.

-- Вход:

-- вход по кнопке «Войти в аккаунт» на главной,
-- вход через кнопку «Личный кабинет»,
-- вход через кнопку в форме регистрации,
-- вход через кнопку в форме восстановления пароля.

-- Переход в личный кабинет.
-- Переход по клику на «Личный кабинет».

-- Переход из личного кабинета в конструктор.
-- Переход по клику на «Конструктор» и на логотип Stellar Burgers.

-- Выход из аккаунта.
-- Выход по кнопке «Выйти» в личном кабинете.

-- Раздел «Конструктор». Проверено, что работают переходы к разделам:

-- «Булки»,
-- «Соусы»,
-- «Начинки».

-- Запуск через параметризацию.

-- Google Chrome и Yandex Browser: mvn clean test -Dbrowser=chrome ,   mvn clean test -Dbrowser=yandex
-- Для запустится веб-сервер Allure ввести команду: mvn allure:serve
