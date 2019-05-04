#language: ru

Функционал: Страхование
  Сценарий: Отправка заявки на страхование путешественников без персональных данных

Дано выполнен переход на главную страницу
  Когда выбирается пункт верхнего меню:"Страхование"
    И выбирается вид страхования:"Путешествия и покупки"
  Тогда на странице отображается заголовок "Страхование путешественников"
    И выполнен скриншот текущего окна
    И выполнено нажатие на кнопку Оформить Онлайн и переключение к новому открывшемуся окну
  Когда выбирается сумма страховой защиты – Минимальная
    И выполнено нажатие на кнопку - Оформить
    И заполняются поля:
  |Фамилия застрахованного|Petrov|
  |Имя застрахованного|Ivan|
  |Дата рождения застрахованного|05.11.1993|
  |Фамилия|Смирнова|
  |Имя|Елена|
  |Отчество|Борисовна|
  |Дата рождения|05.05.1997|
  |Серия паспорта|1234|
  |Номер паспорта|567890|
  |Дата выдачи|26.05.2017|
  |Кем выдан|ОВД Центрального р-на|

    И выбирается Пол - Женский
    И выполнен скриншот текущего окна
  Тогда проверяются поля:
    |Фамилия застрахованного|Petrov|
    |Имя застрахованного|Ivan|
    |Дата рождения застрахованного|05.11.1993|
    |Фамилия|Смирнова|
    |Имя|Елена|
    |Отчество|Борисовна|
    |Дата рождения|05.05.1997|
    |Серия паспорта|1234|
    |Номер паспорта|567890|
    |Дата выдачи|26.05.2017|
    |Кем выдан|ОВД Центрального р-на|
    И проверяется, что в поле Пол выбрано - Женский
  Когда выполнено нажатие на кнопку - Продолжить
  Тогда на странице отображается сообщение об ошибке "Заполнены не все обязательные поля"
    И выполнен скриншот текущего окна


