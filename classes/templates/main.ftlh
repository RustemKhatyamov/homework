<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row md-3">
        <div class="form-group col-md-6">
            <form method="get", action="/main" class="form-inline">
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по названию"/>
                <button class="btn btn-primary" type="submit">Найти</button>
            </form>
        </div>
    </div>
<#--    <a class="btn btn-primary" data-toggle="collapse" href="#test" role="button" >-->
<#--        Добавить сообщение-->
<#--    </a>-->
<#--    <div class="form-group">-->
<#--        <input type="text" class="form-control"-->
<#--               value="<#if message??>${message.name}</#if>" name="name" placeholder="Название проекта">-->
<#--        <#if nameError??>-->
<#--            <div class="invalid-feedback">-->
<#--                ${nameError}-->
<#--            </div>-->
<#--        </#if>-->
<#--    </div>-->
    <div>
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите сообщение" />
                    <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                    </#if>
                </div>
                <div class="form-group mt-3">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile"></label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>
<#--    <#include "parts/messageEdit.ftlh" />-->
    <h1>Список проектов</h1>
    <#include "parts/messageList.ftlh" />
</@c.page>