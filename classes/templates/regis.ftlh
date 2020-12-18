<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1"><h1>Добавление нового пользователя</h1></div>
    ${message?ifExists}
<@l.login "/regis" true/>
</@c.page>