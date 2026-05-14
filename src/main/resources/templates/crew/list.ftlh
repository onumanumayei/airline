<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Екіпаж</title>
</head>

<body>
    <h1>Члени екіпажу</h1>
    <a href="/crew/new">+ Додати</a> | <a href="/flights">Рейси</a>
    <table border="1">
        <tr>
            <th>Ім'я</th>
            <th>Роль</th>
            <th>Доступний</th>
            <th>Дія</th>
        </tr>
        <#list crew as m>
            <tr>
                <td>${m.fullName}</td>
                <td>${m.role}</td>
                <td>${m.available?then('Так','Ні')}</td>
                <td>
                    <form method="post" action="/crew/${m.id}/delete">
                        <button type="submit">Видалити</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</body>

</html>