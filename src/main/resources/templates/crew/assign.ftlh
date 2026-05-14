<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Призначення екіпажу</title>
</head>

<body>
    <h1>Екіпаж на рейс ${flight.flightNumber}</h1>
    <p>${flight.departureCity} → ${flight.arrivalCity}</p>

    <h2>Поточний екіпаж</h2>
    <#if assigned?has_content>
        <table border="1">
            <tr>
                <th>Ім'я</th>
                <th>Роль</th>
                <th>Дія</th>
            </tr>
            <#list assigned as a>
                <tr>
                    <td>${a.crewMember.fullName}</td>
                    <td>${a.crewMember.role}</td>
                    <td>
                        <form method="post" action="/crew/unassign/${a.id}?flightId=${flight.id}">
                            <button type="submit">Зняти</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </table>
        <#else>
            <p>Екіпаж ще не призначено.</p>
    </#if>

    <h2>Додати члена екіпажу</h2>
    <form method="post" action="/crew/assign">
        <input type="hidden" name="flightId" value="${flight.id}">
        <select name="crewId">
            <#list crew as m>
                <option value="${m.id}">${m.fullName} (${m.role})</option>
            </#list>
        </select>
        <button type="submit">Призначити</button>
    </form>
    <a href="/flights">Назад до рейсів</a>
</body>

</html>