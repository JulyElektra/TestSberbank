# TestSberbank
Testing of http://www.sberbank.ru/ru/quotes/converter with Selenium WebDriver

Тестовые данные параметризированы через файл convertation_test_data.csv

Проект содержит три тестовых сценария, каждый из которых написан как отдельный тест JUnit. 

Тесты находятся в классе TestForSber.java

Для запуска тестов, формирования и просмотра Allure отчетов необходимо в командной строке набрать: mvn clean test site jetty:run

Просмотр отчетов после выполненой команды доступен для просмотра двумя способами:
1. B браузере по адресу http://localhost:8080/ 
2. ScreenShot по ссылке https://goo.gl/S5lNYA


