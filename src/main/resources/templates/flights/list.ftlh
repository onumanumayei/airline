<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Рейси</title>
</head>

<body>
    <h1>Список рейсів</h1>
    <a href="/flights/new">+ Додати рейс</a> | <a href="/logout">Вийти</a>
    <table border="1">
        <tr>
            <th>Номер</th>
            <th>Звідки</th>
            <th>Куди</th>
            <th>Відправлення</th>
            <th>Прибуття</th>
            <th>Статус</th>
            <th>Дії</th>
        </tr>
        <#list flights as flight>
            <tr>
                <td>${flight.flightNumber}</td>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>${flight.departureTime}</td>
                <td>${flight.arrivalTime}</td>
                <td>${flight.status}</td>
                <td>
                    <a href="/flights/${flight.id}/edit">Редагувати</a>
                    <a href="/crew/assign/${flight.id}">Екіпаж</a>
                    <form method="post" action="/flights/${flight.id}/delete" style="display:inline">
                        <button type="submit">Видалити</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</body>

</html>