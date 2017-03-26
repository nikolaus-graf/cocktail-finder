$(document).ready(function () {
    $('#cocktailsCocktailTable')
        .on('preXhr.dt', function (e, settings, data) {
            data.zutaten = $('#cocktailsZutaten').val();
        })
        .DataTable({
            ajax: '/cocktail/data/cocktails',
            searching: true,
            paging: false,
            ordering: false,
            info: false,
            scrollY:        200,
            scrollCollapse: true,
            scroller:       true
        })

    $('#cocktailsZutaten')
        .change(function () {
            $('#cocktailsCocktailTable').DataTable().ajax.reload();
        });

    $('#adminZutatTable')
        .DataTable({
            ajax: '/admin/data/zutaten',
            searching: true,
            paging: false,
            ordering: false,
            info: false,
            scrollY:        200,
            scrollCollapse: true,
            scroller:       true
        })

    var zutat = {
        saveZutat: function (name, callback) {
            $.ajax({
                type: "PUT",
                url: "/admin/data/zutat",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({
                    "name": name
                })
            }).done(function (response) {
                if (typeof callback === "function") {
                    callback(response);
                }
            });
        }
    };

    $('#adminZutatSave').click(function () {
        var name = $('#adminZutatName').val();
        zutat.saveZutat(name, function (response) {
            $('#adminZutatName').val("");
            $('#adminZutatTable').DataTable().ajax.reload();
        });
    });
});