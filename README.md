# TestSberbank
Testing of http://www.sberbank.ru/ru/quotes/converter with Selenium WebDriver

Тестовые данные параметризированы через файлы: convertation_test_data.csv, dinamic_changes_test_data.csv, extended_table_test_data.csv

Проект содержит три тестовых сценария, каждый из которых написан как отдельный тест JUnit. 

Для запуска тестов, формирования и просмотра Allure отчетов необходимо в командной строке набрать: mvn clean test site jetty:run

Просмотр отчетов после выполненой команды доступен для просмотра двумя способами:
1. B браузере по адресу http://localhost:8080/ 
2. ScreenShots по ссылке https://goo.gl/kEW3Ax


