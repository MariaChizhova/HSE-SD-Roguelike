# HSE-SD-Roguelike

## Разработчики

* Чижова Мария
* Потрясаева Анна
* Тарабонда Герман
* Обрядина Александра

## Общие сведения о системе

Игра в жанре Roguelike с консольной графикой.

### Назначение
Данная игра предназначена для развлечения.

### Границы системы
* Консольная двумерная графика
* Управление производится с помощью клавиатуры
* Не поддерживается переназначение клавиш
* Игра однопользовательская в режиме оффлайн
* Есть заранее созданные уровни, нет возможности добавления своих
* Поле отображается на экране целиком
* Отсутствуют точки сохранения
* Язык - английский

### Контекст, в котором существует система
1. Кроссплатформенность. Игра должна поддерживаться в терминалах операционных систем Windows, Linux, MacOS

## Ключевые требования (architectural drivers)

### Технические ограничения:
* Должна быть предусмотрена библиотека, позволяющая работать с консолью (например, ncurses)

### Бизнес-ограничения:
* Время разработки ограничено двумя месяцами
* Используются лишь бесплатные библиотеки, поскольку нет бюджета на использование платных

### Качественные характеристики системы:
* Игра должна быть гибкой и расширяемой, чтобы было легко добавить новую функциональность или модифицировать уже имеющуюся
* Игра должна содержать подробную и актуальную документацию, а также описание ко всем публичным методам
* Производительность не столь важна, поскольку игра с консольной графикой.
  Для реализации данной игры мы выбрали библиотеку lanterna. По сравнению с остальными библиотеками она кроссплатформенная,
  а также поддерживает вывод цветов, что улучшило бы восприятие игры.
* Безопасность также не столь важна, поскольку не предполагается взаимодействие с сетью и другими пользователями

### Ключевые функциональные требования:
1. Управление игроком производится с помощью клавиатуры
2. Консольная графика
3. Возможность загрузки карты из файла
4. Должен быть персонаж, перемещающийся по полю
5. Должны быть объекты, с которыми взаимодействует персонаж

## Architectural views

### Роли
Игрок - персонаж, управляемый человеком.
Программист, который улучшает и дорабатывает игру

### Диаграмма случаев использования

![](https://github.com/MariaChizhova/HSE-SD-Roguelike/blob/task-2-impl/img/Usage.png)

### Типичный пользователь
Имя: Василий Пупкин
Возраст: 15 лет
Род деятельности: школьник

### Игрок
Каждый игрок имеет определенные характеристики, которые представляются в виде текущее/максимальное:
* Здоровье - когда здоровье становится нулевым, игрок умирает
* Урон - нападающая сила
* Броня - уменьшение получаемого урона
* Опыт - очки для получения нового уровня, получаемые при убийствах

### Поле
Поле генерируется случайно. На нем представлены определенные сущности:
* Свободная клетка - ничего не произойдет при нахождении на ней
* Стена - через стену нельзя пройти
* Игрок на определенной позиции
* Враги на определенных позициях
* Предметы - повышают характеристики игрока
* Для каждой клетки поля есть набор определенных возможных значений (Стена, пустое поле, артефакт, враг и тд (может дополняться) - они являются взаимоисключающими).
* Каждое такое значение также является сущностью и для отображения имеет свой символ (или его отсутствие) и цвет


### Враг
Каждый враг имеет аналогично игроку: здоровье, урон, броню, опыт(за его убийство). Также имеют стратегию перемещения по полю - куда двигаться в зависимости от нахождении на поле и самому полю. Стратегий может быть много и они могут добавлятся.

## Композиция (диаграмма компонентов)

![](https://github.com/MariaChizhova/HSE-SD-Roguelike/blob/task-2-impl/img/Comp.png)

Можно выделить 4 основные компоненты:

* User, которая включает в себя пользовательский ввод, управление или иное взаимодействие с игрой
* Controller, обрабатывающий информацию, полученную от пользователя и изменяющий модели
* Model, в которой зашита логика игры, а именно модели и их взаимодействие
* View, которая отвечает за отображение состояний модели для пользователя. Также ее состояние может зависеть от входного файла с картой, если таковой имеется.

## Логическая структура (диаграмма классов)

![](https://github.com/MariaChizhova/HSE-SD-Roguelike/blob/task-2-impl/img/Logic.png)

### Main
Данный класс отвечает за запуск логики всего процесса. Запускает GameController.

### GameController
Часть Controller. Данный класс отвечает за выбор режима, в котором будет играть игрок, а также за выбор выхода из игры/раунда. Если игрок выберет “начать игру”, то данный класс вызовет Round.

### InputHandler
Часть Controller. Данный класс обрабатывает ввод пользователя и передает нужные команды, находящимся в компоненте View.

### Generation
Часть Model. Генерирует раунд игры.

### GenerationResult
Часть Model. Результат работы Generation. Хранит в себе информацию о сгенерированной клетке.

### Round
Часть Model. Данный класс отвечает за окружающую среду в раунде. Регулирует взаимодействие между игроками и полем.

### Field
Часть Model. Хранит информацию о поле: какие объекты находятся в сетке поля.

### Character
Часть Model. Интерфейс, описывающий основные характеристики и действия всех игроков.

### Position
Часть Model. Класс, который хранит информацию о позиции на поле.

### Cell
Часть Model. Интерфейс, хранящий в себе информацию о клетке на поле.

### EmptyCell
Часть Model. Класс, реализующий интерфейс Cell. Отвечает за пустую клетку.

### Wall
Часть Model. Класс, реализующий интерфейс Cell. Отвечает за стенку в игре.

### Player
Часть Model. Класс, который отвечает за характеристики и действия игрока, который контролируется пользователем. Реализует интерфейс Character и Cell.

### Enemy
Часть Model. Класс, который отвечает за характеристики и действия врага. Реализует интерфейс Character и Cell.

### StrategyEnemy
Часть Model. Интерфейс, отвечающий за стратегию врага в игре.

### SimpleStrategy
Часть Model. Класс, реалтзующий интерфейс StrategyEnemy.

### FoodWithPosition
Часть Model. Класс, который содержит информацию о позиции еды на поле
и информацию о самой еде (сколько здоровья она восстанавливает).

### Food
Часть Model. Класс, который содержит информацию о самой еде (сколько здоровья она восстанавливает).

### ArtifactWithPosition
Часть Model. Класс, который содержит информацию о позиции артефакта на поле
и информацию о самом артефакте.

### Artifact
Часть Model. Класс, который содержит информацию о самом артефакте: что за артефакт,
какие преимущества он дает игроку.

### ConsoleDrawer
Часть View. Класс, который отвечает за отрисовку поля и меню в консоле.

### GameSaverLoader
Часть Controller. Класс, который отвечает за сохранение и восстановление игры.

## Диаграмма последовательностей

![](https://github.com/MariaChizhova/HSE-SD-Roguelike/blob/task-2-impl/img/Subseq.png)

## Диаграмма состояний

![](https://github.com/MariaChizhova/HSE-SD-Roguelike/blob/task-2-impl/img/States.png)

Начинается приложение в главном меню, в котором присутствуют следующие пункты:
* Новая игра (поле генерируется случайно)
* Новая игра из файла
* Выйти из игры
Из состояния - игровой процесс можно перейти в меню игры, нажав Escape.
В меню игры можно посмотреть характеристики игры и либо вернуться в игру (Continue), либо выйти из игры в главное меню, при этом можно сохранить поле текущей игры.

