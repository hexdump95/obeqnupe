<!DOCTYPE html>
<html lang="en" data-bs-theme="dark">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><#if title??>${title}<#else>obeqnupe</#if></title>
        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="/css/styles.css">
        <#if stylesheets??>
            ${stylesheets}
        </#if>
    </head>
    <body>
    <nav class="navbar fixed-top navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <p>obeqnupe</p>
        </div>
    </nav>
    <#if body??>
        ${body}
    </#if>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/jquery-3.7.1.js"></script>
    <#if javascripts??>
        ${javascripts}
    </#if>
    </body>
</html>
