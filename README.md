# TestSberbank
Testing of http://www.sberbank.ru/ru/quotes/converter with Selenium WebDriver

Тестовые данные параметризированы через файл convertation_test_data.csv

Проект содержит три тестовых сценария, каждый из которых написан как отдельный тест JUnit

Для запуска тестов, формирования и просмотра Allure отчетов необходимо в командной строке набрать: mvn clean test site jetty:run

Просмотр отчетов после выполненой команды доступен в браузере по адресу http://localhost:8080/
