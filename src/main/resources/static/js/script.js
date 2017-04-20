$(document).ready(function () {
    $('#loginLink').click(function(e) {
        $('#loginDiv').fadeIn('slow');
        e.preventDefault();
    });

    $('#logoutLink').click(function(e) {
        $('#logoutForm').submit();
        e.preventDefault();
    });

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
            if(response.success){
                $("#errorDiv").text("");
                $("#errorDiv").fadeOut();
                $('#zutatZutatName').val("");
                $('#zutatZutatTable').DataTable().ajax.reload();
            } else {
                $("#errorDiv").text(response.error);
                $("#errorDiv").fadeIn("slow");

            }
        });
    });

    $("#zutatZutatName").on('keyup', function (e) {
        if (e.keyCode == 13) {
            var name = $('#zutatZutatName').val();
            zutat.saveZutat(name, function (response) {
                if(response.success){
                    $("#errorDiv").text("");
                    $("#errorDiv").fadeOut();
                    $('#zutatZutatName').val("");
                    $('#zutatZutatTable').DataTable().ajax.reload();
                } else {
                    $("#errorDiv").text(response.error);
                    $("#errorDiv").fadeIn("slow");

                }
            });
        }
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

    $('#cocktailRemoveZutat').click(function () {
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
            if(response.success){
                $("#errorDiv").text("");
                $("#errorDiv").fadeOut();
                $('#cocktailCocktailName').val("");
                $("#cocktailSelectedZutaten").find("> option").each(function () {
                    $("#cocktailAllZutaten").append('<option value="' + this.value + '" >' + this.text + '</option>');
                    this.remove();
                });
                cocktail.sortSelect($("#cocktailAllZutaten"));
                $('#cocktailCocktailTable').DataTable().ajax.reload();
            } else {
                $("#errorDiv").text(response.error);
                $("#errorDiv").fadeIn("slow");
            }
        });
    });
});

var zutat = {
    saveZutat: function (name, callback) {
        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        $.ajax({
            type: "PUT",
            url: "zutaten/data/zutat",
            contentType: 'application/json; charset=utf-8',
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            },
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
        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        $.ajax({
            type: "DELETE",
            url: "zutaten/data/zutat/" + name,
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            }
        }).done(function (response) {
            if(response.success){
                $("#errorDiv").text("");
                $("#errorDiv").fadeOut();
                $('#zutatZutatTable').DataTable().ajax.reload();
            } else {
                $("#errorDiv").text(response.error);
                $("#errorDiv").fadeIn("slow");

            }
        });
    }
};

var cocktail = {
    saveCocktail: function (name, zutaten, callback) {
        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        $.ajax({
            type: "PUT",
            url: "cocktails/data/cocktail",
            contentType: 'application/json; charset=utf-8',
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            },
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
    removeCocktail: function (name) {
        var header = $("meta[name='_csrf_header']").attr("content");
        var token = $("meta[name='_csrf']").attr("content");
        $.ajax({
            type: "DELETE",
            url: "cocktails/data/cocktail/" + name,
            beforeSend: function(request) {
                request.setRequestHeader(header, token);
            }
        }).done(function (response) {
            if(response.success){
                $("#errorDiv").text("");
                $("#errorDiv").fadeOut();
                $('#cocktailCocktailTable').DataTable().ajax.reload();
            } else {
                $("#errorDiv").text(response.error);
                $("#errorDiv").fadeIn("slow");

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
    }
};