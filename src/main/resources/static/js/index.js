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
            locationId: $('#location-id').val(),
            benefitIds: benefitIds,
            query: $('#query').val(),
        },
        success: (res) => {
            let html = '';
            if (res && res.items.length > 0) {
                $.each(res.items, (_, company) => {
                    html += '<tr>'
                    html += '<td><a href="#" onclick="getCompany(\'' + company.id + '\'); return false;">' + company.name + '</a></td>'
                    html += '<td><a href="' + company.page + '">Visit site!</a></td>'
                    html += '</tr>'
                });
                $("#companies").html(html);
                $('#page-number').text(`Page ${currentPage} of ${res.totalPages}`);

                const prevPageId = $('#prev-page');
                if (!res.hasPreviousPage) {
                    prevPageId.addClass('disabled');
                } else {
                    if (prevPageId.hasClass('disabled'))
                        prevPageId.removeClass('disabled');
                }

                const nextPageId = $('#next-page');
                if (!res.hasNextPage) {
                    nextPageId.addClass('disabled');
                } else {
                    if (nextPageId.hasClass('disabled'))
                        nextPageId.removeClass('disabled');
                }
            }
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
    $.ajax({
        type: 'GET',
        url: '/api/v1/companies/' + id,
        dataType: 'json',
        success: function (res) {
            $("#company-name").html(res.name);

            let html = '';
            html += '<a href="' + res.page + '">Visit site</a>'
            html += '<p>Location: ' + res.locationName + '</p>'
            if (res.benefits.length > 0)
                html += '<p>Benefits: ' + res.benefits.join(', ') + '.' + '</p>'
            if (res.skills.length > 0)
                html += '<p>Skills: ' + res.skills.join(', ') + '.' + '</p>'
            $('#company-detail').html(html);

            $('#my-modal').modal({backdrop: 'static'}).modal('show');
        }
    });
}

function closeModal() {
    document.activeElement.blur();
    $('#my-modal').modal('hide');
}

function search(text) {
}
