<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Новий член екіпажу</title>
</head>

<body>
    <h1>Додати члена екіпажу</h1>
    <form method="post" action="/crew">
        <label>Ім'я: <input type="text" name="fullName" /></label><br />
        <label>Роль:
            <select name="role">
                <#list roles as r>
                    <option value="${r}">${r}</option>
                </#list>
            </select>
        </label><br />
        <label>Ліцензія: <input type="text" name="licenseNumber" /></label><br />
        <button type="submit">Зберегти</button>
        <a href="/crew">Скасувати</a>
    </form>
</body>

</html>