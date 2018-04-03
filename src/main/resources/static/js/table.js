jQuery.fn.karpo_table = function (params) {

    var tableContainer = $(this[0]);
    var countTr = 7;
    if (params.countTr) {
        countTr = params.countTr;
    }
    var countPagesVisibleNearCurrent = 2;
    var currentPage = 1;
    var countTablePages;
    var dataAutocomplete = {'': null};
    var typedKeywords = [];
    var ajaxParametersForTable = {
        orderBy: 1,
        desc: true,
        keywords: function () {
            return typedKeywords.join("\n");
        },
        rowLimit: countTr,
        rowOffset: 0
    };

    tableContainer.prepend('<div class="found-length-wrapper hide-on-small-only"><span>Found:</span><span class="found-length"></span></div> ')
    tableContainer.prepend('<div class="input-field col s12 m5 l6 field-search"><i class="material-icons prefix">search</i><div class="chips chips-search"></div></div>')
    tableContainer.prepend("<div class='input-field col s1 input-number-row hide-on-small-only'><select class='number-row'><option value='3'>3</option><option value='" + countTr + "' selected>" + countTr + "</option><option value='20'>20</option><option value='100'>100</option><option value='9999999'>all</option></select></div>")
    tableContainer.find(".table-wrapper").append('<div class="message">Unfortunately, your request not found</div><div class="preloader-wrapper big active "><div class="spinner-layer spinner-blue-only"><div class="circle-clipper left"><div class="circle"></div></div><div class="gap-patch"><div class="circle"></div></div><div class="circle-clipper right"><div class="circle"></div></div></div></div>')
    tableContainer.append('<ul class="pagination col"><li class="page-left"><a href="#!" class="a-dummy"><i class="material-icons">chevron_left</i></a></li><li class="page-right"><a href="#!" class="a-dummy"><i class="material-icons">chevron_right</i></a></li></ul><div class="footer col"></div>')

    tableContainer.find(".number-row").material_select();
    tableContainer.find("select.number-row").on("change", function (e) {
        countTr = parseInt($(this).val());
    });

    tableContainer.find('.chips-search').material_chip({
        placeholder: 'Type and enter',
        secondaryPlaceholder: 'Search',
        autocompleteOptions: {
            data: dataAutocomplete,
            limit: Infinity,
            minLength: 1
        }
    });

    tableContainer.find('.dropdown-button').dropdown({
        belowOrigin: true,
        alignment: 'left'
    });

    tableContainer.find('.chips-search').on('chip.add', function (e, chip) {
        typedKeywords.push(chip.tag);
        currentPage = 1;
        downloadTable();
    });

    tableContainer.find('.chips-search').on('chip.delete', function (e, chip) {
        var idx = typedKeywords.indexOf(chip.tag)
        typedKeywords.splice(idx, 1);
        currentPage = 1;
        downloadTable();
    });

    tableContainer.find(".chips-search input").on("input", function (event) {
        var typedText = tableContainer.find(".chips-search input").val();
        $.get(params.urlSearch, {pattern: typedText}, function (autocompleteObjects) {
            for (key in dataAutocomplete) {
                delete dataAutocomplete[key];
            }
            autocompleteObjects.forEach(function (element) {
                dataAutocomplete[element.value] = null;
            });
        });
    })
    Materialize.updateTextFields();

    downloadTable();
    function downloadTable() {
        ajaxParametersForTable.rowOffset = (currentPage - 1) * countTr;
        tableContainer.find("tbody").empty();
        tableContainer.find(".preloader-wrapper").addClass("active");
        ajaxParametersForTable.rowLimit = countTr;
        $.get(params.urlTable, ajaxParametersForTable, function (data) {
            tableContainer.find(".found-length").text(data.length);
            tableContainer.find(".preloader-wrapper").removeClass("active");
            tableContainer.find("tbody").empty();
            fillTable(data);
            if (params.complete) {
                params.complete();
            }
        }).done(function () {
            getAllItems();
        });
    }

    function fillTable(data) {
        try {
            if (data.length == 0) {
                tableContainer.find(".message").show();
                countTablePages = 0;
            } else {
                tableContainer.find(".message").hide();

                countTablePages = Math.ceil(data.length / countTr);


                data.rows.forEach(function (element) {
                    tableContainer.find("tbody").append(params.mapper(element));
                });
            }

            fillPagination();
            setCheckboxes();
        } catch (err) {
            window.location.reload();
        }

    }

    function fillPagination() {
        tableContainer.find(".pagination li:not(:first-child, :last-child)").remove();
        if (countTablePages == 0) {
            return;
        }

        addTablePage(1);
        var firstPageNearCurrent = currentPage - countPagesVisibleNearCurrent;
        var lastPageNearCurrent = currentPage + countPagesVisibleNearCurrent;
        if (firstPageNearCurrent > 2) {
            addTablePageEllipsis();
        }
        if (firstPageNearCurrent <= 1) {
            firstPageNearCurrent = 2;
            // lastPageNearCurrent++;
        }
        if (lastPageNearCurrent >= countTablePages) {
            lastPageNearCurrent = countTablePages - 1;
        }
        for (var i = firstPageNearCurrent; i <= lastPageNearCurrent; i++) {
            addTablePage(i);
        }
        if (lastPageNearCurrent < countTablePages - 1) {
            addTablePageEllipsis();
        }
        if (countTablePages != 1) {
            addTablePage(countTablePages);
        }

        checkVisibleChevronsAndActivePage();
    }

    function addTablePage(number) {
        var aAttributes = {
            href: "#",
            class: "black-text a-dummy",
            text: number
        };
        var liAttributes = {
            class: "waves-effect number-page"
        };
        tableContainer.find(".pagination li:last-child")
            .before($("<li>", liAttributes).append($("<a>", aAttributes)));
    }

    function addTablePageEllipsis() {
        tableContainer.find(".pagination li:last-child")
            .before($("<li>", {class: "disabled"}).append($("<a>", {text: "..."})));
    }

    function checkVisibleChevronsAndActivePage() {
        tableContainer.find(".pagination li.number-page.active").removeClass("active");
        tableContainer.find('.pagination li.number-page:contains("' + currentPage + '")').filter(function (index) {
            return $(this).text() == currentPage;
        }).addClass("active");

        if (currentPage == 1) {
            tableContainer.find(".pagination li:first-child").addClass("disabled");
            tableContainer.find(".pagination li:first-child").removeClass("waves-effect");
        } else {
            tableContainer.find(".pagination li:first-child").removeClass("disabled");
            tableContainer.find(".pagination li:first-child").addClass("waves-effect");
        }
        if (currentPage == countTablePages) {
            tableContainer.find(".pagination li:last-child").addClass("disabled");
            tableContainer.find(".pagination li:last-child").removeClass("waves-effect");
        } else {
            tableContainer.find(".pagination li:last-child").removeClass("disabled");
            tableContainer.find(".pagination li:last-child").addClass("waves-effect");
        }
    }

    $(document).on("click", "#" + tableContainer.attr('id') + " .pagination li.number-page", function (e) {
        var pageClick = $(this).text();
        if (currentPage != pageClick) {
            currentPage = parseInt(pageClick);
            downloadTable();
        }
    });

    $(document).on("click", "#" + tableContainer.attr('id') + " .pagination li.page-left:not(.disabled)", function (e) {
        currentPage--;
        downloadTable();
    });

    $(document).on("click", "#" + tableContainer.attr('id') + " .pagination li.page-right:not(.disabled)", function (e) {
        currentPage++;
        downloadTable();
    });

    tableContainer.find(".dropdown-content li").on("click", function () {
        var aValue = $(this).find("a");
        var th = $(this).closest(".th-dropdown");
        th.find(".dropdown-button").text(aValue.text());
        th.find(".deleter").show();
        ajaxParametersForTable[th.data("field")] = aValue.data("value");
        currentPage = 1;
        downloadTable();
    });

    tableContainer.find(".deleter").on("click", function () {
        var th = $(this).closest(".th-dropdown");
        var dropdownButton = th.find(".dropdown-button");
        dropdownButton.text(dropdownButton.data("default-name"));
        ajaxParametersForTable[th.data("field")] = null;
        th.find(".deleter").hide();
        currentPage = 1;
        downloadTable();
    });

    var oldSortedElement = tableContainer.find(".sorted-element").first();
    oldSortedElement.append($("<span>", {html: "&#9660;", class: "sorter"}));
    tableContainer.find(".sorted-element").on("click", function () {
        var newSortedElement = $(this);
        if (newSortedElement.is(oldSortedElement)) {
            if (ajaxParametersForTable.desc == true) {
                newSortedElement.find("span.sorter").html("&#9650;");
                ajaxParametersForTable.desc = false;
            } else {
                newSortedElement.find("span.sorter").html("&#9660;");
                ajaxParametersForTable.desc = true;
            }
        } else {
            oldSortedElement.find("span.sorter").remove();
            newSortedElement.append($("<span>", {html: "&#9660;", class: "sorter"}));
            ajaxParametersForTable.desc = true;
            ajaxParametersForTable.orderBy = parseInt(newSortedElement.closest("th").data("field"));
            oldSortedElement = newSortedElement;
        }
        currentPage = 1;
        downloadTable();
    });
    var iSearch = $("<i>", {text: "search", class: "material-icons search-element tiny"});
    tableContainer.find(".sorted-element:not(.passive-single-sort)").before(iSearch);

    tableContainer.find(".search-element").on("click", function () {
        var dataField = $(this).closest("th").data("field")
        tableContainer.find(".chips-search input").val(dataField + ":");
        tableContainer.find(".chips-search input").focus();
    });

//----------------Bulk Operations---------------------

    const checkBoxIdPrefix = "bulk-table-";

    var card = $(document).find('#bulk-card');
    var modal = $(document).find('#bulk-change-modal');
    var itemIDsInput = $(document).find('#bulk-item-ids')
    var allItemsID = [];
    var selectedItemsID = [];

    $(document).on('change', '.bulk-select-all', function () {
        if (this.checked) {
            $('input[id^=' + checkBoxIdPrefix + ']').each(function (i, rowCheckbox) {
                $(rowCheckbox).prop('checked', true);
                pushId.call(rowCheckbox);
                selectRow.call(rowCheckbox);
                getBulkCard();
            });
        } else {
            $('input[id^=' + checkBoxIdPrefix + ']').each(function (i, rowCheckbox) {
                $(rowCheckbox).prop('checked', false);
                removeId.call(rowCheckbox);
                setItemsCountOnCard();
            });
            deselectRows();
        }
        if (selectedItemsID.length == 0) {
            setDefaultTableStyle();
        }
    });

    $(document).on('change', '.bulk-checkbox', function () {
        if (this.checked) {
            pushId.call(this);
            selectRow.call(this);
            getBulkCard();
            if (isSubArray(selectedItemsID, allItemsID)) {
                $('.bulk-select-all').prop('checked', true);
            }
        } else {
            removeId.call(this);
            deselectRow.call(this);
            setItemsCountOnCard();
            $('.bulk-select-all').prop('checked', false);
        }
        if (selectedItemsID.length == 0) {
            setDefaultTableStyle();
        }
    });

    $('#bulk-change-btn').on('click', function () {
        initMaterializeComponents();
        $(modal).modal('open');
    });

    $('#bulk-submit').on('click', function (e) {
        e.preventDefault();
        $(itemIDsInput).val(selectedItemsID);
        send('#bulk-change-form', params.bulkUrl, 'PUT').done(function () {
            $(modal).modal('close');
            deselectRows();
            setDefaultTableStyle();
            $(window).trigger('hashchange');
        })
    });

    $('.bulk-field-change').on('change', function () {
        var checkbox = $(this).parent().find('.is-changed-checkbox');
        checkbox.val(true);
        $('div[checkbox-id=' + checkbox.attr('id') + ']').css("display", "block");
    });

    $('.chip-close').on('click', function () {
        var chip = $(this).parent('.bulk-chip');
        var checkboxId = chip.attr('checkbox-id');
        $(modal).find('#' + checkboxId).val(false);
        $(chip).css("display", "none");
    });

    $('#bulk-cancel-btn').on('click', function () {
        deselectRows();
        setDefaultTableStyle();
    });

    function getAllItems() {
        allItemsID = [];
        $('input[id^=' + checkBoxIdPrefix + ']').each(function (i, row) {
            var itemId = row.id.replace(checkBoxIdPrefix, "");
            var index = $.inArray(itemId, allItemsID);
            if (index == -1) {
                allItemsID.push(itemId);
            }
        });
        if (isSubArray(selectedItemsID, allItemsID)) {
            $('.bulk-select-all').prop('checked', true);
        } else {
            $('.bulk-select-all').prop('checked', false);
        }
    }

    function pushId() {
        var itemId = this.id.replace(checkBoxIdPrefix, "");
        var index = $.inArray(itemId, selectedItemsID);
        if (index == -1) {
            selectedItemsID.push(itemId);
        }
    }

    function removeId() {
        var itemId = this.id.replace(checkBoxIdPrefix, "");
        var index = $.inArray(itemId, selectedItemsID);
        if (index != -1) {
            selectedItemsID.splice(index, 1);
        }
    }

    function selectRow() {
        tableContainer.find(".bulk-table").removeClass("striped");
        $(this).parents("tr").addClass("highlighted-row");
    }

    function deselectRow() {
        $(this).parents("tr").removeClass("highlighted-row");
    }

    function deselectRows() {
        tableContainer.find("tr").removeClass("highlighted-row");
        tableContainer.find(".bulk-checkbox").prop('checked', false);
    }

    function setItemsCountOnCard() {
        card.find(".selected-items").text(selectedItemsID.length);
    }

    function getBulkCard() {
        card.css("display", "block");
        setItemsCountOnCard();
    }

    function setDefaultTableStyle() {
        selectedItemsID = [];
        card.css("display", "none");
        tableContainer.find(".bulk-table").addClass("striped");
        $('.bulk-select-all').prop('checked', false);
    }

    function setCheckboxes() {
        if (selectedItemsID.length == 0) {
            setDefaultTableStyle();
        } else {
            for (var i = 0; i < selectedItemsID.length; i++) {
                var checkBox = tableContainer.find("#" + checkBoxIdPrefix + selectedItemsID[i]);
                $(checkBox).attr("checked", "checked");
                $(checkBox).parents("tr").addClass("highlighted-row");
            }
        }
    }

    function initMaterializeComponents() {
        $('.modal').modal({opacity: .5, startingTop: '4%', endingTop: '10%'});
        $('ul.tabs').tabs();
        $('.chips').material_chip();
    }

    function isSubArray(x, y) {
        if (x.length === 0 || y.length === 0) return false;
        return y.reduce(function (included, num) {
            return included && x.includes(num)
        }, true);
    }

    return {
        reloadTable: function () {
            downloadTable();
        }
    }
};