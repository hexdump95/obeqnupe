<html lang="en" data-bs-theme="dark">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>obeqnupe</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h1>obeqnupe</h1>
<div class="container">

    <form class="border border-1 rounded-1 p-4" id="filter-form">

        <div class="accordion accordion-flush" id="accordionFlushSkills">
            <div class="accordion-item border border-1 rounded-1">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed rounded-1" type="button" data-bs-toggle="collapse"
                            data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                        Skills
                    </button>
                </h2>
                <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushSkills">
                    <div class="accordion-body">
                        <#list filterData.skills as skill>
                            <div class="badge text-bg-dark badge-button"
                                 onclick="addSkill(this, ${skill.id})">${skill.name}</div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="accordion accordion-flush accordion-border" id="accordionFlushBenefits">
            <div class="accordion-item border border-1 rounded-1">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed rounded-1" type="button" data-bs-toggle="collapse"
                            data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                        Benefits
                    </button>
                </h2>
                <div id="flush-collapseTwo" class="accordion-collapse collapse"
                     data-bs-parent="#accordionFlushBenefits">
                    <div class="accordion-body">
                        <#list filterData.benefits as benefit>
                            <div class="badge text-bg-dark badge-button"
                                 onclick="addBenefit(this, ${benefit.id})">${benefit.name}</div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="form-floating">
            <select class="form-select" id="location">
                <option value=""></option>
                <#list filterData.locations as location>
                    <option value="${location.id}">${location.name}</option>
                </#list>
            </select>
            <label for="location">Location</label>
        </div>
        <br>
        <div hidden class="input-group form-floating">
            <input id="query" class="form-control" value="" oninput="search(value)">
            <label for="query">Name</label>
        </div>
        <div id="result-search"></div>
        <br>
        <button class="btn btn-primary" type="submit">Show results</button>
    </form>

    <table class="table table-striped table-dark mx-auto">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Type</th>
            <th scope="col">Location</th>
            <th scope="col">Actions</th>
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
        <p class="text-center" id="page-info">Page 1</p>
        <ul class="pagination justify-content-center">
            <li class="page-item" id="prev-page">
                <a class="page-link" href="#" onclick="previousPage(); return false" tabindex="-1">Previous</a>
            </li>
            <li class="page-item active">
                <a class="page-link" aria-current="page" id="current-page">1</a>
            </li>
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
