<!DOCTYPE html>
<html lang="uk">

<head>
    <meta charset="UTF-8">
    <title>Вхід</title>
</head>

<body>
    <h1>Авіакомпанія — Вхід</h1>
    <#if RequestParameters.error??>
        <p style="color:red">Невірний логін або пароль</p>
    </#if>
    <form method="post" action="/login">
        <label>Логін: <input type="text" name="username" /></label><br />
        <label>Пароль: <input type="password" name="password" /></label><br />
        <button type="submit">Увійти</button>
    </form>
</body>

</html>