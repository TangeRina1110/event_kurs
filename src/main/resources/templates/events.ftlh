<!DOCTYPE html>
<html>
<head>
    <title>MIREAevents</title>
    <style>
        .block1{
            background-color:#ADD8E6;
        }
        .block2{
            background-color:#C0C0C0;
        }
        .block3{
            background-color:#C0C0C0;
        }
    </style>
</head>
<body>
<div class="block1">
    <center><h1>MIREAevents</h1><hr></center>
    <#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#else>
    <a align="right" href="/login">Войти</a></h1>
</#if>
<hr>
</div>

<div class="block2">
    <center><h4>Мероприятия университета</h4></center>
    <center><form action="/events" method="get"></center>
    <center>Поиск по названию объявления: <input type="text" name="title"/> <input type="submit" value="Поиск"/></center>
    </form>
    <center><#list events as event>
        <div>
            <p><b>${event.title}</b> ${event.price} чел. | <a href="/event-info?id=${event.id}">Подробнее...</a></p>
        </div>
        <#else>
        <h3>Мероприятий нет</h3>
    </#list></center>
    <#if user.email??>
    <hr>
</div>

<div class="block3">
    <h3>Создать новое Мероприятие</h3></center>
    <form action="/events/create" method="post" enctype="multipart/form-data">
        Название мероприятия: <input type="text" name="title"/><br><br></center>
        Описание мероприятия: <input type="text" name="description"/><br><br>
        Количество человек: <input type="number" name="price"/><br><br>
        Место проведения: <input type="text" name="city"/><br><br>
        Первая фотография: <input type="file" name="file1"/><br><br>
        Вторая фотография: <input type="file" name="file2"/><br><br>
        Третья фотография: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить мероприятие"/>
    </form>
</#if>
</div>
</body>
</html>