$(document).ready(function () {
    $('#homeCocktailTable')
        .on('preXhr.dt', function (e, settings, data) {
            data.zutaten = $('#cocktailsZutaten').val();
        })
        .DataTable({
            ajax: '/data/cocktails',
            searching: false,
            paging: false,
            ordering: false,
            info: false,
            scrollY: 200,
            scrollCollapse: true,
            scroller: true
        })

    $('#cocktailCocktailTable')
        .DataTable({
            ajax: '/data/cocktails',
            searching: false,
            paging: false,
            ordering: false,
            info: false,
            scrollY: 200,
            scrollCollapse: true,
            scroller: true,
            columnDefs: [{
                targets: 2,
                render: function (data, type, row, meta) {
                    return '<a href="#" onclick="cocktail.removeCocktail(\'' + row[0] + '\');return false;"><span class="glyphicon glyphicon-remove"></span></a>';
                }
            }]
        })

    $('#cocktailsZutaten')
        .change(function () {
            $('#homeCocktailTable').DataTable().ajax.reload();
        });

    $('#zutatZutatTable')
        .DataTable({
            ajax: '/data/zutaten',
            searching: false,
            paging: false,
            ordering: false,
            info: false,
            scrollY: 200,
            scrollCollapse: true,
            scroller: true,
            columnDefs: [{
                targets: 1,
                render: function (data, type, row, meta) {
                    if(row[1] == 'false') {
                        return '<a href="#" onclick="zutat.removeZutat(\'' + row[0] + '\');return false;"><span class="glyphicon glyphicon-remove"></span></a>';
                    }
                    return '';
                }
            }]
        });

    $('#zutatZutatSave').click(function () {
        var name = $('#zutatZutatName').val();
        zutat.saveZutat(name, function (response) {
            $('#zutatZutatName').val("");
            $('#zutatZutatTable').DataTable().ajax.reload();
        });
    });

    $('#cocktailAddZutat').click(function () {
        $("#cocktailAllZutaten").find("> option").each(function () {
            if (this.selected === true) {
                $("#cocktailSelectedZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            }
        });
        cocktail.sortSelect($("#cocktailSelectedZutaten"));
    });

    $('#zutatRemoveZutat').click(function () {
        $("#cocktailSelectedZutaten").find("> option").each(function () {
            if (this.selected === true) {
                $("#cocktailAllZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            }
        });

        cocktail.sortSelect($("#cocktailAllZutaten"));
    });

    $('#cocktailCocktailSave').click(function () {
        var name = $('#cocktailCocktailName').val();
        var zutaten = [];

        $('#cocktailSelectedZutaten').find('option').each(function () {
            zutaten.push($(this).val());
        });

        cocktail.saveCocktail(name, zutaten, function (response) {
            $('#cocktailCocktailName').val("");
            $("#cocktailSelectedZutaten").find("> option").each(function () {
                $("#cocktailAllZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            });
            cocktail.sortSelect($("#cocktailAllZutaten"));
            $('#cocktailCocktailTable').DataTable().ajax.reload();
        });
    });
});

var zutat = {
    saveZutat: function (name, callback) {
        $.ajax({
            type: "PUT",
            url: "/data/zutat",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                "name": name
            })
        }).done(function (response) {
            if (typeof callback === "function") {
                callback(response);
            }
        });
    },
    removeZutat: function (name) {
        $.ajax({
            type: "DELETE",
            url: "/data/zutat/" + name
        }).done(function (response) {
            $('#zutatZutatTable').DataTable().ajax.reload();
        });
    }
};

var cocktail = {
    saveCocktail: function (name, zutaten, callback) {
        $.ajax({
            type: "PUT",
            url: "/data/cocktail",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                "name": name,
                "zutaten": zutaten
            })
        }).done(function (response) {
            if (typeof callback === "function") {
                callback(response);
            }
        });
    },
    sortSelect: function (select) {
        var options = select.find("option")
        options.sort(function (a, b) {
            if (a.text > b.text) return 1;
            if (a.text < b.text) return -1;
            return 0
        });
        select.empty().append(options);
    },
    removeCocktail: function (name) {
        $.ajax({
            type: "DELETE",
            url: "/data/cocktail/" + name
        }).done(function (response) {
            $('#cocktailCocktailTable').DataTable().ajax.reload();
        });
    }
};