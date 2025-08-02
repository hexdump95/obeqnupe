let currentPage = 1;
let skillIds = [];
let benefitIds = [];

$(document).ready(() => {
    getCompanies();
});

function addSkill(element, id) {
    const idx = skillIds.indexOf(id);
    if (idx === -1) {
        skillIds.push(id);
        element.classList.remove('text-bg-dark');
        element.classList.add('text-bg-secondary');
    } else {
        skillIds = skillIds.filter(x => x !== id);
        element.classList.remove('text-bg-secondary');
        element.classList.add('text-bg-dark');
    }
}

function addBenefit(element, id) {
    const idx = benefitIds.indexOf(id);
    if (idx === -1) {
        benefitIds.push(id);
        element.classList.remove('text-bg-dark');
        element.classList.add('text-bg-secondary');
    } else {
        benefitIds = benefitIds.filter(x => x !== id);
        element.classList.remove('text-bg-secondary');
        element.classList.add('text-bg-dark');
    }
}

function getQueryData() {
    let data = {
        page: currentPage,
        skillIds: skillIds,
        benefitIds: benefitIds
    }
    const locationId = $('#location').val();
    const query = $('#query').val();
    if (locationId)
        data.locationId = locationId;
    if (query)
        data.query = query;
    return data;
}

function getCompanies() {
    $('#spinner').removeClass('d-none').addClass('d-flex');
    $.ajax({
        type: 'GET',
        url: '/api/v1/companies',
        dataType: 'json',
        data: getQueryData(),
        success: (res) => {
            let html = '';
            const paginationId = $('#pagination');
            if (res.items.length > 0) {
                $.each(res.items, (_, company) => {
                    html += '<tr>';
                    html += '<td>' + company.name + '</td>';
                    html += '<td>' + company.companyTypeName + '</td>';
                    html += '<td>' + company.locationName + '</td>';
                    html += '<td>';
                    html += '<button type="button" class="btn btn-default" onclick="getCompany(\'' + company.id + '\');" title="View info"><i class="bi bi-eye"></i></button>';
                    html += '<a href="' + company.page + '" class="btn btn-link" title="Visit site"><i class="bi bi-link-45deg"></i></a>';
                    html += '</td>';
                    html += '</tr>';
                });
                $('#page-info').html(`Page ${currentPage} of ${res.totalPages}`);
                $('#current-page').html(currentPage);

                $('#prev-page').toggleClass('disabled', !res.hasPreviousPage);

                $('#next-page').toggleClass('disabled', !res.hasNextPage);
                if (paginationId.is(':hidden')) {
                    paginationId.show();
                }
            } else {
                html += '<tr><td colspan="4">There are no items.</td></tr>';
                paginationId.hide();
            }
            $("#companies").html(html);
            $('#spinner').removeClass('d-flex').addClass('d-none');
        }
    });
}

$('#filter-form').submit(function (event) {
    event.preventDefault();
    currentPage = 1;
    getCompanies();
});

function previousPage() {
    if (currentPage > 0) {
        currentPage--;
        getCompanies();
    }
}

function nextPage() {
    currentPage++;
    getCompanies();
}

function getCompany(id) {
    $('#spinner').removeClass('d-none').addClass('d-flex');
    $.ajax({
        type: 'GET',
        url: '/api/v1/companies/' + id,
        dataType: 'json',
        success: function (res) {
            $("#company-name").html(res.name);

            let html = '';
            html += '<a href="' + res.page + '">Visit site</a>';
            html += '<p>Location: ' + res.locationName + '</p>';
            if (res.benefits.length > 0)
                html += '<p>Benefits: ' + res.benefits.join(', ') + '.' + '</p>';
            if (res.skills.length > 0)
                html += '<p>Skills: ' + res.skills.join(', ') + '.' + '</p>';
            $('#company-detail').html(html);

            $('#my-modal').modal({backdrop: 'static'}).modal('show');
            $('#spinner').removeClass('d-flex').addClass('d-none');
        }
    });
}

function closeModal() {
    document.activeElement.blur();
    $('#my-modal').modal('hide');
}

function search(text) {
    if (text.length < 3)
        return;
    $.ajax({
        type: 'GET',
        url: '/api/v1/search?query=' + text,
        dataType: 'json',
        success: function (res) {
            console.log(res);
            let html = '<ul>';
            $.each(res, (_, x) => {
                html += '<li onclick="completeText(\'' + x + '\');">' + x + '</li>';
            });
            html += '</ul>';
            $('#result-search').html(html);
        }
    });
}

function completeText(text) {
    $('#query').val(text);
    $('#result-search').empty();
}
