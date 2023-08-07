# HackAnton2023

<p align="center">
    <img src="URL">
</p>

<h1 align="center">
BI.ZONE-CVE-Crawler - Telegram Bot
</h1>

<p align="justify">&emsp;&emsp;Данный репозиторий включает в себя реализацию кейса по разработке Telegram бота, который позволяет отсеживать в гибкой форме изменения базы CVE от компании BI.ZONE с хакатона "киб_хак" (11.05.2023 - 18.05.2023).</p>

<h2 align="center">
    Описание задачи от BI.ZONE: Разработка CVE Crawler Telegram Bot
</h2>

<h3 align="center">
    Вводная часть
</h3> 

<p align="justify">&emsp;&emsp;Специалистам, работающим в области кибербезопасности, необходимо всегда быть в курсе самых актуальных киберугроз, чтобы своевременно противостоять им. На текущий момент существует большее количество источников информации об уязвимостях и новых кибератаках - новостные веб-сайты, форумы, Telegram каналы, Jabber каналы и т.д. Однако, далеко не вся информация с источников бывает актуальной и применимой к конкретной компании. Для того чтобы найти актуальную для себя информацию, приходится тратить слишком много времени вычитывая различные ресурсы.</p>

<p align="justify">&emsp;&emsp;Существует множество решений, которые автоматизируют процесс поиска, например: https://t.me/pocfather_bot, https://nvd.nist.gov, https://github.com/carlospolop/BotPEASS/ или https://cvetrends.com с открытым API. Однако, такие решения показывают малое количество информации и не имеют возможности расширенной конфигурации отображаемых параметров.</p>

<p align="justify">&emsp;&emsp;У нас в компании информацию по новым уязвимостям хотят получать разные подразделения, в частности:</p>

&emsp;&emsp;:hash: Аналитики центра мониторинга киберугроз (SOC), чтобы своевременно уведомлять Клиентов о потенциальных уязвимостях и просить "пропатчиться";

&emsp;&emsp;:hash: Аналитики Web Application Firewall, чтобы своевременно разработать правила детектирования и реагирования;

&emsp;&emsp;:hash: Специалисты по тестированию (Pentest), для понимания новых возможных векторов кибератак;

&emsp;&emsp;:hash: Разработчики продуктов и сервисов кибербезопасности, для оптимизации механизмов противодействия киберугрозам.


<h3 align="center">
    Требования к разрабатываемому программному обеспечению
</h3> 

<p align="justify">&emsp;&emsp;:large_blue_circle: Программное решение должно быть реализовано в виде Telegram бота;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: На программное решение должна быть разработана минимально необходимая документация, описывающая архитектуру, принципы использования и конфигурации программного обеспечения;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: В программном решении не должно быть "захардкожено" каких-либо параметров/секретов/ключей, которые не позволят переиспользовать его;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Программное решение должно иметь функционал поиска CVE по номеру с выводом информации по найденой уязвимости;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Должен быть реализован функционал отображения новых CVE за выбранный период времени 1 день/неделю/месяц (в идеале гибкий настраиваемый период) и возможностью преднастроить критерии вывода по максимальному количеству критериев (уровень критичности, оценка CVSS, наличие PoC, версия программного обеспечения, вендор и т.д). Пример логики работы: покажи мне все CVE, которые вышли за последний месяц под nginx версии 1.18.0, с CVSS больше 6, по которым есть PoC;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Должен быть реализован функционал автоматического уведомления о новых зарегистрированных в мире CVE  по заранее заданным критериям уведомления. Пример логики работы: присылай мне уведомление в Telegram, как только обнаружишь новую CVE  под CentOS 7 с CVSS больше 6;</p>
 
<p align="justify">&emsp;&emsp;:large_blue_circle: Программное решение должно поддерживать конфигурацию отображаемых данных по любой CVE и при любом из трех выше описанных сценариев, из следующих полей (их добавление/изменение на усмотрение команды разработчиков на Хакатоне, если у изменения будет обоснованная причина):</p>

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Номер CVE (С кликабельной ссылкой на источник)

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: CVSS (2/3) рейтинг

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Уровень критичности

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Метрики CVSS (Вектор, сложность и т.д.)

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: EPSS рейтинг

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Дата/время регистрации CVE 

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Продукт/вендор для которого характерна CVE 

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Уязвимые версии продукта

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: PoC/CVE WriteUp (С кликабельными ссылками, если есть)

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Описание CVE

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Информация о количестве упоминаний о CVE в Twitter/Facebook/Telegram/Reddit/GitHub/Habr/Xaker

&emsp;&emsp;&emsp;&emsp;:small_blue_diamond: Необходимые действия по устранению уязвимости 


<h3 align="center">
    Дополнительные идеи
</h3> 

<p align="justify">&emsp;&emsp;:large_blue_circle: Любое полезное творчество и расширение функционала максимально приветствуется;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Функционал вывода списка наиболее значимых CVE за выбранный период времени (Значимость может определяться по разным параметрам: Количество PoC, высокая обсуждаемость CVE в средствах массовой информации, высокое значение рейтинга EPSS, и т.д.);</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Возможность получать описание CVE на русском языке;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Добавление возможности поиска CVE не только по конкретному номеру, но и по определенным значениям, аналогично проекту Searchsploit: https://github.com/Err0r-ICA/Searchsploit;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Функционал построения и отображения графика регистрации новых CVE за определенный промежуток времени.</p>


<h3 align="center">
    Критерии оценки
</h3>  

<p align="justify">&emsp;&emsp;:large_blue_circle: Качество реализации основного функционала - должны быть реализованы минимальные требования к разрабатываемому программному решению;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Не должно быть сбоев и ошибок в работе программного решения;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Количество и качество реализации дополнительного функционала - будет оцениваться качество и количество реализованного дополнительного функционала.<p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Качество и полнота Roadmap -  Если фича готова не до конца или полностью не реализована, ее можно описать словами и презентовать в формате "роадмапа". Будет оцениваться полезность, реальность реализации и количество таких фич. Не стоит прикручивать к велосипеду атомный реактор😉;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Грамотность программного кода/архитектуры программного решения;</p>

<p align="justify">&emsp;&emsp;:large_blue_circle: Удобство UI, качество UX;</p>
 
<p align="justify">&emsp;&emsp;:large_blue_circle: Итоговая презентация разработанного решения.</p>


<h2 align="center">
    Описание решения
</h2>

<h3 align="center">
    Авторы
</h3>

Команда - **Impulse**:
<p align="justify">&emsp;&emsp;1) Иорин Давид Андреевич (Team Leader)</p>
<p align="justify">&emsp;&emsp;2) Богданов Данила Андреевич</p>
<p align="justify">&emsp;&emsp;3) Беляев Иван Дмитриевич</p>
<p align="justify">&emsp;&emsp;4) Малахов Арсений Константинович</p>
<p align="justify">&emsp;&emsp;5) Морозов Андрей Александрович</p>