# UlearnFinalProject 
  **Итоговый проект по курсу Ulearn Java - Вариант 4**
  
  Для выполнения задач нам требуется скачать файл с данными формата csv.
![image](https://github.com/alej0n/UlearnProject/assets/129119172/987aa5b2-399d-4fde-bbaa-af70abd87f18)
  
  На основе данных в csv, создаём модель для данных. Поля данной модели являются приватными(Инкапсуляция)
![image](https://github.com/alej0n/UlearnProject/assets/129119172/90cab444-0208-414f-8ebd-7a7bfd60c255)
  Для получения данных из модели создадим get
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/f6ada6d7-d787-422c-8603-0504c8724076)

<br /><br />
  Создадим класс CSVHandler в котором опишем логику чтения и преобразование данных из csv в ArrayList<Earthquake>. Сразу же объявляем метод readToObj который будет возвращать List<Earthquake>
![image](https://github.com/alej0n/UlearnProject/assets/129119172/ed17a9c9-3d87-4fb2-808d-6b0dca059f50)

  Описываем логику метода readToObj, там же был создан в метод splitData получающий на вход строку, возвращающий String[] (элементы строки разделённые ',')
![image](https://github.com/alej0n/UlearnProject/assets/129119172/3dba840a-b886-46a4-a3cd-0279bbd49f8b)

  Описываем логику метода splitData, т.к строка из сsv файла, может содержать запятые которые не являются разделителями между строками, мы не можем использовать просто split(","). Нам надо метод где можем это исправить.
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/c5faeac2-fd7c-41cd-8cf2-76f8d99d5288)
  
<br /><br />
  Создадим класс SQLHandler который будет хранить логику операций с БД. Создаём сигнатуры методов addTable, add, executeQuery.
  addTable - будет создавать таблицу в БД, если данной таблицы не существует.
  add - будет записывать данные в БД, принимая в качестве аргумента класс Grants.
  executeQuery - будет выполнять запрос и возвращать результат в виде ResultSet
![image](https://github.com/alej0n/UlearnProject/assets/129119172/63b24c55-33d7-485d-b9b4-2a8315fb9ca4)

  Логика метода addTable, переменная createTableSqlQuery - хранит sql запрос, метод stmt.execute выполняет операцию
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/9253fea3-0062-403e-ba46-394eaeb94999)
  Логика метода add, переменная addSqlQuery - хранит sql запрос, с помощью conn.prepareStatement заполняем запрос данными и выполняем его
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/af73b3fa-5281-40b1-8d3c-537e48b4cb9c)
  Логика метода executeQuery - в stmt.executeQuery передаем аргумент
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/5c59a101-9f34-4cfe-ac77-80f8d6f0272d)
  
  В классе Main в методе main(главном выполняемом) запустим парсинг csv, создание БД, заполнения БД
![image](https://github.com/alej0n/UlearnProject/assets/129119172/5d6fb179-4a7e-4251-a1e7-c6ead9cc2187)

  Выводим данные из БД
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/47d84acd-9aff-4d5f-8a83-6df5d77fc7cb)

  
  <br /><br />
  **Задание 1 - Постройте график по среднему количеству рабочих мест для каждого фискального года**
  Создаём класс ChartCreate который будет иметь статический метод для создания и сохранения графика
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/d42b2f9c-5684-496c-a44b-524c67efdebd)
  Создадим метод Task1 который будет решать первое задание
![image](https://github.com/alej0n/UlearnProject/assets/129119172/d15365d4-f81e-4cda-a0a7-be73d19abcb9)
 **Результат:**
![chart](https://github.com/alej0n/UlearnProject/assets/129119172/d8af8298-d781-4f7c-baf7-0fd6844550fb)


<br /><br />
  **Задание 2 - Выведите в консоль средний размер гранта для бизнеса типа "Salon/Barbershop""**
  Используем ранее заполенный лист с размерами гранта для бизнеса типа "Salon/Barbershop", и с помощью Stream API находим среднее.
  ![image](https://github.com/alej0n/UlearnProject/assets/129119172/199d6e02-3519-4a43-8e0f-fb0c29b05906)

<br /><br />  
  **Задание 3 - Выведите в консоль тип бизнеса, предоставивший наибольшее количество рабочих мест, где размер гранта не превышает $55,000.00.**
  Для решения данной задачи будем использовать sql запрос (выберим строки только с amount = 55000) сделаем сортировку number_jobs от большего к меньшему с помощью order by depth DESC  который передадим в executeQuery.
![image](https://github.com/alej0n/UlearnProject/assets/129119172/34486aeb-a3b8-4587-bfb0-d554ab3dbcc1)

<br /><br />
В методе main класса Main вызываем решение всех данных нам задач
![image](https://github.com/alej0n/UlearnProject/assets/129119172/ebfd7678-19fe-480d-8988-ef9aa4046908)