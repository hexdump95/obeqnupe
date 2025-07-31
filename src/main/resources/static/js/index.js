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
        element.classList.remove('text-bg-light');
        element.classList.add('text-bg-dark');
    } else {
        skillIds = skillIds.filter(x => x !== id);
        element.classList.remove('text-bg-dark');
        element.classList.add('text-bg-light');
    }
}

function addBenefit(element, id) {
    const idx = benefitIds.indexOf(id);
    if (idx === -1) {
        benefitIds.push(id);
        element.classList.remove('text-bg-light');
        element.classList.add('text-bg-dark');
    } else {
        benefitIds = benefitIds.filter(x => x !== id);
        element.classList.remove('text-bg-dark');
        element.classList.add('text-bg-light');
    }
}

function getCompanies() {
    $.ajax({
        type: 'GET',
        url: '/api/v1/companies',
        dataType: 'json',
        data: {
            page: currentPage,
            skillIds: skillIds,
            locationId: $('#locationId').val(),
            benefitIds: benefitIds,
        },
        success: (res) => {
            let html = '';
            if (res && res.items.length > 0) {
                $.each(res.items, (_, company) => {
                    html += '<tr>'
                    html += '<th scope="row">' + company.id + '</th>'
                    html += '<td><a href="#" onclick="getCompany(' + company.id + '); return false;">' + company.name + '</a></td>'
                    html += '<td><a href="' + company.page + '">Visit site!</a></td>'
                    html += '</tr>'
                });
                $("#companies").html(html);
                $('#pageNumber').text(`Page ${currentPage} of ${res.totalPages}`);
                $('#prevPage').prop('disabled', !res.hasPreviousPage);
                $('#nextPage').prop('disabled', !res.hasNextPage);
            }
        }
    });
}

$('#filterForm').submit(function (event) {
    event.preventDefault();
    currentPage = 1;
    getCompanies();
});

$('#prevPage').click(function () {
    if (currentPage > 0) {
        currentPage--;
        getCompanies();
    }
});

$('#nextPage').click(function () {
    currentPage++;
    getCompanies();
});

function getCompany(id) {
    $.ajax({
        type: 'GET',
        url: '/api/v1/companies/' + id,
        dataType: 'json',
        success: function (res) {
            $("#company-name").html(res.name);
            $("#company-link").html(`<a href="` + res.page + `">Visit site</a>`);
            $("#company-location").html(res.locationName);
            $("#company-benefits").html(res.benefits.join(', ') + '.');
            $("#company-skills").html(res.skills.join(', ') + '.');

            $('#myModal').modal({backdrop: 'static'}).modal('show');
        }
    });
}

function closeModal() {
    document.activeElement.blur();
    $('#myModal').modal('hide');
}
