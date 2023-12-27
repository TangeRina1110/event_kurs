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
<div class="block1"><center><h1>MIREAevents</h1><hr></center></div>

<div class="block2">
    <h4>Подробная информация о Мероприятии</h4>
    <#list images as img>
    <img src="/images/${img.id}" height="175px"/><br><br>
</#list>
</div>

<div class="block3">
    <b>Название мероприятия: </b>${event.title}<br>
    <b>Описание мероприятия: </b>${event.description}<br>
    <b>Количество участников: </b>${event.price}<br>
    <b>Место проведения: </b>${event.city}<br>
    <b>Автор: </b>${event.user.name}<br>
</div>

</body>
</html>