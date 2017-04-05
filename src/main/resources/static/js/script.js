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
            scrollY: 200,
            scrollCollapse: true,
            scroller: true
        })

    $('#cocktailsZutaten')
        .change(function () {
            $('#cocktailsCocktailTable').DataTable().ajax.reload();
        });

    $('#adminZutatTable')
        .DataTable({
            ajax: '/admin/data/zutaten',
            searching: false,
            paging: false,
            ordering: false,
            info: false,
            scrollY: 200,
            scrollCollapse: true,
            scroller: true
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

    var cocktail = {
        saveCocktail: function (name, zutaten, callback) {
            $.ajax({
                type: "PUT",
                url: "/admin/data/cocktail",
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
        sortSelect: function (select){
            var options = select.find("option")
            options.sort(function(a,b) {
                if (a.text > b.text) return 1;
                if (a.text < b.text) return -1;
                return 0
            });
            select.empty().append( options );
        }
    };

    $('#adminZutatSave').click(function () {
        var name = $('#adminZutatName').val();
        zutat.saveZutat(name, function (response) {
            $('#adminZutatName').val("");
            $('#adminZutatTable').DataTable().ajax.reload();
        });
    });

    $('#adminAddZutat').click(function () {
        $("#adminAllZutaten").find("> option").each(function () {
            if (this.selected === true) {
                $("#adminSelectedZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            }
        });
        cocktail.sortSelect($("#adminSelectedZutaten"));
    });

    $('#adminRemoveZutat').click(function () {
        $("#adminSelectedZutaten").find("> option").each(function () {
            if (this.selected === true) {
                $("#adminAllZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            }
        });

        cocktail.sortSelect($("#adminAllZutaten"));
    });

    $('#adminCocktailSave').click(function () {
        var name = $('#adminCocktailName').val();
        var zutaten = [];

        $('#adminSelectedZutaten').find('option').each(function () {
            zutaten.push($(this).val());
        });

        cocktail.saveCocktail(name, zutaten, function (response) {
            $('#adminCocktailName').val("");
            $("#adminSelectedZutaten").find("> option").each(function () {
                $("#adminAllZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                this.remove();
            });
            cocktail.sortSelect($("#adminAllZutaten"));
        });
    });
});