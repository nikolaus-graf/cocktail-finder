$(document).ready(function () {
    $('#cocktailsCocktailTable')
        .on('preXhr.dt', function (e, settings, data) {
            data.zutaten = $('#cocktailsZutaten').val();
        })
        .DataTable({
            ajax: '/data/cocktails',
            searching: false,
            paging: false,
            ordering: false,
            info: false
        })

    $('#cocktailsZutaten')
        .change(function () {
            $('#cocktailsCocktailTable').DataTable().ajax.reload();
        });
});