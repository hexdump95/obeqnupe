<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>obeqnupe</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h1>obeqnupe</h1>
<div class="container">
    <form id="filter-form">
        <#list filterData.skills as skill>
            <div class="badge text-bg-light" onclick="addSkill(this, ${skill.id})">${skill.name}</div>
        </#list>
        <br>
        <select class="form-select" id="location-id" name="select">
            <option value="">Select one...</option>
            <#list filterData.locations as location>
                <option value="${location.id}">${location.name}</option>
            </#list>
        </select>
        <br>
        <#list filterData.benefits as benefit>
            <div class="badge text-bg-light" onclick="addBenefit(this, ${benefit.id})">${benefit.name}</div>
        </#list>
        <br>
        <input id="query" hidden class="input-group" value="" oninput="search(value)">
        <button type="submit">Filtrar</button>
    </form>

    <table class="table table-striped table-light w-75 mx-auto">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Page</th>
        </tr>
        </thead>
        <tbody id="companies">
        </tbody>
    </table>

    <div class="modal" id="my-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"><span id="company-name"></span></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                            onclick="closeModal()"><span
                                aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="company-detail">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="closeModal()">Close
                    </button>
                </div>
            </div>
        </div>
    </div>
    <nav id="pagination">
        <ul class="pagination justify-content-center">
            <li class="page-item" id="prev-page">
                <a class="page-link" href="#" onclick="previousPage(); return false" tabindex="-1">Previous</a>
            </li>
            <li id="page-number">Page 1</li>
            <li class="page-item" id="next-page">
                <a class="page-link" href="#" onclick="nextPage(); return false;">Next</a>
            </li>
        </ul>
    </nav>
</div>
<script src="/js/bootstrap.bundle.min.js"></script>
<script src="/js/jquery-3.7.1.js"></script>
<script src="/js/index.js"></script>
</body>
</html>
