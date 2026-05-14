<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Рейс</title>
</head>

<body>
    <h1>Рейс</h1>
    <#if flight.id??>
        <form method="post" action="/flights/${flight.id}">
            <#else>
                <form method="post" action="/flights">
    </#if>
    <label>Номер: <input type="text" name="flightNumber" value="${flight.flightNumber!''}" /></label><br />
    <label>Звідки: <input type="text" name="departureCity" value="${flight.departureCity!''}" /></label><br />
    <label>Куди: <input type="text" name="arrivalCity" value="${flight.arrivalCity!''}" /></label><br />
    <label>Відправлення: <input type="datetime-local" name="departureTime" /></label><br />
    <label>Прибуття: <input type="datetime-local" name="arrivalTime" /></label><br />
    <label>Статус:
        <select name="status">
            <#list statuses as s>
                <option value="${s}">${s}</option>
            </#list>
        </select>
    </label><br />
    <button type="submit">Зберегти</button>
    <a href="/flights">Скасувати</a>
    </form>
</body>

</html>