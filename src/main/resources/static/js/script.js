$(document).ready(function () {
    $('#cocktailTable')
        .on('preXhr.dt', function (e, settings, data) {
            data.zutaten = $('#zutaten').val();
        })
        .DataTable({
            ajax: '/data/cocktails',
            searching: false,
            paging: false,
            ordering: false,
            info: false
        })

    $('#zutaten')
        .change(function () {
            $('#cocktailTable').DataTable().ajax.reload();
        });
});